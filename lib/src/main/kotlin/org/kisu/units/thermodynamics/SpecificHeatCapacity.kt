@file:Suppress("TooManyFunctions")

package org.kisu.units.thermodynamics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kelvin
import org.kisu.units.base.Kilogram
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import org.kisu.units.thermodynamics.SpecificHeatCapacity.Companion.JoulePerKilogramKelvin

/**
 * Represents the physical quantity of **specific heat capacity**, measured in
 * [JoulePerKilogramKelvin].
 *
 * Specific heat capacity quantifies how much heat is required to raise the temperature
 * of one kilogram of a substance by one kelvin. It is one of the most important
 * descriptors of a material's thermal response.
 *
 * Typical examples include comparing water, metals, or building materials for heating,
 * cooling, and energy-storage behavior.
 *
 * The associated SI unit representation is [JoulePerKilogramKelvin] (`J/(kg·K)`).
 */
class SpecificHeatCapacity(
    magnitude: Magnitude,
    expression: JoulePerKilogramKelvin
) : Measure<SpecificHeatCapacity.JoulePerKilogramKelvin, SpecificHeatCapacity>(
    magnitude = magnitude,
    expression = expression,
    create = ::SpecificHeatCapacity
) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(
            magnitude,
            JoulePerKilogramKelvin(prefix)
        )

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

    companion object {
        /**
         * Creates a [JoulePerKilogramKelvin] expression for **joule per kilogram kelvin** (`J/(kg·K)`).
         *
         * @param prefix Metric prefix applied to the kilogram unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [JoulePerKilogramKelvin] expression for `J/(kg·K)`.
         */
        @Suppress("FunctionNaming")
        internal fun JoulePerKilogramKelvin(prefix: Metric = Metric.BASE): JoulePerKilogramKelvin =
            Quotient(
                Joule(),
                Product(Kilogram(prefix), Kelvin())
            )
    }

    // Dimension-aware arithmetic
    /**
     * Multiplies this [SpecificHeatCapacity] by [Mass][org.kisu.units.base.Mass],
     * yielding [HeatCapacity][org.kisu.units.thermodynamics.HeatCapacity].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.thermodynamics.HeatCapacity =
        org.kisu.units.thermodynamics.HeatCapacity(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [SpecificHeatCapacity] by [Temperature][org.kisu.units.base.Temperature],
     * yielding [SpecificEnergy][org.kisu.units.mechanics.SpecificEnergy].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Temperature
    ): org.kisu.units.mechanics.SpecificEnergy =
        org.kisu.units.mechanics.SpecificEnergy(canonical.component1() * other.canonical.component1())
}
