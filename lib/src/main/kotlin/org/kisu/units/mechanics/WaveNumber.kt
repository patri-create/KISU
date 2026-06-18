package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.base.Length
import org.kisu.units.base.Metre
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents the physical quantity of **wave number**, measured in
 * [ReciprocalMetre].
 *
 * Wave number quantifies spatial frequency: how many oscillations occur per unit
 * length. It is widely used in optics, spectroscopy, and quantum mechanics.
 *
 * Typical examples include spectroscopic line positions, electromagnetic wave
 * characterization, and molecular vibration analysis.
 *
 * The associated unit representation is [ReciprocalMetre] (`m⁻¹`).
 */
class WaveNumber(
    magnitude: Magnitude,
    expression: ReciprocalMetre
) : Measure<ReciprocalMetre, WaveNumber>(magnitude, expression, ::WaveNumber) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, ReciprocalMetre(prefix))

    /**
     * Returns the wavelength associated with this wave number by inverting its canonical magnitude.
     */
    val wavelength: Length
        get() = Length(canonical.component1().inverted)
}

/**
 * Represents the unit **reciprocal metre** (`m⁻¹`), used by [WaveNumber].
 *
 * Reciprocal metre quantifies how many wavelengths, cycles, or spatial oscillations
 * fit into a unit length. It is common in spectroscopy, optics, and wave mechanics.
 *
 * A familiar use is the spectroscopic wave number, where larger values correspond to
 * shorter wavelengths.
 *
 * In this library, [ReciprocalMetre] is defined as the inverse of [Metre.UNIT].
 *
 * @see WaveNumber
 */
class ReciprocalMetre private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit,
) : Scalar<Metric, ReciprocalMetre>(algebra, prefix, unit, ::ReciprocalMetre) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        internal val UNIT = Metre.UNIT.inverted
    }
}
