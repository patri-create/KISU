package org.kisu.units.derived

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import java.math.BigDecimal

/**
 * Represents the physical quantity of **plane angle**, measured in radians (rad).
 *
 * A radian is the angle subtended at the center of a circle by an arc whose length is equal to the radius.
 * It is the standard unit of angular measure used in many areas of mathematics.
 *
 * The radian is a **dimensionless** derived unit in the SI, meaning it has no units in terms of base SI dimensions.
 *
 * This class expresses angle as a combination of a [magnitude] and a [prefix], supporting values such as
 * milliradians (mrad) or kiloradians (krad).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class PlaneAngle internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
    Measure<Metric, PlaneAngle>(magnitude, prefix, SYMBOL, ::PlaneAngle) {

    companion object {
        /** The SI symbol for plane angle: "rad" (radian). */
        private const val SYMBOL = "rad"
    }
}