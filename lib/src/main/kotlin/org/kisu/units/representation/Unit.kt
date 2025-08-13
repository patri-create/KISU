package org.kisu.units.representation

import org.kisu.units.base.Amount
import org.kisu.units.base.Current
import org.kisu.units.base.Length
import org.kisu.units.base.LuminousIntensity
import org.kisu.units.base.Mass
import org.kisu.units.base.Temperature
import org.kisu.units.base.Time
import org.kisu.units.special.AbsorbedDose
import org.kisu.units.special.Capacitance
import org.kisu.units.special.CatalyticActivity
import org.kisu.units.special.Conductance
import org.kisu.units.special.DoseEquivalent
import org.kisu.units.special.ElectricCharge
import org.kisu.units.special.ElectricPotential
import org.kisu.units.special.Energy
import org.kisu.units.special.Force
import org.kisu.units.special.Illuminance
import org.kisu.units.special.Inductance
import org.kisu.units.special.LuminousFlux
import org.kisu.units.special.MagneticFlux
import org.kisu.units.special.MagneticFluxDensity
import org.kisu.units.special.PlaneAngle
import org.kisu.units.special.Power
import org.kisu.units.special.Pressure
import org.kisu.units.special.Radioactivity
import org.kisu.units.special.Resistance
import org.kisu.units.special.SolidAngle

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
     * Computes hash code based on the [symbol] only, ignoring the exponent.
     */
    override fun hashCode(): Int = symbol.hashCode()

    /**
     * Returns the string representation of the unit, including superscript exponent
     * if applicable (e.g., `"m²"`).
     */
    override fun toString(): String = representation
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
    Force.UNIT,
    Pressure.UNIT,
    Energy.UNIT,
    Power.UNIT,
    ElectricCharge.UNIT,
    ElectricPotential.UNIT,
    Capacitance.UNIT,
    Resistance.UNIT,
    Conductance.UNIT,
    MagneticFlux.UNIT,
    MagneticFluxDensity.UNIT,
    Inductance.UNIT,
    LuminousFlux.UNIT,
    Illuminance.UNIT,
    Radioactivity.UNIT,
    AbsorbedDose.UNIT,
    DoseEquivalent.UNIT,
    CatalyticActivity.UNIT,
    Length.UNIT,
    Mass.UNIT,
    Time.UNIT,
    Current.UNIT,
    Temperature.UNIT,
    Amount.UNIT,
    LuminousIntensity.UNIT,
    PlaneAngle.UNIT,
    SolidAngle.UNIT
)
