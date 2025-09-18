package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

/**
 * Measure of kinematic viscosity expressed in [SquareMetrePerSecond].
 *
 * Kinematic viscosity quantifies the intrinsic resistance of a fluid to motion
 * under the influence of shear stress, normalized by its density.
 *
 * Common applications include:
 * - Fluid mechanics (laminar and turbulent flow analysis)
 * - Hydraulic engineering (pipeline and channel flow)
 * - Aerodynamics and meteorology (air viscosity, diffusion)
 *
 * @property magnitude Numerical value of the kinematic viscosity.
 * @property expression Unit of the kinematic viscosity, here [SquareMetrePerSecond].
 *
 * @see SquareMetrePerSecond
 */
class KinematicViscosity(
    magnitude: BigDecimal,
    expression: SquareMetrePerSecond
) : Measure<KinematicViscosity.SquareMetrePerSecond, KinematicViscosity>(
    magnitude = magnitude,
    expression = expression,
    create = ::KinematicViscosity
) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, SquareMetrePerSecond(prefix))

    /**
     * Unit of [KinematicViscosity].
     *
     * Represents the unit of **kinematic viscosity**, i.e., the physical quantity measuring
     * a fluid's resistance to flow relative to its density.
     *
     * Symbol: `m²/s`
     * SI: `m²·s⁻¹`
     *
     * @see KinematicViscosity
     */
    typealias SquareMetrePerSecond = Quotient<SquareMetre, Second>

    companion object {
        /**
         * Creates a measure of **square metres per second** (m²/s).
         *
         * This derived unit expresses **area flux** —
         * how much area is covered or transported per unit time, commonly used
         * in diffusion or fluid flow contexts.
         *
         * Internally this returns a [Quotient] of:
         *  - a [SquareMetre] (area) with the specified [prefix]
         *  - divided by a [Second] (time)
         *
         * @param prefix Metric prefix to apply to the square metre unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [SquareMetrePerSecond] representing m²/s.
         */
        @Suppress("FunctionNaming")
        internal fun SquareMetrePerSecond(prefix: Metric = Metric.BASE): SquareMetrePerSecond =
            Quotient(SquareMetre(prefix), Second())
    }
}
