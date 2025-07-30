package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.Scalar
import java.math.BigDecimal

/**
 * Represents the physical quantity of **volume**, measured in cubic metres (m³).
 *
 * One cubic metre is the volume of a cube with edges one metre in length.
 * This is a coherent derived SI unit with the base unit m³.
 *
 * This class expresses volume as a combination of a [magnitude] and a [prefix], supporting values such as
 * cubic millimetres (mm³), cubic centimetres (cm³), or cubic kilometres (km³).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Volume internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, Volume>(magnitude, expression, ::Volume) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, SYMBOL))

    companion object {
        /** The SI symbol for volume: "m³" (cubic metre). */
        internal const val SYMBOL = "m³"
    }
}
