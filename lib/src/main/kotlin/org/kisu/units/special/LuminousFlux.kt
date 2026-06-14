package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
import java.math.BigDecimal

/**
 * Represents the physical quantity of **luminous flux**, measured in [Lumen].
 *
 * Luminous flux quantifies the total visible light output of a source, weighted by the
 * response of human vision. It describes how much light is emitted overall, without
 * yet saying how that light is distributed.
 *
 * Typical examples include the rated output of a lamp, flashlight, LED panel, or
 * projector.
 *
 * The canonical SI unit is the [Lumen] (`lm`), often scaled to `klm` for larger
 * lighting systems.
 */
class LuminousFlux internal constructor(magnitude: BigDecimal, expression: Lumen) :
    Measure<Lumen, LuminousFlux>(magnitude, expression, ::LuminousFlux) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Lumen(prefix))
}

/**
 * Represents the unit **lumen** (`lm`), used to express [LuminousFlux].
 *
 * A lumen quantifies the total visible light emitted by a source, weighted by the
 * sensitivity of human vision. It describes overall light output rather than how that
 * light is distributed over an area.
 *
 * This unit is commonly used to compare light bulbs, LED fixtures, flashlights, and
 * other sources where total visible output matters.
 *
 * In unit form, `lm = cd·sr`.
 *
 * @see LuminousFlux
 * @see Lux
 * @see Steradian
 */
class Lumen private constructor(
    scale: Scale<Metric> = ExponentialScale(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Lumen>(scale, prefix, unit, ::Lumen) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for lumen: "lm". */
        internal val UNIT = Unit("lm", 1)
    }
}
