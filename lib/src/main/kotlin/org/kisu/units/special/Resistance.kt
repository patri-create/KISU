package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **electrical resistance**, measured in ohms (Ω).
 *
 * One ohm is the resistance between two points of a conductor when a constant potential difference of one volt,
 * applied to these points, produces a current of one ampere.
 * In SI base units, it is kg·m²·s⁻³·A⁻².
 *
 * Ohms are used in electrical and electronic systems to describe resistance and impedance.
 *
 * This class expresses resistance as a combination of a [magnitude] and a [prefix], supporting values such as
 * milliohms (mΩ), kiloohms (kΩ), or megaohms (MΩ).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Resistance internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, Resistance>(magnitude, expression, ::Resistance) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, unit = UNIT))

    companion object {
        /** The SI symbol for electrical resistance: "Ω" (ohm). */
        internal val UNIT = Unit("Ω", 1)
    }
}
