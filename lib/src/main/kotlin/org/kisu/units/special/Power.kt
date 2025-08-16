package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **power** or **radiant flux**, measured in watts (W).
 *
 * One watt is equal to one joule per second (J/s), or kg·m²·s⁻³ in base SI units.
 * It quantifies the rate of energy transfer or conversion.
 *
 * Commonly used in electrical and mechanical contexts, as well as in radiometry and thermodynamics.
 *
 * This class expresses power as a combination of a [magnitude] and an [expression], supporting values such as
 * kilowatts (kW), megawatts (MW), or milliwatts (mW).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Power internal constructor(magnitude: BigDecimal, expression: Watt) :
    Measure<Watt, Power>(magnitude, expression, ::Power) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Watt(prefix))
}

/**
 * Represents the SI derived unit of power: **watt** (W).
 *
 * One watt is equal to one joule per second.
 *
 * SI definition: `W = m²·kg·s⁻³`.
 */
class Watt private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Watt>(prefix, overflow, unit, ::Watt) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for watt: "W". */
        internal val UNIT = Unit("W", 1)
    }
}
