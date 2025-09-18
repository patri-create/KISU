package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Steradian
import org.kisu.units.special.Watt
import java.math.BigDecimal

/**
 * Measure of radiant intensity expressed in [WattPerSteradian].
 *
 * Radiant intensity quantifies the distribution of radiated power in a particular direction.
 *
 * Common applications include:
 * - Optics and photometry (characterizing light sources)
 * - Radiative heat transfer (directional energy emission)
 * - Astronomy and remote sensing (measuring emission from celestial sources)
 *
 * @property magnitude Numerical value of the radiant intensity.
 * @property expression Unit of the radiant intensity, here [WattPerSteradian].
 *
 * @see WattPerSteradian
 */
class RadiantIntensity(
    magnitude: BigDecimal,
    expression: WattPerSteradian
) : Measure<RadiantIntensity.WattPerSteradian, RadiantIntensity>(magnitude, expression, ::RadiantIntensity) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, WattPerSteradian(prefix))

    /**
     * Unit of [RadiantIntensity].
     *
     * Represents the unit of **radiant intensity**, i.e., the physical quantity measuring
     * radiant power emitted per unit solid angle.
     *
     * Symbol: `W/sr`
     * SI: `kg·m²·s⁻³`
     *
     * @see RadiantIntensity
     */
    typealias WattPerSteradian = Quotient<Watt, Steradian>

    companion object {
        /**
         * Creates a measure of **watts per steradian** (W/sr).
         *
         * This derived unit expresses **radiant intensity** —
         * the power emitted per unit solid angle.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Watt] (power) with the specified [prefix]
         *  - divided by a [Steradian] (solid angle)
         *
         * @param prefix Metric prefix to apply to the watt unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [WattPerSteradian] representing W/sr.
         */
        @Suppress("FunctionNaming")
        internal fun WattPerSteradian(prefix: Metric = Metric.BASE): WattPerSteradian =
            Quotient(Watt(prefix), Steradian())
    }
}
