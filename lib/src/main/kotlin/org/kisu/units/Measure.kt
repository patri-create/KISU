package org.kisu.units

import org.kisu.KisuConfig
import org.kisu.bigDecimal
import org.kisu.orElse
import org.kisu.prefixes.Metric
import org.kisu.prefixes.primitives.System
import org.kisu.zero
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Represents a physical quantity composed of a [magnitude], a [expression], and a [unit].
 *
 * This generic class models measurements in a unit system with metric-style prefixes,
 * such as meters, grams, or seconds.
 *
 * It is designed to support operations like addition, subtraction, scaling, and conversion
 * between compatible units with automatic prefix normalization (e.g., converting 1000 millimeters to 1 meter).
 *
 * @param Expression The type representing the system of prefixes (e.g., [Metric]).
 *        It must implement both [System] (to provide ordering and base relations)
 *        and [org.kisu.prefixes.Prefix] (to support scaling operations).
 *
 * @param Self The concrete subclass type used for safe covariant returns.
 *        This is a common pattern to support fluent APIs in hierarchies.
 *
 * @property magnitude The numerical value of the measurement in the specified [expression].
 * @property expression The metric prefix applied to the unit (e.g., `kilo`, `milli`).
 *        This determines the scale of the [magnitude] relative to the base unit.
 * @property unit A string representing the unit symbol (e.g., `"m"` for meters, `"g"` for grams).
 *
 * @constructor Protected to enforce instantiation via factory methods or subclasses.
 *
 * @see System for prefix ordering and canonical base relationships.
 * @see org.kisu.prefixes.Prefix for scaling behavior.
 */
