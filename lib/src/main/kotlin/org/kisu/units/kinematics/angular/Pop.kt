package org.kisu.units.kinematics.angular

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.SecondSixth
import org.kisu.units.kinematics.angular.Pop.Companion.RadianPerSecondSixth
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian

/**
 * Represents the physical quantity of **angular pop**, measured in
 * [RadianPerSecondSixth].
 *
 * Angular pop is the sixth time derivative of angular position. It is rarely needed in
 * everyday mechanics, but it appears in very high-order motion planning and analytical
 * treatments of smooth rotational trajectories.
 *
 * The associated unit representation is [RadianPerSecondSixth] (`rad/s⁶`).
 */
class Pop internal constructor(
    magnitude: Magnitude,
    expression: RadianPerSecondSixth
) : Measure<Pop.RadianPerSecondSixth, Pop>(magnitude, expression, ::Pop) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, RadianPerSecondSixth(prefix))

    /**
     * Represents the SI unit **radian per second to the sixth power (rad/s⁶)**.
     *
     * This unit is used to measure the **sixth time derivative of angular position**,
     * sometimes referred to as **angular pop** or higher-order angular rate change,
     * i.e., how rapidly the fifth derivative of angular position changes over time.
     * It is defined as the [Quotient] of [Radian] (angle) divided by [SecondSixth] (time⁶).
     *
     * Example usages include:
     * - Very high-order rotational motion analysis in robotics, aerospace, or vibration studies
     * - Advanced modelling of oscillatory systems with complex dynamics
     * - Control algorithms that incorporate higher-order derivatives for precision movement
     *
     * @see Pop
     */
    typealias RadianPerSecondSixth = Quotient<Radian, SecondSixth>

    companion object {
        /**
         * Creates a [RadianPerSecondSixth] expression for **radian per second sixth** (`rad/s⁶`).
         *
         * @param prefix Metric prefix applied to the radian unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [RadianPerSecondSixth] expression for `rad/s⁶`.
         */
        @Suppress("FunctionNaming")
        internal fun RadianPerSecondSixth(prefix: Metric = Metric.BASE): RadianPerSecondSixth =
            Quotient(Radian(prefix), SecondSixth())
    }
}
