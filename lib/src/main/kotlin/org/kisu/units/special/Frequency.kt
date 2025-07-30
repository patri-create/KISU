package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.Scalar
import java.math.BigDecimal

/**
 * Represents the physical quantity of **frequency**, measured in hertz (Hz).
 *
 * Frequency quantifies how often a repeating event occurs per unit time.
 * One hertz corresponds to one cycle per second.
 *
 * This unit is commonly used in physics, engineering, and signal processing to describe waveforms and oscillations.
 *
 * This class expresses frequency as a combination of a [magnitude] and a [prefix], supporting values such as
 * kilohertz (kHz), megahertz (MHz), or millihertz (mHz).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Frequency internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, Frequency>(magnitude, expression, ::Frequency) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, SYMBOL))

    companion object {
        /** The SI symbol for frequency: "Hz" (hertz). */
        internal const val SYMBOL = "Hz"
    }
}
