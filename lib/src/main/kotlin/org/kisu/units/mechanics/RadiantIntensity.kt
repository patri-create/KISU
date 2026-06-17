package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.mechanics.RadiantIntensity.Companion.WattPerSteradian
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Steradian
import org.kisu.units.special.Watt

/**
 * Represents the physical quantity of **radiant intensity**, measured in
 * [WattPerSteradian].
 *
 * Radiant intensity quantifies how radiant power is distributed by direction. It is
 * especially useful for point-like or directional sources whose emission pattern
 * matters.
 *
 * Typical examples include lamps, LEDs, laser sources, and astronomical emitters.
 *
 * The associated unit representation is [WattPerSteradian] (`W/sr`).
 */
class RadiantIntensity(
    magnitude: Magnitude,
    expression: WattPerSteradian
) : Measure<RadiantIntensity.WattPerSteradian, RadiantIntensity>(magnitude, expression, ::RadiantIntensity) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
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
         * Creates a [WattPerSteradian] expression for **watt per steradian** (`W/sr`).
         *
         * @param prefix Metric prefix applied to the watt unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [WattPerSteradian] expression for `W/sr`.
         */
        @Suppress("FunctionNaming")
        internal fun WattPerSteradian(prefix: Metric = Metric.BASE): WattPerSteradian =
            Quotient(Watt(prefix), Steradian())
    }
}
