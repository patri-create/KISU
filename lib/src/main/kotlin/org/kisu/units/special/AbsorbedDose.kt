@file:Suppress("TooManyFunctions")

package org.kisu.units.special

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

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
class AbsorbedDose internal constructor(magnitude: Magnitude, expression: Gray) :
    Measure<Gray, AbsorbedDose>(magnitude, expression, ::AbsorbedDose) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Gray(prefix))

    // Dimension-aware arithmetic
    /**
     * Divides this [AbsorbedDose] by [Time][org.kisu.units.base.Time],
     * yielding [AbsorbedDoseRate][org.kisu.units.mechanics.AbsorbedDoseRate].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.mechanics.AbsorbedDoseRate =
        org.kisu.units.mechanics.AbsorbedDoseRate(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [AbsorbedDose] by [AbsorbedDoseRate][org.kisu.units.mechanics.AbsorbedDoseRate],
     * yielding [Time][org.kisu.units.base.Time].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.mechanics.AbsorbedDoseRate
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())
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
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Gray>(algebra, prefix, unit, ::Gray) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for gray: "Gy". */
        internal val UNIT = Unit("Gy", 1)
    }
}
