package org.kisu.units.base

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
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
class Time internal constructor(magnitude: BigDecimal, expression: Second) :
    Measure<Second, Time>(magnitude, expression, ::Time) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Second(prefix))
}

/**
 * Represents the SI base unit of **time**.
 *
 * The second (s) is the standard unit for measuring duration.
 */
class Second private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Second>(prefix, overflow, unit, ::Second) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical SI symbol for time: "s". */
        internal val UNIT = Unit("s", 1)
    }
}
