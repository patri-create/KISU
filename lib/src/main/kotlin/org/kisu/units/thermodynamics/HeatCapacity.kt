package org.kisu.units.thermodynamics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kelvin
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule

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
}
