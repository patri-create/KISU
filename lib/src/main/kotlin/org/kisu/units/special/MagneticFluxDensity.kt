package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import java.math.BigDecimal

/**
 * Represents the physical quantity of **magnetic flux density**, measured in teslas (T).
 *
 * One tesla is equal to one weber per square meter (Wb/m²), or kg·s⁻²·A⁻¹ in SI base units.
 * It measures the strength of magnetic fields.
 *
 * Teslas are used in electromagnetism, especially in the context of MRI machines, magnets, and inductors.
 *
 * This class expresses magnetic flux density as a combination of a [magnitude] and a [prefix], supporting values such
 * as milliteslas (mT), microteslas (µT), or kiloteslas (kT).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class MagneticFluxDensity internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, MagneticFluxDensity>(magnitude, expression, ::MagneticFluxDensity) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, SYMBOL))

    companion object {
        /** The SI symbol for magnetic flux density: "T" (tesla). */
        internal const val SYMBOL = "T"
    }
}
