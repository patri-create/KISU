package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

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
class Force internal constructor(magnitude: BigDecimal, expression: Newton) :
    Measure<Newton, Force>(magnitude, expression, ::Force) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Newton(prefix))
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
