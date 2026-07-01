@file:Suppress("TooManyFunctions")

package org.kisu.units.thermodynamics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kelvin
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import org.kisu.units.thermodynamics.HeatCapacity.Companion.JoulePerKelvin

/**
 * Represents the physical quantity of **heat capacity**, measured in
 * [JoulePerKelvin].
 *
 * Heat capacity quantifies how much heat must be supplied to raise the temperature of
 * an entire system by one kelvin. It depends on the system as a whole, not only on the
 * material from which it is made.
 *
 * Typical examples include the thermal response of a sample, vessel, machine component,
 * or room-sized system during heating and cooling.
 *
 * The associated SI unit representation is [JoulePerKelvin] (`J/K`).
 */
class HeatCapacity(
    magnitude: Magnitude,
    expression: JoulePerKelvin
) : Measure<HeatCapacity.JoulePerKelvin, HeatCapacity>(magnitude, expression, ::HeatCapacity) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, JoulePerKelvin(prefix))

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

    companion object {
        /**
         * Creates a [JoulePerKelvin] expression for **joule per kelvin** (`J/K`).
         *
         * @param prefix Metric prefix applied to the joule unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [JoulePerKelvin] expression for `J/K`.
         */
        @Suppress("FunctionNaming")
        internal fun JoulePerKelvin(prefix: Metric = Metric.BASE): JoulePerKelvin =
            Quotient(Joule(prefix), Kelvin())
    }

    // Dimension-aware arithmetic
    /**
     * Divides this [HeatCapacity] by [Amount][org.kisu.units.base.Amount],
     * yielding [MolarHeatCapacity][org.kisu.units.chemistry.MolarHeatCapacity].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Amount
    ): org.kisu.units.chemistry.MolarHeatCapacity =
        org.kisu.units.chemistry.MolarHeatCapacity(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [HeatCapacity] by [Mass][org.kisu.units.base.Mass],
     * yielding [SpecificHeatCapacity][org.kisu.units.thermodynamics.SpecificHeatCapacity].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.thermodynamics.SpecificHeatCapacity =
        org.kisu.units.thermodynamics.SpecificHeatCapacity(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [HeatCapacity] by [MolarHeatCapacity][org.kisu.units.chemistry.MolarHeatCapacity],
     * yielding [Amount][org.kisu.units.base.Amount].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.chemistry.MolarHeatCapacity
    ): org.kisu.units.base.Amount =
        org.kisu.units.base.Amount(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [HeatCapacity] by [SpecificHeatCapacity][org.kisu.units.thermodynamics.SpecificHeatCapacity],
     * yielding [Mass][org.kisu.units.base.Mass].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.thermodynamics.SpecificHeatCapacity
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [HeatCapacity] by [Temperature][org.kisu.units.base.Temperature],
     * yielding [Energy][org.kisu.units.special.Energy].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Temperature
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())
}
