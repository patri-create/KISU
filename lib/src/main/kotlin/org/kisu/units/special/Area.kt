package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

private const val SQUARE_METRE_SCALE_BASE = 100

/**
 * Represents the physical quantity of **area**, measured in [SquareMetre].
 *
 * Area quantifies the extent of a surface. It answers how much two-dimensional space a
 * region occupies, regardless of whether that surface is a floor plan, a field, a
 * sheet of material, or a cross section in an engineering model.
 *
 * Typical examples include the surface of a room, the footprint of a building, the
 * cross-sectional area of a pipe, or the exposed face of a solar panel.
 *
 * The canonical SI unit is the [SquareMetre] (`m²`), with smaller or larger metric
 * forms such as `cm²` or `km²` used when convenient.
 */
class Area internal constructor(magnitude: BigDecimal, expression: SquareMetre) :
    Measure<SquareMetre, Area>(magnitude, expression, ::Area) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, SquareMetre(prefix))
}

/**
 * Represents the unit **square metre** (`m²`), used to express [Area].
 *
 * A square metre quantifies the extent of a two-dimensional surface. It is the area
 * enclosed by a square whose sides are each one metre long.
 *
 * This unit is used for the floor area of a room, the footprint of a building, the
 * size of a solar panel, or the surface of a wall to be painted.
 *
 * In unit form, `m²` is the product of two lengths.
 *
 * @see Area
 * @see CubicMetre
 */
class SquareMetre private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(SQUARE_METRE_SCALE_BASE),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, SquareMetre>(algebra, prefix, unit, ::SquareMetre) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for square metre: "m²". */
        internal val UNIT = Unit("m²", 1)
    }
}
