package org.kisu.units.derived

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import java.math.BigDecimal

/**
 * Represents the physical quantity of **pressure** or **stress**, measured in pascals (Pa).
 *
 * One pascal equals one newton per square meter (N/m²), which is kg·m⁻¹·s⁻² in SI base units.
 * It is used to quantify internal pressure, stress, Young's modulus, and tensile strength.
 *
 * This class expresses pressure as a combination of a [magnitude] and a [prefix], supporting values such as
 * kilopascals (kPa), megapascals (MPa), or hectopascals (hPa).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Pressure internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
    Measure<Metric, Pressure>(magnitude, prefix, SYMBOL, ::Pressure) {

    companion object {
        /** The SI symbol for pressure or stress: "Pa" (pascal). */
        internal const val SYMBOL = "Pa"
    }
}
