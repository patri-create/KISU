@file:Suppress("TooManyFunctions")

package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.mechanics.SpectralRadiance.Companion.WattPerSteradianCubicMetre
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import org.kisu.units.special.Steradian
import org.kisu.units.special.Watt

/**
 * Represents the physical quantity of **spectral radiance**, measured in
 * [WattPerSteradianCubicMetre].
 *
 * Spectral radiance combines directional radiance with spectral resolution. It is used
 * when both angular distribution and spectral content of radiation must be described at
 * once.
 *
 * Typical examples include atmospheric radiation models, plasma emission studies, and
 * remote-sensing radiative transfer.
 *
 * The associated unit representation is [WattPerSteradianCubicMetre].
 */
class SpectralRadiance(
    magnitude: Magnitude,
    expression: WattPerSteradianCubicMetre
) : Measure<SpectralRadiance.WattPerSteradianCubicMetre, SpectralRadiance>(
    magnitude = magnitude,
    expression = expression,
    create = ::SpectralRadiance
) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, WattPerSteradianCubicMetre(prefix))

    /**
     * Unit of [SpectralRadiance].
     *
     * Represents the unit of **spectral radiance**, i.e., the physical quantity measuring
     * radiant power per unit volume per unit solid angle.
     *
     * Symbol: `W/(sr·m³)`
     * SI: `kg·m⁻¹·s⁻³`
     *
     * @see SpectralRadiance
     */
    typealias WattPerSteradianCubicMetre = Quotient<Watt, Product<Steradian, CubicMetre>>

    companion object {
        /**
         * Creates a [WattPerSteradianCubicMetre] expression for **watt per steradian cubic metre** (`W/(sr·m³)`).
         *
         * @param prefix Metric prefix applied to the watt unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [WattPerSteradianCubicMetre] expression for `W/(sr·m³)`.
         */
        @Suppress("FunctionNaming")
        internal fun WattPerSteradianCubicMetre(prefix: Metric = Metric.BASE): WattPerSteradianCubicMetre =
            Quotient(Watt(prefix), Product(Steradian(), CubicMetre()))
    }

    // Dimension-aware arithmetic
    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.mechanics.Radiance =
        org.kisu.units.mechanics.Radiance(canonical.component1() * other.canonical.component1())
}
