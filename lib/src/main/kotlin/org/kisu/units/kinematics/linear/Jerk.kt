package org.kisu.units.kinematics.linear

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.SecondCubed
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

/**
 * Represents the physical quantity of **jerk**, the third derivative of position with respect to time.
 *
 * Jerk quantifies how the **acceleration** changes over time.
 * Its SI unit is the **metre per second cubed (m/s³)**, represented here by [MetrePerSecondCubed].
 *
 * Typical applications include:
 * - Motion control and robotics, to ensure smooth acceleration profiles
 * - Vehicle dynamics analysis
 * - Physics simulations that require higher-order derivatives of position
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [Jerk] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([MetrePerSecondCubed]).
 *
 * @property magnitude The numeric value of the jerk.
 * @property expression The unit expression of the jerk, always [MetrePerSecondCubed].
 */
class Jerk(
    magnitude: BigDecimal,
    expression: MetrePerSecondCubed
) : Measure<Jerk.MetrePerSecondCubed, Jerk>(magnitude, expression, ::Jerk) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, MetrePerSecondCubed(prefix))

    /**
     * Represents the SI unit **metre per second cubed (m/s³)**.
     *
     * This unit is used to measure the **third time derivative of position**,
     * commonly called **jerk** in linear motion,
     * i.e., the rate of change of acceleration with respect to time.
     * It is defined as the [Quotient] of [Metre] (length) divided by [SecondCubed] (time³).
     *
     * Example usages include:
     * - Analysing rapid changes in acceleration in vehicles or machinery
     * - Designing smooth motion profiles in robotics or automation
     * - Studying vibrations or dynamic response in mechanical systems
     *
     * @see Jerk
     */
    typealias MetrePerSecondCubed = Quotient<Metre, SecondCubed>

    companion object {
        /**
         * Creates a measure of **metres per second cubed** (m/s³).
         *
         * This derived unit expresses the third-order time derivative of linear position —
         * also known as **jerk**, i.e., the rate of change of acceleration.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Metre] (length) with the specified [prefix]
         *  - divided by a [SecondCubed] (time³)
         *
         * @param prefix Metric prefix to apply to the metre unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [Quotient] representing m/s³.
         */
        @Suppress("FunctionNaming")
        internal fun MetrePerSecondCubed(prefix: Metric = Metric.BASE): Quotient<Metre, SecondCubed> =
            Quotient(Metre(prefix), SecondCubed())
    }
}
