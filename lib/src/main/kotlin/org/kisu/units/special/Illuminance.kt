package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
import java.math.BigDecimal

/**
 * Represents the physical quantity of **illuminance**, measured in [Lux].
 *
 * Illuminance quantifies how much visible light arrives at a surface. Unlike luminous
 * flux, which describes total emitted light, illuminance is about the light actually
 * falling on a desk, road, page, sensor, or wall.
 *
 * Typical examples include office lighting standards, daylight measurements, museum
 * conservation limits, and exposure planning in photography or cinematography.
 *
 * The canonical SI unit is the [Lux] (`lx`), with values ranging from fractions of a
 * lux in dim settings to many kilolux in full daylight.
 */
class Illuminance internal constructor(magnitude: BigDecimal, expression: Lux) :
    Measure<Lux, Illuminance>(magnitude, expression, ::Illuminance) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Lux(prefix))
}

/**
 * Represents the unit **lux** (`lx`), used to express [Illuminance].
 *
 * A lux quantifies how much visible light reaches a surface. One lux means one [Lumen]
 * distributed over one [SquareMetre].
 *
 * It is used when specifying lighting conditions for offices, classrooms, roads,
 * museums, photography, or plant growth environments.
 *
 * In unit form, `lx = lm/m² = m⁻²·cd·sr`.
 *
 * @see Illuminance
 * @see Lumen
 * @see SquareMetre
 */
class Lux private constructor(
    scale: Scale<Metric> = ExponentialScale(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Lux>(scale, prefix, unit, ::Lux) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for lux: "lx". */
        internal val UNIT = Unit("lx", 1)
    }
}
