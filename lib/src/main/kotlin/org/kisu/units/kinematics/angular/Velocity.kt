package org.kisu.units.kinematics.angular

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian

/**
 * Represents the physical quantity of **angular velocity**, measured in
 * [RadianPerSecond].
 *
 * Angular velocity quantifies the rate at which angular position changes. It is the
 * standard descriptor of rotational speed in mechanics, robotics, and wave motion.
 *
 * Typical examples include wheel rotation, shaft speed, orbital spin, and actuator
 * motion.
 *
 * The associated unit representation is [RadianPerSecond] (`rad/s`).
 */
class Velocity(
    magnitude: Magnitude,
    expression: RadianPerSecond
) : Measure<Velocity.RadianPerSecond, Velocity>(magnitude, expression, ::Velocity) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, RadianPerSecond(prefix))

    /**
     * Represents the SI unit **radian per second (rad/s)**.
     *
     * This unit is used to measure **angular velocity**,
     * i.e., the rate of change of angular position with respect to time.
     * It is defined as the [Quotient] of [Radian] (angle) divided by [Second] (time).
     *
     * Example usages include:
     * - Measuring rotational speed of wheels, turbines, or motors
     * - Describing the angular velocity of planets or satellites
     * - Analysing the motion of rotating machinery in physics and engineering
     *
     * @see Velocity
     */
    typealias RadianPerSecond = Quotient<Radian, Second>

    companion object {
        /**
         * Creates a [RadianPerSecond] expression for **radian per second** (`rad/s`).
         *
         * @param prefix Metric prefix applied to the radian unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [RadianPerSecond] expression for `rad/s`.
         */
        @Suppress("FunctionNaming")
        internal fun RadianPerSecond(prefix: Metric = Metric.BASE): RadianPerSecond =
            Quotient(Radian(prefix), Second())
    }
}
