package org.kisu.units.representation

/**
 * Represents an integer exponent, providing utilities for arithmetic and string representation
 * using Unicode superscripts for every value except 0 and 1.
 *
 * This class is intended for use in contexts such as unit systems or mathematical formatting,
 * where exponents are displayed in a human-readable superscript form.
 *
 * @property exponent The integer value of the exponent.
 */
data class Exponent(private val exponent: Int) {
    /**
     * Indicates whether the exponent is positive.
     *
     * Returns `true` if the exponent is greater than zero and `false` otherwise.
     */
    val positive: Boolean by lazy {
        exponent > 0
    }

    /**
     * Indicates whether the exponent is zero.
     *
     * Returns `true` if the exponent is zero and `false` otherwise.
     */
    val zero: Boolean by lazy {
        exponent == 0
    }

    /**
     * A lazily computed string representation of the exponent using Unicode superscript characters.
     *
     * Exponents 0 and 1 are rendered as an empty string, matching common unit notation where `x¹` is shown as `x`.
     * Every other exponent is converted to a superscript string, including negative exponents such as `⁻¹`.
     *
     * ```
     * Exponent(2) // "²"
     * ```
     * ```
     * Exponent(1) // ""
     * ```
     * ```
     * Exponent(-1) // "⁻¹"
     * ```
     */
    val representation: String by lazy {
        if (exponent != 0 && exponent != 1) {
            exponent.toString().fold("") { acc, ch -> acc + superscripts[ch] }
        } else {
            ""
        }
    }

    /**
     * Returns the inverse of this exponent.
     *
     * This is equivalent to multiplying the exponent by -1.
     * For example, an exponent of 3 becomes -3, and -2 becomes 2.
     */
    val inverted: Exponent by lazy { Exponent(-exponent) }

    /**
     * Adds two [Exponent] instances.
     *
     * ```
     * Exponent(1) + Exponent(2) // "³"
     * ```
     *
     * @param other The [Exponent] to add.
     * @return A new [Exponent] representing the sum.
     */
    operator fun plus(other: Exponent): Exponent = Exponent(exponent + other.exponent)

    /**
     * Subtracts another [Exponent] from this one.
     *
     * ```
     * Exponent(1) - Exponent(2) // "⁻¹"
     * ```
     *
     * @param other The [Exponent] to subtract.
     * @return A new [Exponent] representing the difference.
     */
    operator fun minus(other: Exponent): Exponent = Exponent(exponent - other.exponent)

    /**
     * Returns the string representation of this exponent using superscripts,
     * as defined in [representation].
     */
    override fun toString(): String = representation

    private companion object {
        /**
         * Maps numeric characters and minus sign to their corresponding Unicode superscript symbols.
         */
        private val superscripts = mapOf(
            '0' to '⁰', '1' to '¹', '2' to '²', '3' to '³', '4' to '⁴',
            '5' to '⁵', '6' to '⁶', '7' to '⁷', '8' to '⁸', '9' to '⁹', '-' to '⁻'
        )
    }
}
