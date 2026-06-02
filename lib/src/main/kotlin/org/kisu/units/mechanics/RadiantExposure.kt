package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

/**
 * Represents the physical quantity of **radiant exposure**, measured in
 * [JoulePerSquareMetre].
 *
 * Radiant exposure quantifies the total radiant energy received by a surface per unit
 * area over a finite interval. It is the cumulative counterpart of irradiance.
 *
 * Typical examples include pulse-laser exposure, solar energy received over a time
 * window, and optical or radiation treatment delivery.
 *
 * The associated unit representation is [JoulePerSquareMetre] (`J/m²`).
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
        this(magnitude, JoulePerSquareMetre(prefix))

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

    companion object {
        /**
         * Creates a [JoulePerSquareMetre] expression for **joule per square metre** (`J/m²`).
         *
         * @param prefix Metric prefix applied to the joule unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [JoulePerSquareMetre] expression for `J/m²`.
         */
        @Suppress("FunctionNaming")
        internal fun JoulePerSquareMetre(prefix: Metric = Metric.BASE): JoulePerSquareMetre =
            Quotient(Joule(prefix), SquareMetre())
    }
}
