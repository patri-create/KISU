package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import java.math.BigDecimal

/**
 * Measure of fuel efficiency expressed in [MetrePerCubicMetre].
 *
 * Fuel efficiency quantifies how far a vehicle or engine can travel for a given
 * volume of fuel consumed.
 *
 * Common applications include:
 * - Automotive engineering (vehicle efficiency ratings)
 * - Engine performance evaluation
 * - Environmental impact assessment (fuel consumption per distance)
 *
 * @property magnitude Numerical value of the fuel efficiency.
 * @property expression Unit of the fuel efficiency, here [MetrePerCubicMetre].
 *
 * @see MetrePerCubicMetre
 */
class FuelEfficiency(
    magnitude: BigDecimal,
    expression: MetrePerCubicMetre
) : Measure<FuelEfficiency.MetrePerCubicMetre, FuelEfficiency>(magnitude, expression, ::FuelEfficiency) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, MetrePerCubicMetre(prefix))

    /**
     * Unit of [FuelEfficiency].
     *
     * Represents the unit of **fuel efficiency**, i.e., the physical quantity measuring
     * distance traveled per unit volume of fuel consumed.
     *
     * Symbol: `m/m³`
     * SI: `m⁻²`
     *
     * @see FuelEfficiency
     */
    typealias MetrePerCubicMetre = Quotient<Metre, CubicMetre>

    companion object {
        @Suppress("FunctionNaming")
        internal fun MetrePerCubicMetre(prefix: Metric = Metric.BASE): MetrePerCubicMetre =
            Quotient(Metre(prefix), CubicMetre())
    }
}
