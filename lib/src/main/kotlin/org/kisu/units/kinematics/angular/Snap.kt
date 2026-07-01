@file:Suppress("TooManyFunctions")

package org.kisu.units.kinematics.angular

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.SecondFourth
import org.kisu.units.kinematics.angular.Snap.Companion.RadianPerSecondFourth
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian

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
    magnitude: Magnitude,
    expression: RadianPerSecondFourth
) : Measure<Snap.RadianPerSecondFourth, Snap>(magnitude, expression, ::Snap) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
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

    // Dimension-aware arithmetic
    /**
     * Divides this [Snap] by [Time][org.kisu.units.base.Time],
     * yielding [Crackle][org.kisu.units.kinematics.angular.Crackle].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.angular.Crackle =
        org.kisu.units.kinematics.angular.Crackle(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Snap] by [Crackle][org.kisu.units.kinematics.angular.Crackle],
     * yielding [Time][org.kisu.units.base.Time].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.kinematics.angular.Crackle
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [Snap] by [Time][org.kisu.units.base.Time],
     * yielding [Jerk][org.kisu.units.kinematics.angular.Jerk].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.angular.Jerk =
        org.kisu.units.kinematics.angular.Jerk(canonical.component1() * other.canonical.component1())
}
