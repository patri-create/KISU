@file:Suppress("TooManyFunctions")

package org.kisu.units.special

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents the physical quantity of **force**, measured in [Newton].
 *
 * Force quantifies an interaction capable of changing motion or deforming a body. It
 * is the language used to describe pushes, pulls, tension, weight, contact reactions,
 * and many field interactions in classical mechanics.
 *
 * Typical examples include the weight of an object, the thrust of a motor, the pull in
 * a cable, or the load applied to a structural element.
 *
 * The canonical SI unit is the [Newton] (`N`), often scaled to `mN` or `kN` depending
 * on the application.
 */
class Force internal constructor(magnitude: Magnitude, expression: Newton) :
    Measure<Newton, Force>(magnitude, expression, ::Force) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Newton(prefix))

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.mechanics.SurfaceTension =
        org.kisu.units.mechanics.SurfaceTension(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.kinematics.linear.Acceleration =
        org.kisu.units.kinematics.linear.Acceleration(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.Yank =
        org.kisu.units.kinematics.Yank(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.kinematics.Yank
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.kinematics.linear.Acceleration
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.SurfaceTension
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Area
    ): org.kisu.units.special.Pressure =
        org.kisu.units.special.Pressure(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Pressure
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.base.Time
    ): org.kisu.units.mechanics.Momentum =
        org.kisu.units.mechanics.Momentum(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.linear.Speed
    ): org.kisu.units.special.Power =
        org.kisu.units.special.Power(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.Compressibility
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the unit **newton** (`N`), used to express [Force].
 *
 * A newton quantifies force: the push or pull that changes motion or balances another
 * force. One newton is the force required to accelerate a one-kilogram mass by one
 * metre per second squared.
 *
 * This unit is used for mechanical loads, contact forces, weight near Earth's surface,
 * spring forces, and structural calculations.
 *
 * In unit form, `N = kg·m/s² = m·kg·s⁻²`.
 *
 * @see Force
 * @see Joule
 * @see Pascal
 */
class Newton private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Newton>(algebra, prefix, unit, ::Newton) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for newton: "N". */
        internal val UNIT = Unit("N", 1)
    }
}
