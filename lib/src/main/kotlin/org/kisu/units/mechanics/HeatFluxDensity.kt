@file:Suppress("TooManyFunctions")

package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.mechanics.HeatFluxDensity.Companion.WattPerSquareMetre
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

    // Dimension-aware arithmetic
    /**
     * Divides this [HeatFluxDensity] by [TemperatureGradient][org.kisu.units.thermodynamics.TemperatureGradient],
     * yielding [ThermalConductivity][org.kisu.units.thermodynamics.ThermalConductivity].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.thermodynamics.TemperatureGradient
    ): org.kisu.units.thermodynamics.ThermalConductivity =
        org.kisu.units.thermodynamics.ThermalConductivity(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [HeatFluxDensity] by [ThermalConductivity][org.kisu.units.thermodynamics.ThermalConductivity],
     * yielding [TemperatureGradient][org.kisu.units.thermodynamics.TemperatureGradient].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.thermodynamics.ThermalConductivity
    ): org.kisu.units.thermodynamics.TemperatureGradient =
        org.kisu.units.thermodynamics.TemperatureGradient(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [HeatFluxDensity] by [Area][org.kisu.units.special.Area],
     * yielding [Power][org.kisu.units.special.Power].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.Area
    ): org.kisu.units.special.Power =
        org.kisu.units.special.Power(canonical.component1() * other.canonical.component1())
}
