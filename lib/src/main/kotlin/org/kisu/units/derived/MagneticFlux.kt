package org.kisu.units.derived

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import java.math.BigDecimal

/**
 * Represents the physical quantity of **magnetic flux**, measured in webers (Wb).
 *
 * One weber is the magnetic flux that, linking a circuit of one turn, produces an electromotive force of one volt
 * when reduced to zero uniformly over one second. In SI base units, it is kg·m²·s⁻²·A⁻¹.
 *
 * Webers are used in electromagnetism to quantify magnetic flux through a surface.
 *
 * This class expresses magnetic flux as a combination of a [magnitude] and a [prefix], supporting values such as
 * milliwwebers (mWb), microwebers (µWb), or kilowebers (kWb).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class MagneticFlux internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
    Measure<Metric, MagneticFlux>(magnitude, prefix, SYMBOL, ::MagneticFlux) {

    companion object {
        /** The SI symbol for magnetic flux: "Wb" (weber). */
        internal const val SYMBOL = "Wb"
    }
}
