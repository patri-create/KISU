@file:Suppress("TooManyFunctions")

package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.mechanics.SpectralPower.Companion.WattPerMetre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Watt

/**
 * Represents the physical quantity of **spectral power**, measured in [WattPerMetre].
 *
 * Spectral power quantifies how radiant power is distributed with respect to a spectral
 * coordinate. It is frequently used when analyzing sources by wavelength or another
 * one-dimensional spectral variable.
 *
 * Typical examples include source spectra, laser outputs, and optical transmission
 * analysis.
 *
 * The associated unit representation is [WattPerMetre].
 */
class SpectralPower(
    magnitude: Magnitude,
    expression: WattPerMetre
) : Measure<SpectralPower.WattPerMetre, SpectralPower>(magnitude, expression, ::SpectralPower) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, WattPerMetre(prefix))

    /**
     * Unit of [SpectralPower].
     *
     * Represents the unit of **spectral power**, i.e., the physical quantity measuring
     * radiant power per unit length.
     *
     * Symbol: `W/m`
     * SI: `kg·s⁻³`
     *
     * @see SpectralPower
     */
    typealias WattPerMetre = Quotient<Watt, Metre>

    companion object {
        /**
         * Creates a [WattPerMetre] expression for **watt per metre** (`W/m`).
         *
         * @param prefix Metric prefix applied to the watt unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [WattPerMetre] expression for `W/m`.
         */
        @Suppress("FunctionNaming")
        internal fun WattPerMetre(prefix: Metric = Metric.BASE): WattPerMetre =
            Quotient(Watt(prefix), Metre())
    }

    // Dimension-aware arithmetic
    /**
     * Divides this [SpectralPower] by [SpectralIrradiance][org.kisu.units.mechanics.SpectralIrradiance],
     * yielding [Area][org.kisu.units.special.Area].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.mechanics.SpectralIrradiance
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [SpectralPower] by [Area][org.kisu.units.special.Area],
     * yielding [SpectralIrradiance][org.kisu.units.mechanics.SpectralIrradiance].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Area
    ): org.kisu.units.mechanics.SpectralIrradiance =
        org.kisu.units.mechanics.SpectralIrradiance(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [SpectralPower] by [Length][org.kisu.units.base.Length],
     * yielding [Power][org.kisu.units.special.Power].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.special.Power =
        org.kisu.units.special.Power(canonical.component1() * other.canonical.component1())
}
