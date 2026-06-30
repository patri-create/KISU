@file:Suppress("TooManyFunctions")

package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.mechanics.SpectralIrradiance.Companion.WattPerCubicMetre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import org.kisu.units.special.Watt

/**
 * Represents the physical quantity of **spectral irradiance**, measured in
 * [WattPerCubicMetre].
 *
 * Spectral irradiance quantifies radiant power distributed with respect to a spectral
 * variable in this library's chosen representation. It is useful in radiometric and
 * spectroscopic analyses where spectral decomposition matters.
 *
 * The associated unit representation is [WattPerCubicMetre].
 */
class SpectralIrradiance(
    magnitude: Magnitude,
    expression: WattPerCubicMetre
) : Measure<SpectralIrradiance.WattPerCubicMetre, SpectralIrradiance>(
    magnitude = magnitude,
    expression = expression,
    create = ::SpectralIrradiance
) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, WattPerCubicMetre(prefix))

    /**
     * Unit of [SpectralIrradiance].
     *
     * Represents the unit of **spectral irradiance**, i.e., the physical quantity measuring
     * radiant power per unit volume.
     *
     * Symbol: `W/m³`
     * SI: `kg·m⁻¹·s⁻³`
     *
     * @see SpectralIrradiance
     */
    typealias WattPerCubicMetre = Quotient<Watt, CubicMetre>

    companion object {
        /**
         * Creates a [WattPerCubicMetre] expression for **watt per cubic metre** (`W/m³`).
         *
         * @param prefix Metric prefix applied to the watt unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [WattPerCubicMetre] expression for `W/m³`.
         */
        @Suppress("FunctionNaming")
        internal fun WattPerCubicMetre(prefix: Metric = Metric.BASE): WattPerCubicMetre =
            Quotient(Watt(prefix), CubicMetre())
    }

    // Dimension-aware arithmetic
    operator fun times(
        other: org.kisu.units.special.Area
    ): org.kisu.units.mechanics.SpectralPower =
        org.kisu.units.mechanics.SpectralPower(canonical.component1() * other.canonical.component1())
}
