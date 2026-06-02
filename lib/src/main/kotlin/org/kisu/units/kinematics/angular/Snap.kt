package org.kisu.units.kinematics.angular

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.SecondFourth
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian
import java.math.BigDecimal

/**
 * Represents the physical quantity of **angular snap**, measured in
 * [RadianPerSecondFourth].
 *
 * Angular snap is the time derivative of angular jerk. It describes how rotational
 * jerk itself changes and is mainly useful in advanced control, trajectory smoothing,
 * and high-fidelity rotational simulation.
 *
 * The associated unit representation is [RadianPerSecondFourth] (`rad/s⁴`).
 */
class Snap(
    magnitude: BigDecimal,
    expression: RadianPerSecondFourth
) : Measure<Snap.RadianPerSecondFourth, Snap>(magnitude, expression, ::Snap) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, RadianPerSecondFourth(prefix))

    /**
     * Represents the SI unit **radian per second to the fourth power (rad/s⁴)**.
     *
     * This unit is used to measure the **fourth time derivative of angular position**,
     * sometimes called **angular snap**,
     * i.e., the rate of change of angular jerk with respect to time.
     * It is defined as the [Quotient] of [Radian] (angle) divided by [SecondFourth] (time⁴).
     *
     * Example usages include:
     * - Analysing high-order rotational motion in robotics or aerospace
     * - Modelling dynamic systems where angular acceleration changes rapidly
     * - Designing precision control systems requiring higher-order derivatives
     *
     * @see Snap
     */
    typealias RadianPerSecondFourth = Quotient<Radian, SecondFourth>

    companion object {
        /**
         * Creates a [RadianPerSecondFourth] expression for **radian per second fourth** (`rad/s⁴`).
         *
         * @param prefix Metric prefix applied to the radian unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [RadianPerSecondFourth] expression for `rad/s⁴`.
         */
        @Suppress("FunctionNaming")
        internal fun RadianPerSecondFourth(prefix: Metric = Metric.BASE): RadianPerSecondFourth =
            Quotient(Radian(prefix), SecondFourth())
    }
}
