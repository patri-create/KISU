package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre
import org.kisu.units.special.Steradian
import org.kisu.units.special.Watt
import java.math.BigDecimal

/**
 * Unit of [Radiance].
 *
 * Represents the unit of **radiance**, i.e., the physical quantity measuring
 * radiant power emitted per unit area per unit solid angle.
 *
 * Symbol: `W/(sr·m²)`
 * SI: `kg·s⁻³`
 *
 * @see Radiance
 */
typealias WattPerSteradianSquareMetre = Quotient<Watt, Product<Steradian, SquareMetre>>

/**
 * Measure of radiance expressed in [WattPerSteradianSquareMetre].
 *
 * Radiance quantifies the intensity of electromagnetic radiation in a specific
 * direction per unit area.
 *
 * Common applications include:
 * - Optics and photometry (brightness of light sources)
 * - Radiative heat transfer (thermal emission calculations)
 * - Remote sensing and astronomy (intensity of observed radiation)
 *
 * @property magnitude Numerical value of the radiance.
 * @property expression Unit of the radiance, here [WattPerSteradianSquareMetre].
 *
 * @see WattPerSteradianSquareMetre
 */
class Radiance(
    magnitude: BigDecimal,
    expression: WattPerSteradianSquareMetre
) : Measure<WattPerSteradianSquareMetre, Radiance>(magnitude, expression, ::Radiance) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Watt(prefix), Product(Steradian(), SquareMetre())))
}
