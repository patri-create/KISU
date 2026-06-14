package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
import java.math.BigDecimal

/**
 * Represents the physical quantity of **absorbed dose**, measured in [Gray].
 *
 * Absorbed dose quantifies how much energy from ionizing radiation is deposited in a
 * material per unit mass. It answers a physical question: how much radiant energy did
 * the matter actually absorb?
 *
 * This quantity is central in radiology, radiation therapy, shielding analysis, and
 * detector calibration. Typical examples include the dose delivered to tissue during a
 * treatment session or the energy absorbed by a sample exposed to radiation.
 *
 * The canonical SI unit is the [Gray] (`Gy`), with practical values often expressed in
 * mGy or cGy.
 */
class AbsorbedDose internal constructor(magnitude: BigDecimal, expression: Gray) :
    Measure<Gray, AbsorbedDose>(magnitude, expression, ::AbsorbedDose) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Gray(prefix))
}

/**
 * Represents the unit **gray** (`Gy`), used to express [AbsorbedDose].
 *
 * A gray quantifies how much energy from ionizing radiation is deposited in matter.
 * One gray means that one joule of radiation energy has been absorbed by one kilogram
 * of material.
 *
 * This unit is central in dosimetry, radiotherapy planning, and radiation shielding
 * studies, where the concern is the physical energy delivered to tissue or another
 * material rather than its biological effect.
 *
 * In unit form, `Gy = J/kg = m²·s⁻²`.
 *
 * @see AbsorbedDose
 * @see Sievert
 * @see Joule
 */
class Gray private constructor(
    scale: Scale<Metric> = ExponentialScale(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Gray>(scale, prefix, unit, ::Gray) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for gray: "Gy". */
        internal val UNIT = Unit("Gy", 1)
    }
}
