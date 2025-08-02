package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **solid angle**, measured in steradians (sr).
 *
 * A steradian is the solid angle subtended at the center of a sphere by a portion of the surface
 * with area equal to the square of the radius. It is used in three-dimensional angular measurements.
 *
 * Like radians, the steradian is a **dimensionless** derived SI unit.
 *
 * This class expresses solid angle as a combination of a [magnitude] and a [prefix], supporting values such as
 * millisteradians (msr) or kilosteradians (ksr).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class SolidAngle internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, SolidAngle>(magnitude, expression, ::SolidAngle) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, UNIT))

    companion object {
        /** The SI symbol for solid angle: "sr" (steradian). */
        internal val UNIT = Unit("sr", 1)
    }
}
