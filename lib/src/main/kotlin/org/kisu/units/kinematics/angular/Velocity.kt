package org.kisu.units.kinematics.angular

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian
import java.math.BigDecimal

typealias RadianPerSecond = Quotient<Radian, Second>

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
) : Measure<RadianPerSecond, Velocity>(magnitude, expression, ::Velocity) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Radian(prefix), Second()))
}
