package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Steradian
import org.kisu.units.special.Watt

/**
 * Represents the physical quantity of **spectral intensity**, measured in
 * [WattPerSteradianMetre].
 *
 * Spectral intensity quantifies directional radiant power resolved per unit of spectral
 * coordinate. It is useful whenever not only direction but also spectral distribution
 * matters.
 *
 * Typical examples include spectroscopy, optical source characterization, and
 * astrophysical emission analysis.
 *
 * The associated unit representation is [WattPerSteradianMetre].
 */
class SpectralIntensity(
    magnitude: Magnitude,
    expression: WattPerSteradianMetre
) : Measure<SpectralIntensity.WattPerSteradianMetre, SpectralIntensity>(
    magnitude = magnitude,
    expression = expression,
    create = ::SpectralIntensity
) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
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
         * Creates a [WattPerSteradianMetre] expression for **watt per steradian metre** (`W/(sr·m)`).
         *
         * @param prefix Metric prefix applied to the watt unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [WattPerSteradianMetre] expression for `W/(sr·m)`.
         */
        @Suppress("FunctionNaming")
        internal fun WattPerSteradianMetre(prefix: Metric = Metric.BASE): WattPerSteradianMetre =
            Quotient(Watt(prefix), Product(Steradian(), Metre()))
    }
}
