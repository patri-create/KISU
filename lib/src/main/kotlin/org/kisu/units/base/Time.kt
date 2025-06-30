package org.kisu.units.base

import org.kisu.negative
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.exceptions.NegativeTime
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
class Time private constructor(magnitude: BigDecimal, prefix: Metric) :
    Measure<Metric, Time>(magnitude, prefix, SYMBOL, ::invoke) {

    companion object {
        /** The SI symbol for time: "s" (second). */
        private const val SYMBOL = "s"

        /**
         * Creates a new [Time] quantity with the given [magnitude] and [prefix].
         *
         * The [magnitude] must be zero or positive. Negative time is not allowed because it would imply a
         * backward duration, which is not valid when modeling time as an interval or duration.
         *
         * @param magnitude The numeric value of the time quantity.
         * @param prefix The metric prefix to apply (e.g., m, µ, k).
         * @return A validated [Time] instance.
         * @throws NegativeTime if the magnitude is less than zero.
         */
        operator fun invoke(
            magnitude: BigDecimal,
            prefix: Metric,
        ): Time {
            if (magnitude.negative) {
                throw NegativeTime(magnitude, prefix, SYMBOL)
            }
            return Time(magnitude, prefix)
        }
    }
}
