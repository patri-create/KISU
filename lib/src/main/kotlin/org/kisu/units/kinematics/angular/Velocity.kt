package org.kisu.units.kinematics.angular

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian
import java.math.BigDecimal

/**
 * Represents the physical quantity of **angular velocity**.
 *
 * Angular velocity quantifies the **rate of change of angular position** over time.
 * Its SI unit is the **radian per second (rad/s)**, represented here by [RadianPerSecond].
 *
 * Typical applications include:
 * - Rotational motion in machinery and robotics
 * - Vehicle and spacecraft rotational dynamics
 * - Physics simulations involving angular motion
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [Velocity] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([RadianPerSecond]).
 *
 * @property magnitude The numeric value of the angular velocity.
 * @property expression The unit expression of the angular velocity, always [RadianPerSecond].
 */
class Velocity(
    magnitude: BigDecimal,
    expression: RadianPerSecond
) : Measure<Velocity.RadianPerSecond, Velocity>(magnitude, expression, ::Velocity) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Radian(prefix), Second()))

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
}
