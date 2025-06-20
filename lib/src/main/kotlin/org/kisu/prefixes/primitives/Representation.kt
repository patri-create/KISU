package org.kisu.prefixes.primitives

/**
 * A concrete implementation of [Symbol] that represents a unit prefix's symbol as a string.
 *
 * This class holds the symbolic representation of a prefix (e.g. `"k"` for kilo, `"m"` for milli) and provides a
 * standard `toString` implementation that returns the symbol itself.
 *
 * @property symbol The string symbol representing the prefix.
 */
class Representation(override val symbol: String) : Symbol {

    /**
     * Returns the symbol string.
     *
     * Equivalent to accessing [symbol] directly.
     */
    override fun toString(): String = symbol
}
