package org.kisu.prefixes.primitives

/**
 * Represents the symbolic representation of a unit prefix.
 *
 * A `Symbol` is a short textual abbreviation used to denote a prefix in units of measurement. For example:
 *
 * - `"k"` for kilo (10³)
 * - `"M"` for mega (10⁶)
 * - `"c"` for centi (10⁻²)
 *
 * This interface provides access to the symbol string and defines a standard `toString` behavior for consistent display
 * or serialization.
 */
interface Symbol {
    /**
     * The textual symbol representing this prefix.
     *
     * For example: `"k"` for kilo, `"m"` for milli, or `"μ"` for micro.
     */
    val symbol: String

    /**
     * Returns the string representation of the symbol.
     *
     * Typically, this should return the same value as [symbol].
     */
    override fun toString(): String
}
