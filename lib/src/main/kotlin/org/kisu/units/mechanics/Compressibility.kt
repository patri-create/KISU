package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
import org.kisu.units.special.Pascal
import java.math.BigDecimal

/**
 * Represents the physical quantity of **compressibility**, measured in
 * [ReciprocalPascal].
 *
 * Compressibility quantifies how strongly the volume of a substance changes when
 * pressure is applied. A highly compressible material changes volume readily, whereas a
 * low-compressibility material resists such change.
 *
 * This quantity is used in fluid mechanics, acoustics, thermodynamics, and materials
 * science, for example when comparing gases, liquids, and solids or when deriving bulk
 * modulus.
 *
 * The associated unit representation is [ReciprocalPascal] (`Pa⁻¹`).
 */
class Compressibility(
    magnitude: BigDecimal,
    expression: ReciprocalPascal
) : Measure<ReciprocalPascal, Compressibility>(magnitude, expression, ::Compressibility) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, ReciprocalPascal(prefix))
}

/**
 * Represents the unit **reciprocal pascal** (`Pa⁻¹`), used by [Compressibility].
 *
 * Reciprocal pascal quantifies how much a material's volume changes in response to
 * pressure. Larger values indicate a substance that is easier to compress.
 *
 * This unit is used in fluid mechanics, materials science, geophysics, and
 * thermodynamics when comparing how gases, liquids, or solids respond to pressure.
 *
 * In this library, [ReciprocalPascal] is defined as the inverse of [Pascal.UNIT].
 *
 * @see Compressibility
 * @see Pascal
 */
class ReciprocalPascal private constructor(
    scale: Scale<Metric> = ExponentialScale(),
    prefix: Metric,
    unit: Unit,
) : Scalar<Metric, ReciprocalPascal>(scale, prefix, unit, ::ReciprocalPascal) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        internal val UNIT = Pascal.UNIT.inverted
    }
}
