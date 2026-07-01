@file:Suppress("TooManyFunctions")

package org.kisu.units.kinematics.linear

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.SecondFourth
import org.kisu.units.kinematics.linear.Snap.Companion.MetrePerSecondFourth
import org.kisu.units.representation.Quotient

/**
 * Represents the physical quantity of **snap**, measured in
 * [MetrePerSecondFourth].
 *
 * Snap is the time derivative of jerk. It appears in high-order motion design where the
 * smoothness of acceleration changes matters, such as robotics, precision stages, and
 * comfort-constrained vehicle motion.
 *
 * The associated unit representation is [MetrePerSecondFourth] (`m/s⁴`).
 */
class Snap(
    magnitude: Magnitude,
    expression: MetrePerSecondFourth
) : Measure<Snap.MetrePerSecondFourth, Snap>(magnitude, expression, ::Snap) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
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
         * Creates a [MetrePerSecondFourth] expression for **metre per second fourth** (`m/s⁴`).
         *
         * @param prefix Metric prefix applied to the metre unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [MetrePerSecondFourth] expression for `m/s⁴`.
         */
        @Suppress("FunctionNaming")
        internal fun MetrePerSecondFourth(prefix: Metric = Metric.BASE): MetrePerSecondFourth =
            Quotient(Metre(prefix), SecondFourth())
    }

    // Dimension-aware arithmetic
    /**
     * Divides this [Snap] by [Time][org.kisu.units.base.Time],
     * yielding [Crackle][org.kisu.units.kinematics.linear.Crackle].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.linear.Crackle =
        org.kisu.units.kinematics.linear.Crackle(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Snap] by [Crackle][org.kisu.units.kinematics.linear.Crackle],
     * yielding [Time][org.kisu.units.base.Time].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.kinematics.linear.Crackle
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [Snap] by [Time][org.kisu.units.base.Time],
     * yielding [Jerk][org.kisu.units.kinematics.linear.Jerk].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.linear.Jerk =
        org.kisu.units.kinematics.linear.Jerk(canonical.component1() * other.canonical.component1())
}
