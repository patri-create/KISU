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
    /**
     * Divides this [Force] by [Length][org.kisu.units.base.Length],
     * yielding [SurfaceTension][org.kisu.units.mechanics.SurfaceTension].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.mechanics.SurfaceTension =
        org.kisu.units.mechanics.SurfaceTension(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Force] by [Mass][org.kisu.units.base.Mass],
     * yielding [Acceleration][org.kisu.units.kinematics.linear.Acceleration].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.kinematics.linear.Acceleration =
        org.kisu.units.kinematics.linear.Acceleration(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Force] by [Time][org.kisu.units.base.Time],
     * yielding [Yank][org.kisu.units.kinematics.Yank].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.Yank =
        org.kisu.units.kinematics.Yank(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Force] by [Yank][org.kisu.units.kinematics.Yank],
     * yielding [Time][org.kisu.units.base.Time].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.kinematics.Yank
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Force] by [Acceleration][org.kisu.units.kinematics.linear.Acceleration],
     * yielding [Mass][org.kisu.units.base.Mass].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.kinematics.linear.Acceleration
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Force] by [SurfaceTension][org.kisu.units.mechanics.SurfaceTension],
     * yielding [Length][org.kisu.units.base.Length].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.mechanics.SurfaceTension
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Force] by [Area][org.kisu.units.special.Area],
     * yielding [Pressure][org.kisu.units.special.Pressure].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Area
    ): org.kisu.units.special.Pressure =
        org.kisu.units.special.Pressure(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Force] by [Pressure][org.kisu.units.special.Pressure],
     * yielding [Area][org.kisu.units.special.Area].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Pressure
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [Force] by [Length][org.kisu.units.base.Length],
     * yielding [Energy][org.kisu.units.special.Energy].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Force] by [Time][org.kisu.units.base.Time],
     * yielding [Momentum][org.kisu.units.mechanics.Momentum].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Time
    ): org.kisu.units.mechanics.Momentum =
        org.kisu.units.mechanics.Momentum(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Force] by [Speed][org.kisu.units.kinematics.linear.Speed],
     * yielding [Power][org.kisu.units.special.Power].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.kinematics.linear.Speed
    ): org.kisu.units.special.Power =
        org.kisu.units.special.Power(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Force] by [Compressibility][org.kisu.units.mechanics.Compressibility],
     * yielding [Area][org.kisu.units.special.Area].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
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
