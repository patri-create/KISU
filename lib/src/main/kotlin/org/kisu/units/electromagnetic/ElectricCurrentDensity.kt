package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Ampere
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre

/**
 * Represents the physical quantity of **electric current density**, measured in
 * [AmperePerSquareMetre].
 *
 * Electric current density quantifies how much electric current passes through a unit
 * cross-sectional area. It describes how concentrated current flow is inside a
 * conductor, plasma, or continuous medium.
 *
 * The associated unit representation is [AmperePerSquareMetre] (`A/m²`).
 */
class ElectricCurrentDensity(
    magnitude: Magnitude,
    expression: AmperePerSquareMetre
) : Measure<ElectricCurrentDensity.AmperePerSquareMetre, ElectricCurrentDensity>(
    magnitude = magnitude,
    expression = expression,
    create = ::ElectricCurrentDensity
) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, AmperePerSquareMetre(prefix))

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

    companion object {
        /**
         * Creates an [AmperePerSquareMetre] expression for **ampere per square metre** (`A/m²`).
         *
         * @param prefix Metric prefix applied to the ampere unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return An [AmperePerSquareMetre] expression for `A/m²`.
         */
        @Suppress("FunctionNaming")
        internal fun AmperePerSquareMetre(prefix: Metric = Metric.BASE): AmperePerSquareMetre =
            Quotient(Ampere(prefix), SquareMetre())
    }
}
