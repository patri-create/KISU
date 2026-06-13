package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
import java.math.BigDecimal

/**
 * Represents the physical quantity of **dose equivalent**, measured in [Sievert].
 *
 * Dose equivalent quantifies radiation exposure in a way that reflects likely
 * biological effect rather than only absorbed energy. It distinguishes physically equal
 * doses that may not carry the same biological risk.
 *
 * This quantity is used in radiation protection, occupational monitoring, medical risk
 * assessment, and environmental safety reporting.
 *
 * The canonical SI unit is the [Sievert] (`Sv`), with `mSv` and `µSv` being especially
 * common in practice.
 */
class DoseEquivalent internal constructor(magnitude: BigDecimal, expression: Sievert) :
    Measure<Sievert, DoseEquivalent>(magnitude, expression, ::DoseEquivalent) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Sievert(prefix))
}

/**
 * Represents the unit **sievert** (`Sv`), used to express [DoseEquivalent].
 *
 * A sievert quantifies the biological significance of ionizing radiation exposure. It
 * shares the same dimensional form as the [Gray], but it applies weighting factors so
 * that the value reflects expected biological effect rather than only absorbed energy.
 *
 * This unit is used in radiation protection, occupational exposure limits, medical
 * imaging risk assessment, and environmental monitoring.
 *
 * In unit form, `Sv = J/kg = m²·s⁻²`.
 *
 * @see DoseEquivalent
 * @see Gray
 */
class Sievert private constructor(
    scale: Scale<Metric> = ExponentialScale(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Sievert>(scale, prefix, unit, ::Sievert) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for sievert: "Sv". */
        internal val UNIT = Unit("Sv", 1)
    }
}
