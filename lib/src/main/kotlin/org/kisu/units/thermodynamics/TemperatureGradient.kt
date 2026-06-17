package org.kisu.units.thermodynamics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kelvin
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import org.kisu.units.thermodynamics.TemperatureGradient.Companion.KelvinPerMetre

/**
 * Represents the physical quantity of **temperature gradient**, measured in
 * [KelvinPerMetre].
 *
 * Temperature gradient quantifies how rapidly temperature changes across space. It is
 * the spatial counterpart of a thermal slope and is central to heat conduction and
 * transport analysis.
 *
 * Typical examples include the temperature drop through a wall, geothermal gradients in
 * the ground, or thermal fields inside a device.
 *
 * The associated SI unit representation is [KelvinPerMetre] (`K/m`).
 */
class TemperatureGradient(
    magnitude: Magnitude,
    expression: KelvinPerMetre
) : Measure<TemperatureGradient.KelvinPerMetre, TemperatureGradient>(
    magnitude = magnitude,
    expression = expression,
    create = ::TemperatureGradient
) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, KelvinPerMetre(prefix))

    /**
     * Represents the SI unit **kelvin per metre (K/m)**.
     *
     * This unit measures a **temperature gradient**, i.e., the rate of change of temperature
     * with respect to distance.
     * It is defined as the [Quotient] of [Kelvin] (temperature) divided by [Metre] (length).
     *
     * Example usages include:
     * - Heat conduction through materials (Fourier’s law)
     * - Atmospheric or oceanic temperature gradients
     * - Thermal engineering calculations
     *
     * @see TemperatureGradient for the physical quantity represented by this unit.
     */
    typealias KelvinPerMetre = Quotient<Kelvin, Metre>

    companion object {
        /**
         * Creates a [KelvinPerMetre] expression for **kelvin per metre** (`K/m`).
         *
         * @param prefix Metric prefix applied to the kelvin unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [KelvinPerMetre] expression for `K/m`.
         */
        @Suppress("FunctionNaming")
        internal fun KelvinPerMetre(prefix: Metric = Metric.BASE): KelvinPerMetre =
            Quotient(Kelvin(prefix), Metre())
    }
}
