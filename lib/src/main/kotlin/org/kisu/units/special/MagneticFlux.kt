package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **magnetic flux**, measured in webers (Wb).
 *
 * One weber is the magnetic flux that, linking a circuit of one turn, produces an electromotive force of one volt
 * when reduced to zero uniformly over one second. In SI base units, it is kg·m²·s⁻²·A⁻¹.
 *
 * Webers are used in electromagnetism to quantify magnetic flux through a surface.
 *
 * This class expresses magnetic flux as a combination of a [magnitude] and an [expression], supporting values such as
 * milliwwebers (mWb), microwebers (µWb), or kilowebers (kWb).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class MagneticFlux internal constructor(magnitude: BigDecimal, expression: Weber) :
    Measure<Weber, MagneticFlux>(magnitude, expression, ::MagneticFlux) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Weber(prefix))
}

/**
 * Represents the SI derived unit of magnetic flux: **weber** (Wb).
 *
 * One weber is the magnetic flux that, linking a circuit of one turn,
 * produces an electromotive force of one volt if it is reduced to zero
 * at a uniform rate in one second.
 *
 * SI definition: `Wb = m²·kg·s⁻²·A⁻¹`.
 */
class Weber private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Weber>(prefix, overflow, unit, ::Weber) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for weber: "Wb". */
        internal val UNIT = Unit("Wb", 1)
    }
}
