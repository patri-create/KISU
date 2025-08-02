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
 * This class expresses luminous flux as a combination of a [magnitude] and a [prefix], supporting values such as
 * millilumens (mlm), kilolumens (klm), or megalumens (Mlm).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class LuminousFlux internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, LuminousFlux>(magnitude, expression, ::LuminousFlux) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, UNIT))

    companion object {
        /** The SI symbol for luminous flux: "lm" (lumen). */
        internal val UNIT = Unit("lm", 1)
    }
}
