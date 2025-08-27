package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import org.kisu.units.special.Steradian
import org.kisu.units.special.Watt
import java.math.BigDecimal

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

/**
 * Measure of spectral radiance expressed in [WattPerSteradianCubicMetre].
 *
 * Spectral radiance quantifies the directional distribution of radiant power within a
 * volumetric medium.
 *
 * Common applications include:
 * - Radiative transfer in gases, plasmas, or participating media
 * - Astrophysics (emission of radiation in specific directions)
 * - Optical engineering (beam propagation analysis)
 *
 * @property magnitude Numerical value of the spectral radiance.
 * @property expression Unit of the spectral radiance, here [WattPerSteradianCubicMetre].
 *
 * @see WattPerSteradianCubicMetre
 */
class SpectralRadiance(
    magnitude: BigDecimal,
    expression: WattPerSteradianCubicMetre
) : Measure<WattPerSteradianCubicMetre, SpectralRadiance>(magnitude, expression, ::SpectralRadiance) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Watt(prefix), Product(Steradian(), CubicMetre())))
}
