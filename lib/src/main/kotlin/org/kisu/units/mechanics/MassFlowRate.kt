package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.base.Second
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

/**
 * Measure of mass flow rate expressed in [KilogramPerSecond].
 *
 * Mass flow rate quantifies how much mass passes through a given surface or section per unit time.
 *
 * Common applications include:
 * - Fluid and gas transport systems (pipes, ducts)
 * - Chemical and process engineering (reactor feed rates)
 * - Environmental studies (mass flux in rivers or air currents)
 *
 * @property magnitude Numerical value of the mass flow rate.
 * @property expression Unit of the mass flow rate, here [KilogramPerSecond].
 *
 * @see KilogramPerSecond
 */
class MassFlowRate(
    magnitude: BigDecimal,
    expression: KilogramPerSecond
) : Measure<MassFlowRate.KilogramPerSecond, MassFlowRate>(magnitude, expression, ::MassFlowRate) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, KilogramPerSecond(prefix))

    /**
     * Unit of [MassFlowRate].
     *
     * Represents the unit of **mass flow rate**, i.e., the physical quantity measuring
     * mass transported per unit time.
     *
     * Symbol: `kg/s`
     * SI: `kg·s⁻¹`
     *
     * @see MassFlowRate
     */
    typealias KilogramPerSecond = Quotient<Kilogram, Second>

    companion object {
        /**
         * Creates a measure of **kilograms per second** (kg/s).
         *
         * This derived unit expresses **mass flow rate** —
         * how much mass passes through a point or system per unit time.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Kilogram] (mass) with the specified [prefix]
         *  - divided by a [Second] (time)
         *
         * @param prefix Metric prefix to apply to the kilogram unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [KilogramPerSecond] representing kg/s.
         */
        @Suppress("FunctionNaming")
        internal fun KilogramPerSecond(prefix: Metric = Metric.BASE): KilogramPerSecond =
            Quotient(Kilogram(prefix), Second())
    }
}
