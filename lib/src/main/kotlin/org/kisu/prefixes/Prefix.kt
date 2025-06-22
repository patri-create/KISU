package org.kisu.prefixes

import org.kisu.prefixes.primitives.InBase
import org.kisu.prefixes.primitives.Symbol
import java.math.BigDecimal
import java.math.MathContext

/**
 * A Prefix is a specifier or mnemonic which is added to a unit of measurement to specify multiples or fractions of the
 * units.
 *
 * The prefixes are standarized by the [International Bureau of Weights and Measures](https://www.bipm.org/en/) for the
 * Metric system.
 *
 * Units of information (namely bits, bytes and such) are not covered by the Bureau, and instead are handled by the
 * [International Electrotechnical Commision](https://iec.ch/).
 *
 * All prefixes are comprised of three parts, a base, a power to which that base is multiplied and a symbol that
 * annotates the unit of measure.
 *
 * ```
 * | Name  | Symbol | Base | Power | Multiplier |
 * |-------|--------|------|-------|------------|
 * | centi | c      | 10   | -2    | 0.01       |
 * | kilo  | k      | 10   | 3     | 1_000      |
 * | mega  | M      | 10   | 6     | 1_000_000  |
 * ```
 */
interface Prefix : InBase, Symbol, Comparable<Prefix> {
    /**
     * The exponent to which the [base] is raised to calculate the multiplier for this prefix.
     *
     * For example:
     * - For "centi" (c), the power is -2 → 10⁻² = 0.01
     * - For "kilo" (k), the power is 3 → 10³ = 1,000
     */
    val power: Int

    /**
     * Returns the factor by which a value expressed in this prefix must be multiplied
     * to convert it to the scale of the [other] prefix.
     *
     * This is useful for converting magnitudes between different metric prefixes.
     *
     * For example:
     * ```kotlin
     * kilo.scale(centi) // returns 100_000.0
     * centi.scale(kilo) // returns 0.00001
     * ```
     *
     * The returned value satisfies the equation:
     * ```kotlin
     * magnitudeInPrefix * prefix.scale(other) == magnitudeInOtherPrefix
     * ```
     *
     * @param other the target prefix to which the value should be converted
     * @return the scaling factor to convert a value from this prefix to [other]
     */
    fun scale(other: Prefix): BigDecimal {
        val exponent = power - other.power
        return when {
            exponent == 0 -> BigDecimal.ONE
            exponent > 0 -> base.pow(exponent)
            else -> BigDecimal.ONE.divide(base.pow(-exponent), MathContext.DECIMAL128)
        }
    }

    /**
     * Compares this [Prefix] with the specified [other] [Prefix] for order.
     *
     * Comparison is based on the value of [power]. A negative integer, zero, or a positive integer
     * is returned depending on whether this [Prefix]'s [power] is less than, equal to, or greater
     * than the [other] [Prefix]'s [power].
     *
     * @param other the [Prefix] to compare with this instance.
     * @return a negative integer, zero, or a positive integer as this [Prefix] is less than, equal to,
     * or greater than the specified [other] [Prefix].
     */
    override fun compareTo(other: Prefix): Int = power.compareTo(other.power)
}

/**
 * Returns a list containing this [Prefix] and [other], sorted in ascending order.
 *
 * This function is an extension on any type [T] that is both a [Prefix] and implements
 * [Comparable] with respect to [Prefix]. It uses the natural ordering defined by [compareTo].
 *
 * @param other the other [Prefix] to sort with.
 * @return a [List] containing the two [Prefix] instances, sorted in ascending order.
 */
fun <T> T.sortWith(other: T): List<T> where T : Prefix, T : Comparable<Prefix> =
    listOf(this, other).sorted()
