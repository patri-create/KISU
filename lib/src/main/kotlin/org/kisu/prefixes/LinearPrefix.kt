package org.kisu.prefixes

import org.kisu.KisuConfig
import org.kisu.prefixes.algebra.LinearAlgebra
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

/**
 * Prefix whose position in a scale is represented directly by its concrete multiplier.
 *
 * Linear prefixes do not need a separate exponent coordinate. Their [factor] is both the value used for ordering and
 * the multiplier applied by [LinearAlgebra].
 */
interface LinearPrefix<Self : LinearPrefix<Self>> : Prefix<Self> {
    /**
     * Concrete multiplier by which a unit is scaled when this prefix is applied.
     *
     * For example:
     * - "milli" → 0.001
     * - "kilo" → 1,000
     * - "mebi" → 1,048,576
     */
    val factor: BigDecimal

    /**
     * Compares this prefix to another linear prefix by [factor].
     *
     * @param other the other linear prefix to compare
     * @return a negative integer if this prefix is smaller, zero if equal, or a positive integer if larger
     */
    override fun compareTo(other: Self): Int = factor.compareTo(other.factor)
}

/**
 * Multiplies this prefix by another prefix of the same system.
 *
 * This combines the scaling factors of both prefixes using multiplication, and returns
 * a pair consisting of:
 * - The closest matching prefix in the system for the resulting factor.
 * - A remainder that captures the excess factor not represented by the prefix itself.
 *
 * For example:
 * ```
 * Time.MINUTE * Time.MINUTE // (Time.HOUR, 1)
 * ```
 * ```
 * Time.MILLENNIUM * Time.MINUTE // (Time.MILLENNIUM, 60)
 * ```
 *
 * @param other The prefix to multiply with.
 * @return A pair of (prefix, remainder) for the resulting factor.
 */
operator fun <P> P.times(other: P): Pair<P, BigDecimal> where P : LinearPrefix<P>, P : System<P> {
    val factor = this.factor.multiply(other.factor)
    val newPrefix = find(factor)
    return newPrefix to factor.divide(newPrefix.factor, KisuConfig.precision)
}

/**
 * Divides this prefix by another prefix of the same system.
 *
 * This combines the scaling factors of both prefixes using division, and returns
 * a pair consisting of:
 * - The closest matching prefix in the system for the resulting factor.
 * - A remainder that captures the fractional difference not represented by the prefix.
 *
 * For example:
 * ```
 * Time.HOUR / Time.MINUTE // (Time.MINUTE, 1)
 * ```
 * ```
 * Time.QUECTO / Time.MINUTE // (Time.QUECTO, 1 / 60)
 * ```
 * @param other The prefix to divide by.
 * @return A pair of (prefix, remainder) for the resulting factor.
 */
operator fun <P> P.div(other: P): Pair<P, BigDecimal> where P : LinearPrefix<P>, P : System<P> {
    val factor = this.factor.divide(other.factor, KisuConfig.precision)
    val newPrefix = find(factor)
    return newPrefix to factor.divide(newPrefix.factor, KisuConfig.precision)
}
