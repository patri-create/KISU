package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Steradian
import org.kisu.units.special.Watt
import java.math.BigDecimal

/**
 * Measure of spectral intensity expressed in [WattPerSteradianMetre].
 *
 * Spectral intensity quantifies the distribution of radiant power along a specific direction
 * per unit length.
 *
 * Common applications include:
 * - Radiometry (characterizing directional spectral power)
 * - Optics and photonics (laser beam or light source analysis)
 * - Astrophysics (intensity of radiation along a line of sight)
 *
 * @property magnitude Numerical value of the spectral intensity.
 * @property expression Unit of the spectral intensity, here [WattPerSteradianMetre].
 *
 * @see WattPerSteradianMetre
 */
class SpectralIntensity(
    magnitude: BigDecimal,
    expression: WattPerSteradianMetre
) : Measure<SpectralIntensity.WattPerSteradianMetre, SpectralIntensity>(
    magnitude = magnitude,
    expression = expression,
    create = ::SpectralIntensity
) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, WattPerSteradianMetre(prefix))

    /**
     * Unit of [SpectralIntensity].
     *
     * Represents the unit of **spectral intensity**, i.e., the physical quantity measuring
     * radiant power per unit length per unit solid angle.
     *
     * Symbol: `W/(sr·m)`
     * SI: `kg·s⁻³`
     *
     * @see SpectralIntensity
     */
    typealias WattPerSteradianMetre = Quotient<Watt, Product<Steradian, Metre>>

    companion object {
        /**
         * Creates a measure of **watts per steradian per metre** (W/(sr·m)).
         *
         * This derived unit expresses **radiant intensity per unit length** —
         * the power emitted per unit solid angle and per unit length.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Watt] (power) with the specified [prefix]
         *  - divided by the product of a [Steradian] (solid angle) and a [Metre] (length)
         *
         * @param prefix Metric prefix to apply to the watt unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [WattPerSteradianMetre] representing W/(sr·m).
         */
        @Suppress("FunctionNaming")
        internal fun WattPerSteradianMetre(prefix: Metric = Metric.BASE): WattPerSteradianMetre =
            Quotient(Watt(prefix), Product(Steradian(), Metre()))
    }
}
