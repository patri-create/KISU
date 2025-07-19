package org.kisu.units.derived

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.Scalar
import java.math.BigDecimal

/**
 * Represents the physical quantity of **dose equivalent**, measured in sieverts (Sv).
 *
 * One sievert represents a dose of radiation equivalent in biological effect to one gray of X-rays or gamma rays.
 * It shares the same SI base units as the gray (m²·s⁻²) but accounts for biological effectiveness.
 *
 * Sieverts are used in radiation protection to quantify health risk.
 *
 * This class expresses dose equivalent as a combination of a [magnitude] and a [prefix], supporting values such as
 * millisieverts (mSv), microsieverts (µSv), or sieverts (Sv).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class DoseEquivalent internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, DoseEquivalent>(magnitude, expression, ::DoseEquivalent) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, SYMBOL))

    companion object {
        /** The SI symbol for dose equivalent: "Sv" (sievert). */
        internal const val SYMBOL = "Sv"
    }
}
