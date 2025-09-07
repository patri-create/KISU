package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Ampere
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

/**
 * Represents **electric current density** in the SI system.
 *
 * Electric current density describes the amount of electric current flowing per unit
 * cross-sectional area of a conductor. It indicates how concentrated an electric current
 * is within a given area.
 *
 * Defined as:
 *
 *     J = I / A
 *
 * where:
 * - J is the electric current density,
 * - I is the electric current (A),
 * - A is the cross-sectional area (m²).
 *
 * The SI derived unit is **ampere per square metre (A/m²)**:
 *
 *     1 A/m² = 1 ampere / 1 square metre
 *
 * @constructor Creates a measure of electric current density with a given [magnitude]
 * in terms of the unit expression [AmperePerSquareMetre].
 */
class ElectricCurrentDensity(
    magnitude: BigDecimal,
    expression: AmperePerSquareMetre
) : Measure<ElectricCurrentDensity.AmperePerSquareMetre, ElectricCurrentDensity>(
    magnitude = magnitude,
    expression = expression,
    create = ::ElectricCurrentDensity
) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Ampere(prefix), SquareMetre()))

    /**
     * Represents the SI unit **ampere per square metre (A/m²)**.
     *
     * This unit measures **electric current density**, i.e., the amount of electric
     * current flowing per unit cross-sectional area.
     * It is defined as the [Quotient] of [Ampere] (electric current) and [SquareMetre] (area).
     *
     * Example usages include:
     * - Describing current flow in conductors
     * - Modeling electromagnetics and circuit behavior
     *
     * @see ElectricCurrentDensity for the physical quantity represented by this unit.
     */
    typealias AmperePerSquareMetre = Quotient<Ampere, SquareMetre>
}
