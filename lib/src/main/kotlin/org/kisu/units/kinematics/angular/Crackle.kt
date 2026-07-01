@file:Suppress("TooManyFunctions")

package org.kisu.units.kinematics.angular

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.SecondFifth
import org.kisu.units.kinematics.angular.Crackle.Companion.RadianPerSecondFifth
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian

/**
 * Represents the physical quantity of **angular crackle**, measured in
 * [RadianPerSecondFifth].
 *
 * Angular crackle is the fifth time derivative of angular position. It describes how
 * angular snap changes and is mainly relevant in very high-order trajectory design and
 * smoothness-constrained rotational motion.
 *
 * The associated unit representation is [RadianPerSecondFifth] (`rad/s⁵`).
 */
class Crackle internal constructor(
    magnitude: Magnitude,
    expression: RadianPerSecondFifth
) : Measure<Crackle.RadianPerSecondFifth, Crackle>(magnitude, expression, ::Crackle) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, RadianPerSecondFifth(prefix))

    /**
     * Represents the SI unit **radian per second to the fifth power (rad/s⁵)**.
     *
     * This unit is used to measure the **fifth time derivative of angular position**,
     * sometimes called **angular crackle** or higher-order angular rate change,
     * i.e., how rapidly angular jerk or snap itself changes over time.
     * It is defined as the [Quotient] of [Radian] (angle) divided by [SecondFifth] (time⁵).
     *
     * Example usages include:
     * - High-order rotational motion analysis in robotics or aerospace
     * - Modelling vibration or oscillatory systems with complex dynamics
     * - Advanced control systems requiring precise higher-order derivatives
     *
     * @see Crackle
     */
    typealias RadianPerSecondFifth = Quotient<Radian, SecondFifth>

    companion object {
        /**
         * Creates a [RadianPerSecondFifth] expression for **radian per second fifth** (`rad/s⁵`).
         *
         * @param prefix Metric prefix applied to the radian unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [RadianPerSecondFifth] expression for `rad/s⁵`.
         */
        @Suppress("FunctionNaming")
        internal fun RadianPerSecondFifth(prefix: Metric = Metric.BASE): RadianPerSecondFifth =
            Quotient(Radian(prefix), SecondFifth())
    }

    // Dimension-aware arithmetic
    /**
     * Divides this [Crackle] by [Time][org.kisu.units.base.Time],
     * yielding [Pop][org.kisu.units.kinematics.angular.Pop].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.angular.Pop =
        org.kisu.units.kinematics.angular.Pop(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Crackle] by [Pop][org.kisu.units.kinematics.angular.Pop],
     * yielding [Time][org.kisu.units.base.Time].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.kinematics.angular.Pop
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [Crackle] by [Time][org.kisu.units.base.Time],
     * yielding [Snap][org.kisu.units.kinematics.angular.Snap].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.angular.Snap =
        org.kisu.units.kinematics.angular.Snap(canonical.component1() * other.canonical.component1())
}
