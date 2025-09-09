package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

/**
 * Measure of radiant exposure expressed in [JoulePerSquareMetre].
 *
 * Radiant exposure quantifies the total energy incident on a surface per unit area,
 * integrating the effect of radiative flux over time.
 *
 * Common applications include:
 * - Photometry and radiometry (measurement of light energy)
 * - Solar energy studies (energy received by solar panels)
 * - Radiobiology and medical physics (dose from radiation therapy)
 *
 * @property magnitude Numerical value of the radiant exposure.
 * @property expression Unit of the radiant exposure, here [JoulePerSquareMetre].
 *
 * @see JoulePerSquareMetre
 */
class RadiantExposure(
    magnitude: BigDecimal,
    expression: JoulePerSquareMetre
) : Measure<RadiantExposure.JoulePerSquareMetre, RadiantExposure>(
    magnitude = magnitude,
    expression = expression,
    create = ::RadiantExposure
) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Joule(prefix), SquareMetre()))

    /**
     * Unit of [RadiantExposure].
     *
     * Represents the unit of **radiant exposure**, i.e., the physical quantity measuring
     * energy received per unit area from electromagnetic radiation.
     *
     * Symbol: `J/m²`
     * SI: `kg·s⁻²`
     *
     * @see RadiantExposure
     */
    typealias JoulePerSquareMetre = Quotient<Joule, SquareMetre>
}
