@file:Suppress("TooManyFunctions")

package org.kisu.units.kinematics.linear

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.SecondSixth
import org.kisu.units.kinematics.linear.Pop.Companion.MetrePerSecondSixth
import org.kisu.units.representation.Quotient

/**
 * Represents the physical quantity of **pop**, measured in [MetrePerSecondSixth].
 *
 * Pop is the sixth time derivative of position. It is a specialized high-order
 * kinematic quantity used in very smooth trajectory generation and theoretical motion
 * analysis.
 *
 * The associated unit representation is [MetrePerSecondSixth] (`m/s⁶`).
 */
class Pop internal constructor(
    magnitude: Magnitude,
    expression: MetrePerSecondSixth
) : Measure<Pop.MetrePerSecondSixth, Pop>(magnitude, expression, ::Pop) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, MetrePerSecondSixth(prefix))

    /**
     * Represents the SI unit **metre per second to the sixth power (m/s⁶)**.
     *
     * This unit is used to measure the **sixth time derivative of position**,
     * i.e., how rapidly the fifth derivative of displacement changes over time.
     * It is defined as the [Quotient] of [Metre] (length) divided by [SecondSixth] (time⁶).
     *
     * Example usages include:
     * - Very high-order motion analysis in physics or engineering simulations
     * - Advanced vibration studies or precision control systems
     * - Modelling complex dynamic systems requiring higher-order derivatives
     *
     * @see Pop
     */
    typealias MetrePerSecondSixth = Quotient<Metre, SecondSixth>

    companion object {
        /**
         * Creates a [MetrePerSecondSixth] expression for **metre per second sixth** (`m/s⁶`).
         *
         * @param prefix Metric prefix applied to the metre unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [MetrePerSecondSixth] expression for `m/s⁶`.
         */
        @Suppress("FunctionNaming")
        internal fun MetrePerSecondSixth(prefix: Metric = Metric.BASE): MetrePerSecondSixth =
            Quotient(Metre(prefix), SecondSixth())
    }

    // Dimension-aware arithmetic
    /**
     * Multiplies this [Pop] by [Time][org.kisu.units.base.Time],
     * yielding [Crackle][org.kisu.units.kinematics.linear.Crackle].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.linear.Crackle =
        org.kisu.units.kinematics.linear.Crackle(canonical.component1() * other.canonical.component1())
}
