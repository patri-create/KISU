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
 * Unit of [Spectralntensity].
 *
 * Represents the unit of **spectral intensity**, i.e., the physical quantity measuring
 * radiant power per unit length per unit solid angle.
 *
 * Symbol: `W/(sr·m)`
 * SI: `kg·s⁻³`
 *
 * @see Spectralntensity
 */
typealias WattPerSteradianMetre = Quotient<Watt, Product<Steradian, Metre>>

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
class Spectralntensity(
    magnitude: BigDecimal,
    expression: WattPerSteradianMetre
) : Measure<WattPerSteradianMetre, Spectralntensity>(magnitude, expression, ::Spectralntensity) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Watt(prefix), Product(Steradian(), Metre())))
}
