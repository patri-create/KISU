package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **frequency**, measured in hertz (Hz).
 *
 * Frequency quantifies how often a repeating event occurs per unit time.
 * One hertz corresponds to one cycle per second.
 *
 * This unit is commonly used in physics, engineering, and signal processing to describe waveforms and oscillations.
 *
 * This class expresses frequency as a combination of a [magnitude] and an [expression], supporting values such as
 * kilohertz (kHz), megahertz (MHz), or millihertz (mHz).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Frequency internal constructor(magnitude: BigDecimal, expression: Hertz) :
    Measure<Hertz, Frequency>(magnitude, expression, ::Frequency) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Hertz(prefix))
}

/**
 * Represents the SI derived unit of frequency: **hertz** (Hz).
 *
 * One hertz is one event or cycle per second.
 *
 * SI definition: `Hz = s⁻¹`.
 */
class Hertz private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Hertz>(prefix, overflow, unit, ::Hertz) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for hertz: "Hz". */
        internal val UNIT = Unit("Hz", 1)
    }
}
