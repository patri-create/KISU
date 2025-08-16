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
 * This class expresses area as a combination of a [magnitude] and an [expression], supporting values such as
 * square millimetres (mm²), square centimetres (cm²), or square kilometres (km²).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Area internal constructor(magnitude: BigDecimal, expression: SquareMetre) :
    Measure<SquareMetre, Area>(magnitude, expression, ::Area) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, SquareMetre(prefix))
}

/**
 * Represents the SI derived unit of area: **square metre** (m²).
 *
 * One square metre is the area of a square with sides one metre in length.
 *
 * SI definition: `m²`.
 */
class SquareMetre private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, SquareMetre>(prefix, overflow, unit, ::SquareMetre) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for square metre: "m²". */
        internal val UNIT = Unit("m²", 1)
    }
}
