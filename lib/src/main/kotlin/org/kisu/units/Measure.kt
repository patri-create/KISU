package org.kisu.units

import org.kisu.Magnitude
import org.kisu.magnitude
import org.kisu.orElse
import org.kisu.prefixes.primitives.System
import org.kisu.units.representation.Expression

/**
 * Base type for a measured quantity expressed as a numeric `magnitude` in some unit `expression`.
 *
 * `Measure` handles arithmetic on quantities of the same concrete type, conversion between compatible expressions, and
 * normalization into canonical or human-friendly forms. The expression type [A] may be a single prefixed unit such as
 * `km`, or a compound expression such as `m/s` or `J/(K·mol)`.
 *
 * @param A The unit-expression type used by the measure. It must be both an [Expression] and a [System] so it can be
 * ordered, converted, and normalized.
 * @param Self The concrete measure subtype. This self-referential type keeps fluent operations strongly typed.
 * @property magnitude The numeric value represented in `expression`.
 * @property expression The unit expression associated with `magnitude`.
 * @constructor Protected to enforce construction through concrete measure types.
 */
@Suppress("TooManyFunctions")
abstract class Measure<A, Self : Measure<A, Self>> protected constructor(
    private val magnitude: Magnitude,
    private val expression: A,
    private val create: (Magnitude, A) -> Self
) : Comparable<Self> where A : Expression<A>, A : System<A> {
    /**
     * Returns this measure converted to its canonical expression.
     *
     * Example: `1.5 km` becomes `1500 m`.
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
     * Returns this measure rescaled to a more readable expression.
     *
     * The chosen expression is the largest available one whose converted magnitude has absolute value at least `1`.
     * Zero is always rendered in the canonical expression. If the current expression is already at the smallest or
     * largest end of the system and no better rescaling exists, this returns the current value unchanged.
     */
    val optimal: Self by lazy {
        when {
            magnitude.zero -> canonical
            expression == expression.smallest && magnitude.abs < Magnitude.ONE -> self
            expression == expression.largest && magnitude.abs > Magnitude.ONE -> self
            else ->
                expression.all
                    .asSequence()
                    .map(this::to)
                    .lastOrNull { measure -> measure.magnitude.abs >= Magnitude.ONE }
                    .orElse { to(expression.largest) }
        }
    }

    /**
     * Returns a string for the current stored value without rescaling.
     *
     * Zero is always rendered using the canonical expression so equivalent zero values produce the same output.
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
     * Decomposes this measure into additive parts expressed with the system's available expressions.
     *
     * The result is a [List] of the same measure type, not `(count, prefix)` pairs. Decomposition is performed from
     * the canonical value using the available expressions from largest to smallest, omitting zero-valued parts.
     *
     * Example: a canonical metric length of `1234 m` decomposes into `1 km`, `2 hm`, `3 dam`, and `4 m`.
     *
     * If this measure is zero, the result contains a single zero value in the canonical expression.
     */
    val decomposition: List<Self> by lazy {
        if (zero) {
            return@lazy listOf(create(Magnitude.ZERO, expression.canonical))
        }
        if (expression != expression.canonical) {
            canonical.decomposition
        } else {
            expression.decompose(magnitude)
                .map { (magnitude, expression) -> create(magnitude, expression) }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private val self: Self = this as Self

    /**
     * Converts this measure to [other].
     *
     * @param other The target expression.
     * @return A new measure with the converted magnitude and the requested expression.
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
     * Internally converts the number to [Magnitude].
     *
     * @param number The scalar to multiply by.
     * @return A new measure scaled by the given factor.
     */
    operator fun times(number: Number): Self = times(number.magnitude)

    /**
     * Multiplies this measure by a [Magnitude] scalar.
     *
     * @param number The scalar to multiply by.
     * @return A new measure scaled by the given factor.
     */
    operator fun times(number: Magnitude): Self = create(magnitude * number, expression)

    /**
     * Divides this measure by a [Magnitude] scalar.
     *
     * @param number The scalar to divide by.
     * @return A new measure scaled by the given factor.
     */
    operator fun div(number: Magnitude): Self = create(magnitude / number, expression)

    /**
     * Divides this measure by a [Number] scalar.
     *
     * Internally converts the number to [Magnitude].
     *
     * @param number The scalar to divide by.
     * @return A new measure scaled by the given factor.
     */
    operator fun div(number: Number): Self = div(number.magnitude)

    /**
     * Returns the magnitude component of this measure.
     *
     * Enables destructuring declarations like:
     * ```
     * val (magnitude, _, _) = measure
     * ```
     */
    operator fun component1(): Magnitude = magnitude

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
     * Returns the canonical symbol of this measure's expression.
     *
     * Enables destructuring declarations like:
     * ```
     * val (_, _, symbol) = measure
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
     * Returns this measure and [other] ordered by canonical magnitude.
     *
     * @param other The measure to compare with.
     * @return A pair whose first element is the smaller value and whose second element is the larger.
     */
    infix fun sortWith(other: Self): Pair<Self, Self> =
        listOf(self, other)
            .sorted()
            .let { (left, right) -> left to right }

    /**
     * Returns the [optimal] representation of this measure.
     */
    override fun toString(): String = optimal.representation

    /**
     * Compares this measure with [other] for value equality.
     *
     * Two measures are equal when they have the same runtime type, the same canonical expression, and equal canonical
     * magnitudes. The original stored expression is ignored, so equivalent values such as `1 km` and `1000 m` compare
     * as equal.
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
     * Returns a hash code based on the canonical magnitude and canonical expression.
     */
    override fun hashCode(): Int {
        val canonical = canonical
        var result = canonical.magnitude.stripTrailingZeros().hashCode()
        result = 31 * result + canonical.expression.hashCode()
        return result
    }
}
