package org.kisu.units.derived

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
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
class Power internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
    Measure<Metric, Power>(magnitude, prefix, SYMBOL, ::Power) {

    companion object {
        /** The SI symbol for power: "W" (watt). */
        internal const val SYMBOL = "W"
    }
}