@Suppress("TooManyFunctions")
abstract class Measure<A, Self : Measure<A, Self>> protected constructor(
    private val magnitude: BigDecimal,
    private val expression: A,
    private val create: (BigDecimal, A) -> Self
) : Comparable<Self> where A : Expression<A>, A : System<A> {

    /**
     * Returns the most human-readable form of the measurement by automatically choosing the
     * most appropriate [expression] such that the magnitude is near or above 1.
     *
     * If the magnitude is zero, this falls back to the [canonical] representation.
     * If the current [expression] is already the smallest and the magnitude is less than 1,
     * or if it is the largest and the magnitude is greater than 1, the [representation] form is returned.
     *
     * Otherwise, it selects the largest rescaled prefix such that the resulting magnitude is ≥ 1.
     *
     * Does not trim the floating point in any form, to avoid precision loss.
     *
     * Example: `"1500m" -> "1.5 km"`
     *
     * ```
     * val distance = Measure(1500.0, BASE, "m")
     * distance.optimal // "1.5km"
     * ```
     */
    val optimal: Self by lazy {
        when {
            magnitude.zero -> canonical
            expression == expression.smallest && magnitude.abs() < BigDecimal.ONE -> self
            expression == expression.largest && magnitude.abs() > BigDecimal.ONE -> self
            else ->
                expression.all
                    .asSequence()
                    .map(this::to)
                    .lastOrNull { measure -> measure.magnitude.abs() >= BigDecimal.ONE }
                    .orElse { to(expression.largest) }
        }
    }

    /**
     * Returns the canonical form of the measurement by converting it to the base prefix.
     *
     * Example `"1.5 km" -> "1500 m"`
     *
     * ```
     * val distance = Measure(1.5, KILO, "m")
     * distance.canonical // "1500m"
     * ```
     */
    val canonical: Self by lazy {
        if (expression == expression.canonical) {
            self
        } else if (!magnitude.zero) {
            to(expression.canonical)
        } else {
            create(magnitude, expression.canonical)
        }
    }

    /**
     * Returns the raw string representation of the measurement without any rescaling.
     *
     * If magnitude is 0, it returns the value in the canonical unit, even if the prefix stated is different.
     *
     * ```
     * val distance = Measure(1.5, KILO, "m")
     * distance.representation // "1.5 km"
     * ```
     *
     * ```
     * val distance = Measure(0, KILO, "m")
     * distance.representation // "0 m"
     * ```
     */
    val representation: String by lazy {
        if (magnitude.zero) {
            "0 ${expression.canonical.symbol}"
        } else {
            "${magnitude.stripTrailingZeros()} ${expression.symbol}"
        }
    }

    /**
     * Indicates whether the magnitude of this measure is zero.
     *
     * Lazily evaluated for performance.
     */
    val zero: Boolean by lazy { magnitude.zero }

    /**
     * Decomposes this measure into a list of (count, prefix) pairs, representing how
     * the magnitude can be expressed as a sum of scaled canonical prefixes.
     *
     * For example:
     * ```
     * val length = Length(BigDecimal("1.234,55"), Metric.KILO) // 1,23455 km
     * val parts = length.decomposition
     * // parts:
     * // listOf(
     * //     1 to Metric.KILO,
     * //     2.toBigInteger() to Metric.HECTO,
     * //     3.toBigInteger() to Metric.DECA,
     * //     4.toBigInteger() to Metric.BASE,
     * //     5.
     * // )
     * ```
     *
     * - If the magnitude is zero, returns a single pair with 0 and the canonical prefix.
     * - If the current prefix is not canonical, delegates to the canonical version’s decomposition.
     * - Otherwise, attempts to break down the magnitude using available descending prefixes.
     *
     * Lazily evaluated for performance.
     */
    val decomposition: List<Self> by lazy {
        if (zero) {
            return@lazy listOf(create(BigDecimal.ZERO, expression.canonical))
        }
        if (expression != expression.canonical) {
            canonical.decomposition
        } else {
            var remainder = magnitude.stripTrailingZeros().abs()
            expression.all.sortedDescending().fold(listOf<Self>()) { acc, prefix ->
                remainder.divide(prefix.factor, 0, RoundingMode.DOWN).let { quotient ->
                    acc + create(quotient, prefix)
                        .also { remainder -= prefix.factor.multiply(quotient) }
                }
            }.filter { measure -> !measure.zero }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private val self: Self = this as Self

    /**
     * Rescales the current measurement to a different [expression], adjusting the [magnitude] accordingly.
     *
     * ```
     * val distance = Measure(1500.0, METER, "m")
     * val inKilometers = distance.to(KILO)
     * println(inKilometers.literal) // "1.5 km"
     * ```
     *
     * @param other The target prefix to convert to.
     * @return A new [Measure] instance using the new [expression] and the converted [magnitude].
     */
    fun to(other: A): Self {
        if (expression == other) {
            return self
        }
        val conversion = expression.to(other)
        return create(magnitude * conversion, other)
    }

    /**
     * Adds two measures of the same type.
     *
     * Both measures are converted to the smallest of their prefixes for addition,
     * and the result is returned in the larger prefix for better readability.
     *
     * @param other The measure to add.
     * @return A new measure representing the sum.
     */
    operator fun plus(other: Self): Self {
        val (smallest, largest) = expression.sortWith(other.expression)
        return create(this.to(smallest).magnitude + other.to(smallest).magnitude, smallest).to(largest)
    }

    /**
     * Subtracts one measure from another of the same type.
     *
     * Both measures are converted to the smallest of their prefixes for subtraction,
     * and the result is returned in the larger prefix for better readability.
     *
     * @param other The measure to subtract.
     * @return A new measure representing the difference.
     */
    operator fun minus(other: Self): Self {
        val (smallest, largest) = expression.sortWith(other.expression)
        return create(this.to(smallest).magnitude - other.to(smallest).magnitude, smallest).to(largest)
    }

    /**
     * Multiplies this measure by a [Number] scalar.
     *
     * Internally converts the number to [BigDecimal].
     *
     * @param number The scalar to multiply by.
     * @return A new measure scaled by the given factor.
     */
    operator fun times(number: Number): Self = times(number.bigDecimal)

    /**
     * Multiplies this measure by a [BigDecimal] scalar.
     *
     * @param number The scalar to multiply by.
     * @return A new measure scaled by the given factor.
     */
    operator fun times(number: BigDecimal): Self = create(magnitude.times(number), expression)

    /**
     * Divides this measure by a [BigDecimal] scalar.
     *
     * @param number The scalar to divide by.
     * @return A new measure scaled by the given factor.
     */
    operator fun div(number: BigDecimal): Self = create(magnitude.divide(number, KisuConfig.precision), expression)

    /**
     * Divides this measure by a [Number] scalar.
     *
     * Internally converts the number to [BigDecimal].
     *
     * @param number The scalar to divide by.
     * @return A new measure scaled by the given factor.
     */
    operator fun div(number: Number): Self = div(number.bigDecimal)

    /**
     * Returns the magnitude component of this measure.
     *
     * Enables destructuring declarations like:
     * ```
     * val (magnitude, _, _) = measure
     * ```
     */
    operator fun component1(): BigDecimal = magnitude

    /**
     * Returns the expression component of this measure.
     *
     * Enables destructuring declarations like:
     * ```
     * val (_, expression, _) = measure
     * ```
     */
    operator fun component2(): A = expression

    /**
     * Returns the unit name of this measure.
     *
     * Enables destructuring declarations like:
     * ```
     * val (_, _, unit) = measure
     * ```
     */
    operator fun component3(): String = expression.canonical.symbol

    /**
     * Compares this measure to [other] for ordering.
     *
     * The comparison is done using their canonical (base unit) magnitudes.
     *
     * @param other The measure to compare against.
     * @return A negative integer, zero, or a positive integer as this measure is less than,
     *         equal to, or greater than the specified measure.
     */
    override fun compareTo(other: Self): Int = canonical.magnitude.compareTo(other.canonical.magnitude)

    /**
     * Sorts this [Measure] and the [other] measure in ascending order based on their canonical magnitude.
     *
     * @param other The other [Measure] to compare with.
     * @return A [Pair] where the first element is the smaller (or equal) measure and the second is the larger.
     *
     * This uses the [Comparable] implementation of [Measure], which compares based on canonical magnitude.
     * It is useful when performing arithmetic operations that require a common unit or a predictable ordering.
     *
     * Example:
     * ```
     * val a = Measure(5, Metric.KILO)
     * val b = Measure(3000, Metric.UNIT)
     * val (smaller, larger) = a.sortWith(b)
     * ```
     */
    infix fun sortWith(other: Self): Pair<Self, Self> =
        listOf(self, other)
            .sorted()
            .let { (left, right) -> left to right }

    /**
     * Returns the [optimal] string representation of this measurement for inspection purposes.
     *
     * ```
     * val distance = Measure(1500.0, BASE, "m")
     * distance.toString() // "1.5km"
     * ```
     */
    override fun toString(): String = optimal.representation

    /**
     * Compares this [Measure] with another object for equality.
     *
     * Two [Measure] instances are considered equal if:
     * - They are the same instance, or
     * - They have the same runtime class,
     * - Their [unit] is equal,
     * - Their canonical [magnitude] values are equal.
     *
     * The [expression] is intentionally ignored in the comparison,
     * as equality is determined by the canonical (normalized) value.
     *
     * ```
     * val distanceInKm = Measure(1.0, KILO, "m")
     * val distanceInM = Measure(1000.0, BASE, "m")
     *
     * distanceInKm == distanceInM // true
     * ```
     *
     * ```
     * val temperature = Measure(200.0, BASE, "º")
     * val weight = Measure(10.0, KILO, "g")
     *
     * temperature == weight // false
     * ```
     *
     * @param other The object to compare with.
     * @return `true` if the objects are considered equal, `false` otherwise.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Measure<*, *>

        if (expression.canonical != other.expression.canonical) return false

        val left = canonical
        val right = other.canonical
        return left.magnitude.compareTo(right.magnitude) == 0
    }

    /**
     * Returns a hash code value for the [Measure] object.
     *
     * The hash code is based on the canonical [magnitude] and [unit] only,
     * since [expression] is ignored in the [equals] comparison as all units are compared to their canonical unit.
     *
     * @return The hash code value.
     */
    override fun hashCode(): Int {
        val canonical = canonical
        var result = canonical.magnitude.hashCode()
        result = 31 * result + canonical.expression.hashCode()
        return result
    }
}
