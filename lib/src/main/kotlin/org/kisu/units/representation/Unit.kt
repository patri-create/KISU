package org.kisu.units.representation

/**
 * Represents a physical unit (such as "m", "s", "kg") with an optional exponent.
 *
 * This class is used to model unit symbols and their powers, and supports basic
 * arithmetic operations (multiplication and division) with units of the same type.
 *
 * Equality and hash code are based **only** on the [symbol] name, not on the exponent.
 * This is useful when you want to treat units like "m²" and "m³" as the same base unit
 * for lookup or comparison purposes.
 *
 * @property symbol The unit symbol, e.g., "m" for meters, "s" for seconds.
 * @property exponent The exponent of the unit (defaults to 1). For example, "m²" would have an exponent of 2.
 */
@Suppress("NoUnitReturn")
class Unit(
    private val symbol: String,
    private val exponent: Exponent = Exponent(1)
) {

    /**
     * Secondary constructor to allow creating an instance from an [Int] exponent directly.
     *
     * @param unit The unit symbol.
     * @param exponent The integer exponent (defaults to 1).
     */
    constructor(unit: String, exponent: Int = 1) : this(unit, Exponent(exponent))

    /**
     * Multiplies two [Unit]s of the same unit type.
     *
     * ```
     * UnitRepresentation("m") * UnitRepresentation("m") //m * m = m²
     * ```
     *
     * @param other The other unit to multiply.
     * @return A new [Unit] with the same unit and summed exponents.
     * @throws IllegalArgumentException if the units are different.
     */
    operator fun times(other: Unit): Unit {
        require(symbol == other.symbol) {
            "Cannot multiply different units: '$symbol' and '${other.symbol}'"
        }

        return Unit(symbol, exponent + other.exponent)
    }

    /**
     * Divides this [Unit] by another one of the same unit type.
     *
     * ```
     * UnitRepresentation("m", 2) * UnitRepresentation("m") //m² / m = m
     * ```
     *
     * @param other The unit to divide by.
     * @return A new [Unit] with the same unit and the difference of the exponents.
     * @throws IllegalArgumentException if the units are different.
     */
    operator fun div(other: Unit): Unit {
        require(symbol == other.symbol) {
            "Cannot divide different units: '$symbol' and '${other.symbol}'"
        }

        return Unit(symbol, exponent - other.exponent)
    }

    /**
     * Lazily computed string representation of the unit and its exponent, e.g., `"m²"`.
     */
    private val representation: String by lazy {
        "$symbol$exponent"
    }

    /**
     * Checks equality based **only** on the [symbol] name, ignoring the exponent.
     *
     * This is intentional so units like "m²" and "m³" can be grouped or compared
     * as the same base unit symbol.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Unit

        return symbol == other.symbol
    }

    /**
     * Computes hash code based on the [symbol] only, ignoring the exponent.
     */
    override fun hashCode(): Int = symbol.hashCode()

    /**
     * Returns the string representation of the unit, including superscript exponent
     * if applicable (e.g., `"m²"`).
     */
    override fun toString(): String = representation
}
