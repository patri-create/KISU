package org.kisu.units.kinematics.linear

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.SecondCubed
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

/**
 * Represents the physical quantity of **jerk**, measured in
 * [MetrePerSecondCubed].
 *
 * Jerk is the time derivative of acceleration. It quantifies how abruptly acceleration
 * changes and is important in ride comfort, actuator control, vibration analysis, and
 * trajectory smoothing.
 *
 * The associated unit representation is [MetrePerSecondCubed] (`m/s³`).
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
         * Creates a [MetrePerSecondCubed] expression for **metre per second cubed** (`m/s³`).
         *
         * @param prefix Metric prefix applied to the metre unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [MetrePerSecondCubed] expression for `m/s³`.
         */
        @Suppress("FunctionNaming")
        internal fun MetrePerSecondCubed(prefix: Metric = Metric.BASE): MetrePerSecondCubed =
            Quotient(Metre(prefix), SecondCubed())
    }
}
