package org.kisu.units.kinematics.angular

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.SecondFifth
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian
import java.math.BigDecimal

/**
 * Represents the physical quantity of **angular crackle**, the fifth derivative of angular position with respect to
 * time.
 *
 * Angular crackle quantifies how the **angular snap** (fourth derivative of angular position) changes over time.
 * Its SI unit is the **radian per second to the fifth (rad/s⁵)**, represented here by [RadianPerSecondFifth].
 *
 * Typical applications include:
 * - Advanced robotics and precise rotational motion planning
 * - High-fidelity physics simulations of rotational systems
 * - Systems where higher-order derivatives of angular motion are relevant for stability or smoothness
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [AngularCrackle] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([RadianPerSecondFifth]).
 *
 * @property magnitude The numeric value of the angular crackle.
 * @property expression The unit expression of the angular crackle, always [RadianPerSecondFifth].
 */
class Crackle internal constructor(
    magnitude: BigDecimal,
    expression: RadianPerSecondFifth
) : Measure<Crackle.RadianPerSecondFifth, Crackle>(magnitude, expression, ::Crackle) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
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
         * Creates a measure of **radians per second to the fifth power** (rad/s⁵).
         *
         * This derived unit expresses the fifth-order time derivative of angular position —
         * how quickly the fourth derivative of angular velocity changes over time.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Radian] (angle) with the specified [prefix]
         *  - divided by a [SecondFifth] (time⁵)
         *
         * @param prefix Metric prefix to apply to the radian unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [Quotient] representing rad/s⁵.
         */
        @Suppress("FunctionNaming")
        internal fun RadianPerSecondFifth(prefix: Metric = Metric.BASE): Quotient<Radian, SecondFifth> =
            Quotient(Radian(prefix), SecondFifth())
    }
}
