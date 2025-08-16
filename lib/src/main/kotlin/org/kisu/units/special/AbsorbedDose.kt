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
class AbsorbedDose internal constructor(magnitude: BigDecimal, expression: Gray) :
    Measure<Gray, AbsorbedDose>(magnitude, expression, ::AbsorbedDose) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Gray(prefix))
}

/**
 * Represents the SI derived unit of absorbed dose: **gray** (Gy).
 *
 * One gray is the absorption of one joule of radiation energy per kilogram of matter.
 *
 * SI definition: `Gy = m²·s⁻²`.
 */
class Gray private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Gray>(prefix, overflow, unit, ::Gray) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for gray: "Gy". */
        internal val UNIT = Unit("Gy", 1)
    }
}
