package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre
import org.kisu.units.special.Watt
import java.math.BigDecimal

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
) : Measure<WattPerSquareMetre, HeatFluxDensity>(magnitude, expression, ::HeatFluxDensity) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Watt(prefix), SquareMetre()))
}
