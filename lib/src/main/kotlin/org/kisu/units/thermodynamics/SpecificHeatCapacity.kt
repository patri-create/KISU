package org.kisu.units.thermodynamics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kelvin
import org.kisu.units.base.Kilogram
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import java.math.BigDecimal

/**
 * Represents the SI unit **joule per kilogram kelvin (J/(kg·K))**.
 *
 * This unit measures **specific heat capacity**, i.e., the amount of energy required
 * to raise the temperature of one kilogram of a substance by one kelvin.
 * It is defined as the [Quotient] of [Joule] (energy) divided by the [Product] of
 * [Kilogram] (mass) and [Kelvin] (temperature).
 *
 * Example usages include:
 * - Specific heat capacity of water (~4184 J/(kg·K) at 25 °C)
 * - Material science and thermodynamics calculations
 *
 * @see SpecificHeatCapacity for the physical quantity represented by this unit.
 */
typealias JoulePerKilogramKelvin = Quotient<Joule, Product<Kilogram, Kelvin>>

/**
 * Represents the physical quantity of **specific heat capacity**.
 *
 * Specific heat capacity quantifies the **amount of heat energy required to raise the temperature
 * of one kilogram of a substance by one kelvin**.
 * Its SI unit is the **joule per kilogram kelvin (J/(kg·K))**, represented here by [JoulePerKilogramKelvin].
 *
 * Example usages include:
 * - The specific heat capacity of water (~4184 J/(kg·K) at 25 °C)
 * - Material science and thermodynamics calculations
 * - Estimating heating and cooling requirements in engineering systems
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision.
 * Instances of [SpecificHeatCapacity] are immutable, and the [expression] parameter ties the measurement
 * to its unit representation ([JoulePerKilogramKelvin]).
 *
 * @property magnitude The numeric value of the specific heat capacity.
 * @property expression The unit expression of the specific heat capacity, always [JoulePerKilogramKelvin].
 */
class SpecificHeatCapacity(
    magnitude: BigDecimal,
    expression: JoulePerKilogramKelvin
) : Measure<JoulePerKilogramKelvin, SpecificHeatCapacity>(magnitude, expression, ::SpecificHeatCapacity) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(
            magnitude,
            Quotient(
                Joule(),
                Product(Kilogram(prefix), Kelvin())
            )
        )
}
