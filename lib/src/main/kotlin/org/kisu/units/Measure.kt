package org.kisu.units

import org.kisu.KisuConfig
import org.kisu.bigDecimal
import org.kisu.orElse
import org.kisu.prefixes.primitives.System
import org.kisu.zero
import java.math.BigDecimal

/**
 * Represents a physical quantity composed of a [magnitude], a [prefix], and a [unit].
 *
 * This generic class models measurements in a unit system with metric-style prefixes,
 * such as meters, grams, or seconds.
 *
 * It is designed to support operations like addition, subtraction, scaling, and conversion
 * between compatible units with automatic prefix normalization (e.g., converting 1000 millimeters to 1 meter).
 *
 * @param Prefix The type representing the system of prefixes (e.g., [Metric]).
 *        It must implement both [System] (to provide ordering and base relations)
 *        and [org.kisu.prefixes.Prefix] (to support scaling operations).
 *
 * @param Self The concrete subclass type used for safe covariant returns.
 *        This is a common pattern to support fluent APIs in hierarchies.
 *
 * @property magnitude The numerical value of the measurement in the specified [prefix].
 * @property prefix The metric prefix applied to the unit (e.g., `kilo`, `milli`).
 *        This determines the scale of the [magnitude] relative to the base unit.
 * @property unit A string representing the unit symbol (e.g., `"m"` for meters, `"g"` for grams).
 *
 * @constructor Protected to enforce instantiation via factory methods or subclasses.
 *
 * @see System for prefix ordering and canonical base relationships.
 * @see org.kisu.prefixes.Prefix for scaling behavior.
 */
@Suppress("TooManyFunctions")
abstract class Measure<Prefix, Self : Measure<Prefix, Self>> protected constructor(
    private val magnitude: BigDecimal,
    private val prefix: Prefix,
    private val unit: String,
) : Comparable<Self> where Prefix : org.kisu.prefixes.Prefix<Prefix>, Prefix : System<Prefix> {
    protected constructor(magnitude: Double, prefix: Prefix, unit: String) : this(
        BigDecimal.valueOf(magnitude),
        prefix,
        unit,
    )

    /**
     * Returns the most human-readable form of the measurement by automatically choosing the
     * most appropriate [prefix] such that the magnitude is near or above 1.
     *
     * If the magnitude is zero, this falls back to the [canonical] representation.
     * If the current [prefix] is already the smallest and the magnitude is less than 1,
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
            prefix == prefix.smallest && magnitude.abs() < BigDecimal.ONE -> self
            prefix == prefix.largest && magnitude.abs() > BigDecimal.ONE -> self
            else ->
                prefix.all
                    .asSequence()
                    .map(this::to)
                    .lastOrNull { measure -> measure.magnitude.abs() >= BigDecimal.ONE }
                    .orElse { to(prefix.largest) }
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
        if (prefix == prefix.canonical) {
            self
        } else if (!magnitude.zero) {
            to(prefix.canonical)
        } else {
            invoke(magnitude, prefix.canonical)
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
            "0 ${prefix.canonical}$unit"
        } else {
            "$magnitude $prefix$unit"
        }
    }

    val zero: Boolean by lazy { magnitude.zero }

    @Suppress("UNCHECKED_CAST")
    private val self: Self = this as Self

    /**
     * Rescales the current measurement to a different [prefix], adjusting the [magnitude] accordingly.
     *
     * ```
     * val distance = Measure(1500.0, METER, "m")
     * val inKilometers = distance.to(KILO)
     * println(inKilometers.literal) // "1.5 km"
     * ```
     *
     * @param other The target prefix to convert to.
     * @return A new [Measure] instance using the new [prefix] and the converted [magnitude].
     */
    fun to(other: Prefix): Self {
        if (prefix == other) {
            return self
        }
        val conversion = prefix.to(other)
        return invoke(magnitude * conversion, other)
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
        val (smallest, largest) = prefix.sortWith(other.prefix)
        return invoke(this.to(smallest).magnitude + other.to(smallest).magnitude, smallest).to(largest)
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
        val (smallest, largest) = prefix.sortWith(other.prefix)
        return invoke(this.to(smallest).magnitude - other.to(smallest).magnitude, smallest).to(largest)
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
    operator fun times(number: BigDecimal): Self = invoke(magnitude.times(number), prefix)

    /**
     * Divides this measure by a [BigDecimal] scalar.
     *
     * @param number The scalar to divide by.
     * @return A new measure scaled by the given factor.
     */
    operator fun div(number: BigDecimal): Self = invoke(magnitude.divide(number, KisuConfig.precision), prefix)

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
     * Factory method to create a new measure of the same type with a given magnitude and prefix.
     *
     * Subclasses must implement this to support operations that return new instances.
     *
     * @param magnitude The numeric value of the new measure.
     * @param prefix The prefix/unit for the new measure.
     * @return A new instance of [Measure] with the specified properties.
     */
    protected abstract operator fun invoke(
        magnitude: BigDecimal,
        prefix: Prefix,
    ): Self

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
     * The [prefix] is intentionally ignored in the comparison,
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

        if (unit != other.unit) return false

        val left = canonical
        val right = other.canonical
        return left.magnitude.compareTo(right.magnitude) == 0
    }

    /**
     * Returns a hash code value for the [Measure] object.
     *
     * The hash code is based on the canonical [magnitude] and [unit] only,
     * since [prefix] is ignored in the [equals] comparison as all units are compared to their canonical unit.
     *
     * @return The hash code value.
     */
    override fun hashCode(): Int {
        val canonical = canonical
        var result = canonical.magnitude.hashCode()
        result = 31 * result + canonical.unit.hashCode()
        return result
    }
}
