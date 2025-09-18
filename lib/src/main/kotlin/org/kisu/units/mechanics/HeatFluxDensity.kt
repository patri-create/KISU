package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre
import org.kisu.units.special.Watt
import java.math.BigDecimal

/**
 * Measure of heat flux density expressed in [WattPerSquareMetre].
 *
 * Heat flux density quantifies the rate of heat transfer across a unit area.
 *
 * Common applications include:
 * - Heat transfer engineering (conduction, convection, radiation)
 * - Thermal management of systems and devices
 * - Climate and energy studies (solar radiation, building heat loss)
 *
 * @property magnitude Numerical value of the heat flux density.
 * @property expression Unit of the heat flux density, here [WattPerSquareMetre].
 *
 * @see WattPerSquareMetre
 */
class HeatFluxDensity(
    magnitude: BigDecimal,
    expression: WattPerSquareMetre
) : Measure<HeatFluxDensity.WattPerSquareMetre, HeatFluxDensity>(magnitude, expression, ::HeatFluxDensity) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, WattPerSquareMetre(prefix))

    /**
     * Unit of [HeatFluxDensity].
     *
     * Represents the unit of **heat flux density**, i.e., the physical quantity measuring
     * heat transfer per unit area per unit time.
     *
     * Symbol: `W/m²`
     * SI: `kg·s⁻³`
     *
     * @see HeatFluxDensity
     */
    typealias WattPerSquareMetre = Quotient<Watt, SquareMetre>

    companion object {
        /**
         * Creates a measure of **watts per square metre** (W/m²).
         *
         * This derived unit expresses **power flux density** —
         * how much power (energy per unit time) passes through a unit area.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Watt] (power) with the specified [prefix]
         *  - divided by a [SquareMetre] (area)
         *
         * @param prefix Metric prefix to apply to the watt unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [WattPerSquareMetre] representing W/m².
         */
        @Suppress("FunctionNaming")
        internal fun WattPerSquareMetre(prefix: Metric = Metric.BASE): WattPerSquareMetre =
            Quotient(Watt(prefix), SquareMetre())
    }
}
