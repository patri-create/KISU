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
 * This class expresses power as a combination of a [magnitude] and a [prefix], supporting values such as
 * kilowatts (kW), megawatts (MW), or milliwatts (mW).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Power internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, Power>(magnitude, expression, ::Power) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, UNIT))

    companion object {
        /** The SI symbol for power: "W" (watt). */
        internal val UNIT = Unit("W", 1)
    }
}
