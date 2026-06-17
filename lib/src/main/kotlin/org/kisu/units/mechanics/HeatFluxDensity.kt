package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre
import org.kisu.units.special.Watt

/**
 * Represents the physical quantity of **heat flux density**, measured in
 * [WattPerSquareMetre].
 *
 * Heat flux density quantifies the rate at which thermal energy crosses a unit area. It
 * is one of the main quantities used to describe conduction, convection, and radiation
 * heat transfer.
 *
 * Typical examples include solar loading on a surface, heat loss through a wall, and
 * thermal management of electronics.
 *
 * The associated unit representation is [WattPerSquareMetre] (`W/m²`).
 */
class HeatFluxDensity(
    magnitude: Magnitude,
    expression: WattPerSquareMetre
) : Measure<HeatFluxDensity.WattPerSquareMetre, HeatFluxDensity>(magnitude, expression, ::HeatFluxDensity) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
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
         * Creates a [WattPerSquareMetre] expression for **watt per square metre** (`W/m²`).
         *
         * @param prefix Metric prefix applied to the watt unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [WattPerSquareMetre] expression for `W/m²`.
         */
        @Suppress("FunctionNaming")
        internal fun WattPerSquareMetre(prefix: Metric = Metric.BASE): WattPerSquareMetre =
            Quotient(Watt(prefix), SquareMetre())
    }
}
