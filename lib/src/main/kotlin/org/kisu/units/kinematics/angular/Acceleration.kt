package org.kisu.units.kinematics.angular

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.SecondSquared
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian
import java.math.BigDecimal

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
) : Measure<Acceleration.RadianPerSecondSquared, Acceleration>(magnitude, expression, ::Acceleration) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, RadianPerSecondSquared(prefix))

    /**
     * Represents the SI unit **radian per second squared (rad/s²)**.
     *
     * This unit is used to measure **angular acceleration**,
     * i.e., the rate of change of angular velocity with respect to time.
     * It is defined as the [Quotient] of [Radian] (angle) divided by [SecondSquared] (time²).
     *
     * Example usages include:
     * - Determining the angular acceleration of rotating machinery
     * - Analysing the dynamics of wheels, gears, or turbines
     * - Calculating rotational kinematics in physics and engineering
     *
     * @see Acceleration
     */
    typealias RadianPerSecondSquared = Quotient<Radian, SecondSquared>

    companion object {
        /**
         * Creates a measure of **radians per second squared** (rad/s²).
         *
         * This derived unit expresses angular acceleration —
         * how quickly an angular velocity (in radians per second) changes over time.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Radian] (angle) with the specified [prefix]
         *  - divided by a [SecondSquared] (time²)
         *
         * @param prefix Metric prefix to apply to the radian unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [Quotient] representing rad/s².
         */
        @Suppress("FunctionNaming")
        internal fun RadianPerSecondSquared(prefix: Metric = Metric.BASE): Quotient<Radian, SecondSquared> =
            Quotient(Radian(prefix), SecondSquared())
    }
}
