@file:Suppress("TooManyFunctions")

package org.kisu.units.kinematics.angular

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.SecondSquared
import org.kisu.units.kinematics.angular.Acceleration.Companion.RadianPerSecondSquared
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian

/**
 * Represents the physical quantity of **angular acceleration**, measured in
 * [RadianPerSecondSquared].
 *
 * Angular acceleration quantifies how rapidly angular velocity changes. It is the
 * rotational counterpart of linear acceleration and is central to the dynamics of
 * motors, wheels, gears, and articulated systems.
 *
 * The associated unit representation is [RadianPerSecondSquared] (`rad/s²`).
 */
class Acceleration(
    magnitude: Magnitude,
    expression: RadianPerSecondSquared
) : Measure<Acceleration.RadianPerSecondSquared, Acceleration>(magnitude, expression, ::Acceleration) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
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
         * Creates a [RadianPerSecondSquared] expression for **radian per second squared** (`rad/s²`).
         *
         * @param prefix Metric prefix applied to the radian unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [RadianPerSecondSquared] expression for `rad/s²`.
         */
        @Suppress("FunctionNaming")
        internal fun RadianPerSecondSquared(prefix: Metric = Metric.BASE): RadianPerSecondSquared =
            Quotient(Radian(prefix), SecondSquared())
    }

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.angular.Jerk =
        org.kisu.units.kinematics.angular.Jerk(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.kinematics.angular.Jerk
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.angular.Velocity =
        org.kisu.units.kinematics.angular.Velocity(canonical.component1() * other.canonical.component1())
}
