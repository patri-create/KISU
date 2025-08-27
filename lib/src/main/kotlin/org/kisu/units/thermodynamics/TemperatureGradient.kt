package org.kisu.units.thermodynamics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kelvin
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

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

/**
 * Represents the physical quantity of **temperature gradient**.
 *
 * Temperature gradient quantifies the **rate of change of temperature with respect to distance**.
 * Its SI unit is the **kelvin per metre (K/m)**, represented here by [KelvinPerMetre].
 *
 * Example usages include:
 * - Heat conduction through materials (Fourier’s law)
 * - Atmospheric temperature gradients
 * - Thermal engineering and geophysics
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision.
 * Instances of [TemperatureGradient] are immutable, and the [expression] parameter ties the measurement
 * to its unit representation ([KelvinPerMetre]).
 *
 * @property magnitude The numeric value of the temperature gradient.
 * @property expression The unit expression of the temperature gradient, always [KelvinPerMetre].
 */
class TemperatureGradient(
    magnitude: BigDecimal,
    expression: KelvinPerMetre
) : Measure<KelvinPerMetre, TemperatureGradient>(magnitude, expression, ::TemperatureGradient) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Kelvin(prefix), Metre()))
}
