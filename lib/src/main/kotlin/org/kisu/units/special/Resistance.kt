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
 * This class expresses resistance as a combination of a [magnitude] and an [expression], supporting values such as
 * milliohms (mΩ), kiloohms (kΩ), or megaohms (MΩ).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Resistance internal constructor(magnitude: BigDecimal, expression: Ohm) :
    Measure<Ohm, Resistance>(magnitude, expression, ::Resistance) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Ohm(prefix))
}

/**
 * Represents the SI derived unit of electrical resistance: **ohm** (Ω).
 *
 * One ohm is equal to one volt per ampere.
 *
 * SI definition: `Ω = m²·kg·s⁻³·A⁻²`.
 */
class Ohm private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Ohm>(prefix, overflow, unit, ::Ohm) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for ohm: "Ω". */
        internal val UNIT = Unit("Ω", 1)
    }
}
