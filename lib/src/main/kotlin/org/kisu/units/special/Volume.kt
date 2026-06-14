package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
import java.math.BigDecimal

private const val CUBIC_METRE_SCALE_BASE = 1000

/**
 * Represents the physical quantity of **volume**, measured in [CubicMetre].
 *
 * Volume quantifies how much three-dimensional space is occupied or enclosed. It is
 * used for containers, rooms, solids, fluids, and any region with spatial extent in
 * three dimensions.
 *
 * Typical examples include the capacity of a tank, the internal space of a room, the
 * volume of fuel consumed, or the size of a solid object.
 *
 * The canonical SI unit is the [CubicMetre] (`m³`), with smaller or larger metric
 * forms such as `cm³` or `km³` used when appropriate.
 */
class Volume internal constructor(magnitude: BigDecimal, expression: CubicMetre) :
    Measure<CubicMetre, Volume>(magnitude, expression, ::Volume) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, CubicMetre(prefix))
}

/**
 * Represents the unit **cubic metre** (`m³`), used to express [Volume].
 *
 * A cubic metre quantifies three-dimensional space. It is the volume enclosed by a
 * cube whose edges are each one metre long.
 *
 * This unit is used for the capacity of rooms, tanks, containers, natural gas usage,
 * rainfall storage, and many engineering calculations involving occupied space.
 *
 * In unit form, `m³` is the product of three lengths.
 *
 * @see Volume
 * @see SquareMetre
 */
class CubicMetre private constructor(
    scale: Scale<Metric> = ExponentialScale(CUBIC_METRE_SCALE_BASE),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, CubicMetre>(scale, prefix, unit, ::CubicMetre) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for cubic metre: "m³". */
        internal val UNIT = Unit("m³", 1)
    }
}
