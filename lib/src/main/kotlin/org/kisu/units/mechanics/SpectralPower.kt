package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Watt
import java.math.BigDecimal

/**
 * Measure of spectral power expressed in [WattPerMetre].
 *
 * Spectral power quantifies the distribution of radiant power along a specific length,
 * commonly used to analyze energy per wavelength or spatial dimension.
 *
 * Common applications include:
 * - Optical spectroscopy (power per wavelength)
 * - Radiometry (distribution of light along fibers or sources)
 * - Photonics (waveguide or laser power characterization)
 *
 * @property magnitude Numerical value of the spectral power.
 * @property expression Unit of the spectral power, here [WattPerMetre].
 *
 * @see WattPerMetre
 */
class SpectralPower(
    magnitude: BigDecimal,
    expression: WattPerMetre
) : Measure<SpectralPower.WattPerMetre, SpectralPower>(magnitude, expression, ::SpectralPower) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
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
         * Creates a measure of **watts per metre** (W/m).
         *
         * This derived unit expresses **linear power density** —
         * how much power is distributed per unit length.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Watt] (power) with the specified [prefix]
         *  - divided by a [Metre] (length)
         *
         * @param prefix Metric prefix to apply to the watt unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [WattPerMetre] representing W/m.
         */
        @Suppress("FunctionNaming")
        internal fun WattPerMetre(prefix: Metric = Metric.BASE): WattPerMetre =
            Quotient(Watt(prefix), Metre())
    }
}
