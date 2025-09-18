package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import org.kisu.units.special.Watt
import java.math.BigDecimal

/**
 * Measure of spectral irradiance expressed in [WattPerCubicMetre].
 *
 * Spectral irradiance quantifies the distribution of radiant power within a given volume,
 * commonly used to describe energy emission or absorption per unit volume.
 *
 * Common applications include:
 * - Radiative transfer in participating media (gases, plasmas)
 * - Astrophysics (emission from stellar atmospheres)
 * - Material sciences (volumetric energy deposition)
 *
 * @property magnitude Numerical value of the spectral irradiance.
 * @property expression Unit of the spectral irradiance, here [WattPerCubicMetre].
 *
 * @see WattPerCubicMetre
 */
class SpectralIrradiance(
    magnitude: BigDecimal,
    expression: WattPerCubicMetre
) : Measure<SpectralIrradiance.WattPerCubicMetre, SpectralIrradiance>(
    magnitude = magnitude,
    expression = expression,
    create = ::SpectralIrradiance
) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
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
         * Creates a measure of **watts per cubic metre** (W/m³).
         *
         * This derived unit expresses **volumetric power density** —
         * how much power is distributed per unit volume.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Watt] (power) with the specified [prefix]
         *  - divided by a [CubicMetre] (volume)
         *
         * @param prefix Metric prefix to apply to the watt unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [WattPerCubicMetre] representing W/m³.
         */
        @Suppress("FunctionNaming")
        internal fun WattPerCubicMetre(prefix: Metric = Metric.BASE): WattPerCubicMetre =
            Quotient(Watt(prefix), CubicMetre())
    }
}
