package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Measure of wave number expressed in [ReciprocalMetre].
 *
 * Wave number quantifies the **spatial frequency of a wave**, defined as the number of
 * wave cycles per unit distance. It is widely used in spectroscopy, optics, and quantum mechanics.
 *
 * Common applications include:
 * - Characterizing light and electromagnetic waves
 * - Spectroscopic analysis of materials
 * - Quantum physics and molecular vibrations
 *
 * @property magnitude Numerical value of the wave number.
 * @property expression Unit of the wave number, here [ReciprocalMetre].
 *
 * @see ReciprocalMetre
 */
class WaveNumber(
    magnitude: BigDecimal,
    expression: ReciprocalMetre
) : Measure<ReciprocalMetre, WaveNumber>(magnitude, expression, ::WaveNumber) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, ReciprocalMetre(prefix))
}

/**
 * Unit of [WaveNumber].
 *
 * Represents the unit of **wave number**, i.e., the physical quantity measuring
 * the number of waves per unit length.
 *
 * Symbol: `m⁻¹`
 * SI: `m⁻¹`
 *
 * @see WaveNumber
 */
class ReciprocalMetre private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit,
) : Scalar<Metric, ReciprocalMetre>(prefix, overflow, unit, ::ReciprocalMetre) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        internal val UNIT = Metre.UNIT.inverted
    }
}
