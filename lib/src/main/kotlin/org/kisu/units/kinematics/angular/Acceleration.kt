package org.kisu.units.kinematics.angular

import org.kisu.units.Measure
import org.kisu.units.base.SecondSquared
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian
import java.math.BigDecimal

typealias RadianPerSecondSquared = Quotient<Radian, SecondSquared>

/**
 * Represents the physical quantity of **angular acceleration**.
 *
 * Angular acceleration quantifies the **rate of change of angular velocity** over time.
 * Its SI unit is the **radian per second squared (rad/s²)**, represented here by [RadianPerSecondSquared].
 *
 * Typical applications include:
 * - Rotational dynamics of machinery and engines
 * - Robotics and control systems for precise angular motion
 * - Physics simulations involving rotational kinematics
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [AngularAcceleration] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([RadianPerSecondSquared]).
 *
 * @property magnitude The numeric value of the angular acceleration.
 * @property expression The unit expression of the angular acceleration, always [RadianPerSecondSquared].
 */
class Acceleration(
    magnitude: BigDecimal,
    expression: RadianPerSecondSquared
) : Measure<RadianPerSecondSquared, Acceleration>(magnitude, expression, ::Acceleration)
