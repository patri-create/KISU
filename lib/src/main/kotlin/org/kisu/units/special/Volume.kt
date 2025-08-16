package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **volume**, measured in cubic metres (m³).
 *
 * One cubic metre is the volume of a cube with edges one metre in length.
 * This is a coherent derived SI unit with the base unit m³.
 *
 * This class expresses volume as a combination of a [magnitude] and an [expression], supporting values such as
 * cubic millimetres (mm³), cubic centimetres (cm³), or cubic kilometres (km³).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Volume internal constructor(magnitude: BigDecimal, expression: CubicMetre) :
    Measure<CubicMetre, Volume>(magnitude, expression, ::Volume) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, CubicMetre(prefix))
}

/**
 * Represents the SI derived unit of volume: **cubic metre** (m³).
 *
 * One cubic metre is the volume of a cube with edges one metre long.
 *
 * SI definition: `m³`.
 */
class CubicMetre private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, CubicMetre>(prefix, overflow, unit, ::CubicMetre) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for cubic metre: "m³". */
        internal val UNIT = Unit("m³", 1)
    }
}
