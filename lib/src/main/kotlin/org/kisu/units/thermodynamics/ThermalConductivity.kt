package org.kisu.units.thermodynamics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kelvin
import org.kisu.units.base.Metre
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Watt
import org.kisu.units.thermodynamics.ThermalConductivity.Companion.WattPerMetreKelvin

/**
 * Represents the physical quantity of **thermal conductivity**, measured in
 * [WattPerMetreKelvin].
 *
 * Thermal conductivity quantifies how readily heat flows through a material under a
 * temperature gradient. High values indicate efficient heat transfer; low values
 * indicate insulating behavior.
 *
 * This quantity is used to compare metals, ceramics, polymers, gases, and insulation
 * materials in engineering and physics.
 *
 * The associated SI unit representation is [WattPerMetreKelvin] (`W/(m·K)`).
 */
class ThermalConductivity(
    magnitude: Magnitude,
    expression: WattPerMetreKelvin
) : Measure<ThermalConductivity.WattPerMetreKelvin, ThermalConductivity>(
    magnitude = magnitude,
    expression = expression,
    create = ::ThermalConductivity
) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, WattPerMetreKelvin(prefix))

    /**
     * Represents the SI unit **watt per metre kelvin (W/(m·K))**.
     *
     * This unit measures **thermal conductivity**, i.e., the rate at which heat
     * energy passes through a material under a temperature gradient.
     * It is defined as the [Quotient] of [Watt] (power) divided by the [Product] of
     * [Metre] (length) and [Kelvin] (temperature).
     *
     * Example usages include:
     * - Characterizing materials (e.g., copper ≈ 401 W/(m·K), glass ≈ 1 W/(m·K))
     * - Heat transfer calculations in engineering and physics
     * - Thermal insulation design
     *
     * @see ThermalConductivity for the physical quantity represented by this unit.
     */
    typealias WattPerMetreKelvin = Quotient<Watt, Product<Metre, Kelvin>>

    companion object {
        /**
         * Creates a [WattPerMetreKelvin] expression for **watt per metre kelvin** (`W/(m·K)`).
         *
         * @param prefix Metric prefix applied to the watt unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [WattPerMetreKelvin] expression for `W/(m·K)`.
         */
        @Suppress("FunctionNaming")
        internal fun WattPerMetreKelvin(prefix: Metric = Metric.BASE): WattPerMetreKelvin =
            Quotient(Watt(prefix), Product(Metre(), Kelvin()))
    }
}
