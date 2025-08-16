package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **magnetic flux density**, measured in teslas (T).
 *
 * One tesla is equal to one weber per square meter (Wb/m²), or kg·s⁻²·A⁻¹ in SI base units.
 * It measures the strength of magnetic fields.
 *
 * Teslas are used in electromagnetism, especially in the context of MRI machines, magnets, and inductors.
 *
 * This class expresses magnetic flux density as a combination of a [magnitude] and an [expression], supporting values
 * such as milliteslas (mT), microteslas (µT), or kiloteslas (kT).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class MagneticFluxDensity internal constructor(magnitude: BigDecimal, expression: Tesla) :
    Measure<Tesla, MagneticFluxDensity>(magnitude, expression, ::MagneticFluxDensity) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Tesla(prefix))
}

/**
 * Represents the SI derived unit of magnetic flux density: **tesla** (T).
 *
 * One tesla is equal to one weber per square metre.
 *
 * SI definition: `T = m⁻²·kg·s⁻²·A⁻¹`.
 */
class Tesla private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Tesla>(prefix, overflow, unit, ::Tesla) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for tesla: "T". */
        internal val UNIT = Unit("T", 1)
    }
}
