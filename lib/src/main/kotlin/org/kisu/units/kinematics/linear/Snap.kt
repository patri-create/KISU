package org.kisu.units.kinematics.linear

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.SecondFourth
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

/**
 * Represents the physical quantity of **snap**, the fourth derivative of position with respect to time.
 *
 * Snap quantifies how the **jerk** (third derivative of position) changes over time.
 * Its SI unit is the **metre per second to the fourth (m/s⁴)**, represented here by [MetrePerSecondFourth].
 *
 * Snap is mainly used in advanced kinematics and precise motion analysis, such as:
 * - Robotics and trajectory planning, to ensure smooth higher-order motion profiles
 * - Physics simulations requiring accurate modeling of position derivatives beyond acceleration
 * - Systems where control over the change of jerk is necessary for stability or comfort
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [Snap] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([MetrePerSecondFourth]).
 *
 * @property magnitude The numeric value of the snap.
 * @property expression The unit expression of the snap, always [MetrePerSecondFourth].
 */
class Snap(
    magnitude: BigDecimal,
    expression: MetrePerSecondFourth
) : Measure<Snap.MetrePerSecondFourth, Snap>(magnitude, expression, ::Snap) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, MetrePerSecondFourth(prefix))

    /**
     * Represents the SI unit **metre per second to the fourth power (m/s⁴)**.
     *
     * This unit is used to measure the **fourth time derivative of position**,
     * commonly called **snap** in linear motion,
     * i.e., the rate of change of jerk with respect to time.
     * It is defined as the [Quotient] of [Metre] (length) divided by [SecondFourth] (time⁴).
     *
     * Example usages include:
     * - Analysing high-order motion in robotics or mechanical systems
     * - Designing smooth motion profiles in automation or vehicle dynamics
     * - Modelling dynamic systems with rapidly changing acceleration
     *
     * @see Snap
     */
    typealias MetrePerSecondFourth = Quotient<Metre, SecondFourth>

    companion object {
        /**
         * Creates a measure of **metres per second to the fourth power** (m/s⁴).
         *
         * This derived unit expresses the fourth-order time derivative of linear position —
         * often called **snap**, i.e., the rate of change of jerk.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Metre] (length) with the specified [prefix]
         *  - divided by a [SecondFourth] (time⁴)
         *
         * @param prefix Metric prefix to apply to the metre unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [Quotient] representing m/s⁴.
         */
        @Suppress("FunctionNaming")
        internal fun MetrePerSecondFourth(prefix: Metric = Metric.BASE): Quotient<Metre, SecondFourth> =
            Quotient(Metre(prefix), SecondFourth())
    }
}
