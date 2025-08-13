package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **absorbed dose** or **kerma**, measured in grays (Gy).
 *
 * One gray corresponds to the absorption of one joule of radiation energy by one kilogram of matter.
 * In SI base units, it is m²·s⁻².
 *
 * Grays are used in radiology, radiation therapy, and radiation protection.
 *
 * This class expresses dose as a combination of a [magnitude] and an [expression], supporting values such as
 * milligrays (mGy), centigrays (cGy), or kilograys (kGy).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class AbsorbedDose internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, AbsorbedDose>(magnitude, expression, ::AbsorbedDose) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, unit = UNIT))

    companion object {
        /** The SI symbol for absorbed dose: "Gy" (gray). */
        internal val UNIT = Unit("Gy", 1)
    }
}
