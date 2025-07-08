package org.kisu.units.base

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import java.math.BigDecimal

/**
 * Represents the physical quantity of **time**, measured in seconds (s).
 *
 * Time quantifies the duration of events or intervals. It is one of the fundamental SI base quantities
 * and is universally measured in **seconds**. This class allows precise modeling of durations such as
 * milliseconds (ms), microseconds (µs), or kiloseconds (ks), using metric prefixes.
 *
 * Time values must not be negative. In physical systems and real-world contexts, negative time has no
 * meaning — you cannot go back and create a duration with a negative length. Zero represents an
 * instantaneous or null duration, while positive values represent elapsed or measurable intervals.
 *
 * The magnitude is stored using [BigDecimal] to ensure high precision. Instances of this class are immutable
 * and validated to reflect physical reality.
 */
class Time internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
    Measure<Metric, Time>(magnitude, prefix, SYMBOL, ::Time) {

    companion object {
        /** The SI symbol for time: "s" (second). */
        internal const val SYMBOL = "s"
    }
}
