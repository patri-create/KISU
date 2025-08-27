package org.kisu.units.thermodynamics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kelvin
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import java.math.BigDecimal

/**
 * Represents the SI unit **joule per kelvin (J/K)**.
 *
 * This unit measures **heat capacity**, i.e. the amount of energy required
 * to raise the temperature of a system by one kelvin.
 * It is defined as the [Quotient] of [Joule] (energy) divided by [Kelvin] (temperature).
 *
 * Example usages include:
 * - Total heat capacity of a system or object
 * - Thermodynamics and calorimetry calculations
 *
 * @see HeatCapacity for the physical quantity represented by this unit.
 */
typealias JoulePerKelvin = Quotient<Joule, Kelvin>

/**
 * Represents the physical quantity of **heat capacity**.
 *
 * Heat capacity quantifies the **amount of heat energy required to raise the temperature
 * of a system by one kelvin**.
 * Its SI unit is the **joule per kelvin (J/K)**, represented here by [JoulePerKelvin].
 *
 * Example usages include:
 * - The heat capacity of water (~4184 J/K per kilogram at 25 °C)
 * - The thermal response of materials to heating or cooling
 * - Engineering and thermodynamics calculations
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision.
 * Instances of [HeatCapacity] are immutable, and the [expression] parameter ties the measurement
 * to its unit representation ([JoulePerKelvin]).
 *
 * @property magnitude The numeric value of the heat capacity.
 * @property expression The unit expression of the heat capacity, always [JoulePerKelvin].
 */
class HeatCapacity(
    magnitude: BigDecimal,
    expression: JoulePerKelvin
) : Measure<JoulePerKelvin, HeatCapacity>(magnitude, expression, ::HeatCapacity) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Joule(prefix), Kelvin()))
}
