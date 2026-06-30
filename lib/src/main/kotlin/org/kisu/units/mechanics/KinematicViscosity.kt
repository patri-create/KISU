@file:Suppress("TooManyFunctions")

package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.mechanics.KinematicViscosity.Companion.SquareMetrePerSecond
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre

/**
 * Represents the physical quantity of **kinematic viscosity**, measured in
 * [SquareMetrePerSecond].
 *
 * Kinematic viscosity quantifies the diffusive tendency of momentum in a fluid by
 * relating dynamic viscosity to density. It is especially convenient in fluid flow
 * analysis because it appears directly in many transport equations.
 *
 * Typical examples include oil grading, hydraulic calculations, and atmospheric or
 * aerodynamic flow studies.
 *
 * The associated unit representation is [SquareMetrePerSecond] (`m²/s`).
 */
class KinematicViscosity(
    magnitude: Magnitude,
    expression: SquareMetrePerSecond
) : Measure<KinematicViscosity.SquareMetrePerSecond, KinematicViscosity>(
    magnitude = magnitude,
    expression = expression,
    create = ::KinematicViscosity
) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
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
         * Creates a [SquareMetrePerSecond] expression for **square metre per second** (`m²/s`).
         *
         * @param prefix Metric prefix applied to the square metre unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [SquareMetrePerSecond] expression for `m²/s`.
         */
        @Suppress("FunctionNaming")
        internal fun SquareMetrePerSecond(prefix: Metric = Metric.BASE): SquareMetrePerSecond =
            Quotient(SquareMetre(prefix), Second())
    }

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.electromagnetic.ElectronMobility
    ): org.kisu.units.special.ElectricPotential =
        org.kisu.units.special.ElectricPotential(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.ElectricPotential
    ): org.kisu.units.electromagnetic.ElectronMobility =
        org.kisu.units.electromagnetic.ElectronMobility(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.base.Time
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() * other.canonical.component1())
}
