package org.kisu.units.kinematics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.kinematics.FrequencyDrift.Companion.HertzPerSecond
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Hertz

/**
 * Represents the physical quantity of **frequency drift**, measured in
 * [HertzPerSecond].
 *
 * Frequency drift quantifies how rapidly a frequency changes over time. It is useful
 * whenever an oscillator, resonator, or periodic process is not perfectly stable.
 *
 * Typical examples include clock stability analysis, communication carrier drift,
 * resonator aging, and control-system diagnostics.
 *
 * The associated unit representation is [HertzPerSecond] (`Hz/s`).
 */
class FrequencyDrift internal constructor(
    magnitude: Magnitude,
    expression: HertzPerSecond
) : Measure<FrequencyDrift.HertzPerSecond, FrequencyDrift>(magnitude, expression, ::FrequencyDrift) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, HertzPerSecond(prefix))

    /**
     * Represents the SI unit **hertz per second (Hz/s)**.
     *
     * This unit is used to measure **frequency change rate** or **frequency acceleration**,
     * i.e., how quickly the frequency of an oscillation or signal changes over time.
     * It is defined as the [Quotient] of [Hertz] (frequency) divided by [Second] (time).
     *
     * Example usages include:
     * - Characterising the rate of change of frequency in mechanical or electrical oscillators
     * - Describing chirp signals or frequency sweeps in signal processing
     * - Measuring acceleration of rotational speed in machinery
     *
     * @see FrequencyDrift
     */
    typealias HertzPerSecond = Quotient<Hertz, Second>

    companion object {
        /**
         * Creates a [HertzPerSecond] expression for **hertz per second** (`Hz/s`).
         *
         * @param prefix Metric prefix applied to the hertz unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [HertzPerSecond] expression for `Hz/s`.
         */
        @Suppress("FunctionNaming")
        internal fun HertzPerSecond(prefix: Metric = Metric.BASE): HertzPerSecond =
            Quotient(Hertz(prefix), Second())
    }
}
