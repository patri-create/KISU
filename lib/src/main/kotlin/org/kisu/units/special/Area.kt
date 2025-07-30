package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **area**, measured in square metres (m²).
 *
 * One square metre is the area of a square with sides of one metre in length.
 * This is a coherent derived SI unit with the base unit m².
 *
 * This class expresses area as a combination of a [magnitude] and a [prefix], supporting values such as
 * square millimetres (mm²), square centimetres (cm²), or square kilometres (km²).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Area internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, Area>(magnitude, expression, ::Area) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, unit = UNIT))

    companion object {
        /** The SI symbol for area: "m²" (square metre). */
        internal val UNIT = Unit("m²", 1)
    }
}
