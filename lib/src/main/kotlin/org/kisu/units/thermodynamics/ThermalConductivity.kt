package org.kisu.units.thermodynamics

import org.kisu.units.Measure
import org.kisu.units.base.Kelvin
import org.kisu.units.base.Metre
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Watt
import java.math.BigDecimal

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

/**
 * Represents the physical quantity of **thermal conductivity**.
 *
 * Thermal conductivity quantifies a material’s **ability to conduct heat**.
 * It measures the rate at which heat energy passes through a material under a given
 * temperature gradient.
 * Its SI unit is the **watt per metre kelvin (W/(m·K))**, represented here by [WattPerMetreKelvin].
 *
 * Example usages include:
 * - Characterising materials (e.g., copper ≈ 401 W/(m·K), glass ≈ 1 W/(m·K))
 * - Heat transfer calculations in engineering and physics
 * - Thermal insulation design
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision.
 * Instances of [ThermalConductivity] are immutable, and the [expression] parameter ties the measurement
 * to its unit representation ([WattPerMetreKelvin]).
 *
 * @property magnitude The numeric value of the thermal conductivity.
 * @property expression The unit expression of the thermal conductivity, always [WattPerMetreKelvin].
 */
class ThermalConductivity(
    magnitude: BigDecimal,
    expression: WattPerMetreKelvin
) : Measure<WattPerMetreKelvin, ThermalConductivity>(magnitude, expression, ::ThermalConductivity)
