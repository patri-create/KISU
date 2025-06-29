package org.kisu.prefixes

import org.kisu.KisuConfig
import org.kisu.prefixes.primitives.Symbol
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

/**
 * A `Prefix` is a mnemonic added to a unit of measurement to indicate multiplication by a specific factor.
 *
 * These prefixes are commonly standardized by the [International Bureau of Weights and Measures](https://www.bipm.org/en/)
 * (for SI units), or the [International Electrotechnical Commission](https://www.iec.ch/) (for binary units).
 *
 * Prefixes express consistent scaling factors that allow measurement units to span a wide range of magnitudes, such as:
 * - "milli" (m) for 0.001
 * - "kilo" (k) for 1,000
 * - "mega" (M) for 1,000,000
 * - "mebi" (Mi) for 1,048,576 (2²⁰)
 *
 * This interface abstracts the scaling factor directly, without relying on base/exponent representations.
 *
 * ```
 * | Name  | Symbol | Factor           |
 * |-------|--------|------------------|
 * | centi | c      | 0.01             |
 * | kilo  | k      | 1,000            |
 * | mega  | M      | 1,000,000        |
 * | mebi  | Mi     | 1,048,576        |
 * ```
 *
 * Implementations typically come from enums such as [Metric], [Binary], or other domain-specific systems.
 */
interface Prefix<Self : Prefix<Self>> : Symbol, Comparable<Prefix<Self>> {
    /**
     * The factor by which a unit is scaled when this prefix is applied.
     *
     * For example:
     * - "milli" → 0.001
     * - "kilo" → 1,000
     * - "mebi" → 1,048,576
     */
    val factor: BigDecimal

    /**
     * Returns the factor needed to convert a value expressed with this prefix into one expressed in [other].
     *
     * For example:
     * ```kotlin
     * KILO.to(MILLI) // → 1_000_000
     * MILLI.to(KILO) // → 0.000001
     * ```
     *
     * This satisfies:
     * ```kotlin
     * magnitudeInThis * this.to(other) == magnitudeInOther
     * ```
     *
     * @param other the target prefix
     * @return the conversion factor from this prefix to [other]
     */
    fun to(other: Self): BigDecimal = factor.divide(other.factor, KisuConfig.precision)

    /**
     * Returns this prefix and [other], sorted in ascending order of their [factor].
     *
     * @param other the prefix to compare
     * @return a [Pair] of prefixes sorted from smallest to largest
     */
    @Suppress("UNCHECKED_CAST")
    infix fun sortWith(other: Self): Pair<Self, Self> =
        listOf(this as Self, other)
            .sorted()
            .let { (left, right) -> left to right }

    /**
     * Compares this [Prefix] to another based on their [factor].
     *
     * @param other the other [Prefix] to compare
     * @return a negative integer if this prefix is smaller, zero if equal, or a positive integer if larger
     */
    override fun compareTo(other: Prefix<Self>): Int = factor.compareTo(other.factor)
}

/**
 * Returns `true` if this [Prefix] is the **canonical** or base unit prefix for its system.
 *
 * In most systems, this typically corresponds to the unit with a factor of 1 (e.g., no prefix in metric,
 * or `BASE` in binary). It compares the current prefix with the system-defined
 * [org.kisu.prefixes.primitives.System.canonical] prefix.
 *
 * @return `true` if this prefix is the canonical base unit, `false` otherwise.
 */
val <P> P.isCanonical: Boolean where P : Prefix<P>, P : System<P>
    get() = this == canonical
