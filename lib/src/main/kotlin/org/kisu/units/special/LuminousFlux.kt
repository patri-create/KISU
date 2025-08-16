package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **luminous flux**, measured in lumens (lm).
 *
 * One lumen is the luminous flux emitted in a solid angle of one steradian by a point source with a luminous
 * intensity of one candela. In SI, it is defined as cd·sr.
 *
 * Lumens are widely used in lighting to describe the total perceived power of light emitted.
 *
 * This class expresses luminous flux as a combination of a [magnitude] and an [expression], supporting values such as
 * millilumens (mlm), kilolumens (klm), or megalumens (Mlm).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class LuminousFlux internal constructor(magnitude: BigDecimal, expression: Lumen) :
    Measure<Lumen, LuminousFlux>(magnitude, expression, ::LuminousFlux) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Lumen(prefix))
}

/**
 * Represents the SI derived unit of luminous flux: **lumen** (lm).
 *
 * One lumen is the luminous flux emitted within a unit solid angle (one steradian)
 * by a point source having a uniform intensity of one candela.
 *
 * SI definition: `lm = cd·sr`.
 */
class Lumen private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Lumen>(prefix, overflow, unit, ::Lumen) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for lumen: "lm". */
        internal val UNIT = Unit("lm", 1)
    }
}
