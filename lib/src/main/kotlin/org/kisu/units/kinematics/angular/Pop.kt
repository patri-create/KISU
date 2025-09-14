package org.kisu.units.kinematics.angular

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.SecondSixth
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian
import java.math.BigDecimal

/**
 * Represents the physical quantity of **angular pop**, the sixth derivative of angular position with respect to time.
 *
 * Angular pop quantifies how the **angular crackle** (fifth derivative of angular position) changes over time.
 * Its SI unit is the **radian per second to the sixth (rad/s⁶)**, represented here by [RadianPerSecondSixth].
 *
 * Typical applications include:
 * - Advanced robotics for precise rotational trajectory planning
 * - High-fidelity rotational physics simulations
 * - Systems requiring control over higher-order derivatives of angular motion for smoothness or stability
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [AngularPop] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([RadianPerSecondSixth]).
 *
 * @property magnitude The numeric value of the angular pop.
 * @property expression The unit expression of the angular pop, always [RadianPerSecondSixth].
 */
class Pop internal constructor(
    magnitude: BigDecimal,
    expression: RadianPerSecondSixth
) : Measure<Pop.RadianPerSecondSixth, Pop>(magnitude, expression, ::Pop) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
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
         * Creates a measure of **radians per second to the sixth power** (rad/s⁶).
         *
         * This derived unit expresses the sixth-order time derivative of angular position —
         * sometimes informally called a higher-order “angular snap” or “angular crackle”,
         * i.e. the rate of change of the fifth-order derivative.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Radian] (angle) with the specified [prefix]
         *  - divided by a [SecondSixth] (time⁶)
         *
         * @param prefix Metric prefix to apply to the radian unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [Quotient] representing rad/s⁶.
         */
        @Suppress("FunctionNaming")
        internal fun RadianPerSecondSixth(prefix: Metric = Metric.BASE): Quotient<Radian, SecondSixth> =
            Quotient(Radian(prefix), SecondSixth())
    }
}
