package org.kisu.units.representation

import org.kisu.units.base.Ampere
import org.kisu.units.base.Candela
import org.kisu.units.base.Kelvin
import org.kisu.units.base.Kilogram
import org.kisu.units.base.Metre
import org.kisu.units.base.Mol
import org.kisu.units.base.Second
import org.kisu.units.special.Becquerel
import org.kisu.units.special.Coulomb
import org.kisu.units.special.Farad
import org.kisu.units.special.Gray
import org.kisu.units.special.Henry
import org.kisu.units.special.Joule
import org.kisu.units.special.Katal
import org.kisu.units.special.Lumen
import org.kisu.units.special.Lux
import org.kisu.units.special.Newton
import org.kisu.units.special.Ohm
import org.kisu.units.special.Pascal
import org.kisu.units.special.Radian
import org.kisu.units.special.Siemens
import org.kisu.units.special.Sievert
import org.kisu.units.special.Steradian
import org.kisu.units.special.Tesla
import org.kisu.units.special.Volt
import org.kisu.units.special.Watt
import org.kisu.units.special.Weber

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
) : Comparable<Unit> {

    /**
     * Secondary constructor to allow creating an instance from an [Int] exponent directly.
     *
     * @param unit The unit symbol.
     * @param exponent The integer exponent (defaults to 1).
     */
    constructor(unit: String, exponent: Int = 1) : this(unit, Exponent(exponent))

    /**
     * Indicates whether this unit has a zero exponent.
     *
     * For example, in an expression like m⁰, this would return `true`,
     * while for m it would return `false`.
     */
    val zero: Boolean by lazy {
        exponent.zero
    }

    /**
     * Indicates whether this unit has a positive exponent.
     *
     * For example, in an expression like m², this would return `true`,
     * while for m⁻² it would return `false`.
     */
    val positive: Boolean by lazy {
        exponent.positive
    }

    /**
     * Lazily computed string representation of the unit and its exponent, e.g., `"m²"`.
     */
    private val representation: String by lazy {
        "$symbol$exponent"
    }

    /**
     * Returns a new [Unit] with the same symbol but an inverted exponent.
     *
     * For example, if this unit is m², the result will be m⁻²;
     * if it's s⁻¹, the result will be s¹.
     */
    val inverted: Unit by lazy { Unit(symbol, exponent.inverted) }

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
     * Compares this [Unit] with another [Unit] for ordering.
     *
     * The comparison is based on the canonical order of unit symbols defined
     * in [CANONICAL_ORDER]. Units whose symbols appear earlier in the list
     * are considered "less" than those appearing later.
     *
     * Units whose symbols are not found in [CANONICAL_ORDER] are considered
     * greater than all known units and sorted at the end.
     *
     * Note that this comparison **does not** consider the exponent.
     *
     * @param other The [Unit] to compare against.
     * @return A negative integer, zero, or a positive integer as this unit is less than,
     *         equal to, or greater than the specified unit.
     */
    override fun compareTo(other: Unit): Int =
        CANONICAL_ORDER.indexOf(this).compareTo(CANONICAL_ORDER.indexOf(other))

    /**
     * Returns the string representation of the unit, including superscript exponent
     * if applicable (e.g., `"m²"`).
     */
    override fun toString(): String = representation

    /**
     * Compares this [Unit] to another object for structural equality.
     *
     * Two [Unit] instances are considered equal if and only if:
     * - They are of the same runtime type, and
     * - Their [symbol] and [exponent] properties are equal.
     *
     * @param other the object to compare with.
     * @return `true` if [other] is a [Unit] with the same [symbol] and [exponent], `false` otherwise.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Unit

        if (symbol != other.symbol) return false
        if (exponent != other.exponent) return false

        return true
    }

    /**
     * Returns a hash code value for this [Unit].
     *
     * The hash code is computed from the [symbol] and [exponent] properties,
     * ensuring consistency with [equals].
     *
     * @return an integer hash code for this [Unit].
     */
    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + exponent.hashCode()
        return result
    }
}

/**
 * Defines the canonical ordering of SI and derived units used throughout the system.
 *
 * This list ensures a consistent sequence when units are serialized, displayed,
 * or compared, regardless of the order in which they were originally defined.
 *
 * The order starts with common derived units (e.g., force, pressure, energy)
 * followed by base units (length, mass, time, etc.), and finally angular units.
 *
 * This ordering can be useful for:
 * - Formatting compound units in a predictable way
 * - Generating human-readable symbols
 * - Maintaining stable sorting in tests and output
 */
internal val CANONICAL_ORDER: List<Unit> = listOf(
    Newton.UNIT,
    Pascal.UNIT,
    Joule.UNIT,
    Watt.UNIT,
    Coulomb.UNIT,
    Volt.UNIT,
    Farad.UNIT,
    Ohm.UNIT,
    Siemens.UNIT,
    Weber.UNIT,
    Tesla.UNIT,
    Henry.UNIT,
    Lumen.UNIT,
    Lux.UNIT,
    Becquerel.UNIT,
    Gray.UNIT,
    Sievert.UNIT,
    Katal.UNIT,
    Metre.UNIT,
    Kilogram.UNIT,
    Second.UNIT,
    Ampere.UNIT,
    Kelvin.UNIT,
    Mol.UNIT,
    Candela.UNIT,
    Radian.UNIT,
    Steradian.UNIT
)
