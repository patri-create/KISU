package org.kisu.units.base

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.special.Frequency
import org.kisu.units.special.Radioactivity

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
 * The magnitude is stored using [Magnitude] to ensure high precision. Instances of this class are immutable
 * and validated to reflect physical reality.
 */
class Time internal constructor(magnitude: Magnitude, expression: Second) :
    Measure<Second, Time>(magnitude, expression, ::Time) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Second(prefix))

    val frequency: Frequency
        get() = Frequency(canonical.component1().inverted)

    val activity: Radioactivity
        get() = Radioactivity(canonical.component1().inverted)
}

/**
 * Represents the SI base unit of **time**.
 *
 * The second (s) is the standard unit for measuring duration.
 */
class Second private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Second>(algebra, prefix, unit, ::Second) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical SI symbol for time: "s". */
        internal val UNIT = Unit("s", 1)
    }
}
