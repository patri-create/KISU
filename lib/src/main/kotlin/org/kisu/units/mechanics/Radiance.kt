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
) : Measure<Radiance.WattPerSteradianSquareMetre, Radiance>(magnitude, expression, ::Radiance) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, WattPerSteradianSquareMetre(prefix))

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

    companion object {
        /**
         * Creates a measure of **watts per steradian per square metre** (W/(sr·m²)).
         *
         * This derived unit expresses **radiant intensity per unit area** —
         * the power emitted per unit solid angle and per unit area.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Watt] (power) with the specified [prefix]
         *  - divided by the product of a [Steradian] (solid angle) and a [SquareMetre] (area)
         *
         * @param prefix Metric prefix to apply to the watt unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [WattPerSteradianSquareMetre] representing W/(sr·m²).
         */
        @Suppress("FunctionNaming")
        internal fun WattPerSteradianSquareMetre(prefix: Metric = Metric.BASE): WattPerSteradianSquareMetre =
            Quotient(Watt(prefix), Product(Steradian(), SquareMetre()))
    }
}
