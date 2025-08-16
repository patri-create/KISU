package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **dose equivalent**, measured in sieverts (Sv).
 *
 * One sievert represents a dose of radiation equivalent in biological effect to one gray of X-rays or gamma rays.
 * It shares the same SI base units as the gray (m²·s⁻²) but accounts for biological effectiveness.
 *
 * Sieverts are used in radiation protection to quantify health risk.
 *
 * This class expresses dose equivalent as a combination of a [magnitude] and an [expression], supporting values such as
 * millisieverts (mSv), microsieverts (µSv), or sieverts (Sv).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class DoseEquivalent internal constructor(magnitude: BigDecimal, expression: Sievert) :
    Measure<Sievert, DoseEquivalent>(magnitude, expression, ::DoseEquivalent) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Sievert(prefix))
}

/**
 * Represents the SI derived unit of dose equivalent: **sievert** (Sv).
 *
 * One sievert is equal to one joule per kilogram.
 *
 * SI definition: `Sv = m²·s⁻²`.
 */
class Sievert private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Sievert>(prefix, overflow, unit, ::Sievert) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for sievert: "Sv". */
        internal val UNIT = Unit("Sv", 1)
    }
}
