package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
import java.math.BigDecimal

/**
 * Represents the physical quantity of **radioactive activity**, measured in [Becquerel].
 *
 * Radioactivity quantifies how frequently unstable nuclei decay. It measures rate, not
 * energy deposited and not biological effect, so it should be distinguished from
 * quantities such as absorbed dose and dose equivalent.
 *
 * Typical examples include the activity of a medical tracer, a sealed source, a
 * contaminated sample, or a naturally radioactive material.
 *
 * The canonical SI unit is the [Becquerel] (`Bq`), often scaled as `kBq`, `MBq`, or
 * `GBq`.
 */
class Radioactivity internal constructor(magnitude: BigDecimal, expression: Becquerel) :
    Measure<Becquerel, Radioactivity>(magnitude, expression, ::Radioactivity) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Becquerel(prefix))
}

/**
 * Represents the unit **becquerel** (`Bq`), used to express [Radioactivity].
 *
 * A becquerel quantifies radioactive activity: how often unstable nuclei decay. One
 * becquerel means one nuclear decay event per second.
 *
 * This unit is used for radioactive tracers, environmental monitoring, nuclear
 * medicine, and the characterization of radioactive sources.
 *
 * In unit form, `Bq = s⁻¹`.
 *
 * @see Radioactivity
 * @see Hertz
 */
class Becquerel private constructor(
    scale: Scale<Metric> = ExponentialScale(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Becquerel>(scale, prefix, unit, ::Becquerel) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for becquerel: "Bq". */
        internal val UNIT = Unit("Bq", 1)
    }
}
