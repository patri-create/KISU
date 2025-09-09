package org.kisu.units.kinematics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Hertz
import java.math.BigDecimal

/**
 * Represents the physical quantity of **frequency drift**, the rate of change of frequency over time.
 *
 * Frequency drift quantifies how quickly a frequency changes with respect to time.
 * Its SI unit is the **hertz per second (Hz/s)**, represented here by [HertzPerSecond].
 *
 * Typical applications include:
 * - Monitoring clock stability in electronics and timekeeping devices
 * - Signal processing and communication systems
 * - Oscillator or resonator performance analysis
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [FrequencyDrift] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([HertzPerSecond]).
 *
 * @property magnitude The numeric value of the frequency drift.
 * @property expression The unit expression of the frequency drift, always [HertzPerSecond].
 */
class FrequencyDrift internal constructor(
    magnitude: BigDecimal,
    expression: HertzPerSecond
) : Measure<FrequencyDrift.HertzPerSecond, FrequencyDrift>(magnitude, expression, ::FrequencyDrift) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Hertz(prefix), Second()))

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
}
