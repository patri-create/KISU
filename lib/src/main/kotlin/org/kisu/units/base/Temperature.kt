@file:Suppress("TooManyFunctions")

package org.kisu.units.base

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.special.Celsius
import org.kisu.units.special.CelsiusTemperature
import org.kisu.units.thermodynamics.ThermalExpansionCoefficient

/**
 * Represents the physical quantity of **thermodynamic temperature**, measured in kelvin (K).
 *
 * Temperature quantifies the thermal state of a system in absolute terms,
 * using the **kelvin** as the SI base unit. Unlike degrees Celsius or Fahrenheit, the kelvin scale
 * starts at **absolute zero**, the lowest possible temperature in nature, where particles have
 * minimal thermal motion.
 *
 * Because the kelvin scale is absolute, **negative values are not physically meaningful** — no system
 * can exist below absolute zero. A temperature of zero kelvin represents a complete absence of thermal energy.
 *
 * This class models temperature as a combination of a [magnitude] and an optional metric [expression],
 * enabling precise representation of values such as millikelvin (mK) or kilokelvin (kK).
 *
 * The magnitude is stored using [Magnitude] for accuracy. All instances are validated to ensure they
 * respect physical constraints and are immutable once created.
 */
class Temperature internal constructor(magnitude: Magnitude, expression: Kelvin) :
    Measure<Kelvin, Temperature>(magnitude, expression, ::Temperature) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Kelvin(prefix))

    /**
     * Returns this absolute temperature on the Celsius scale.
     */
    val celsius: CelsiusTemperature
        get() = CelsiusTemperature(canonical.component1() - Celsius.CELSIUS_TO_KELVIN_OFFSET)

    /**
     * Returns the reciprocal-kelvin coefficient associated with this temperature.
     */
    val thermalExpansionCoefficient: ThermalExpansionCoefficient
        get() = ThermalExpansionCoefficient(canonical.component1().inverted)

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.thermodynamics.TemperatureGradient =
        org.kisu.units.thermodynamics.TemperatureGradient(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Power
    ): org.kisu.units.thermodynamics.ThermalResistance =
        org.kisu.units.thermodynamics.ThermalResistance(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.thermodynamics.TemperatureGradient
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.thermodynamics.ThermalResistance
    ): org.kisu.units.special.Power =
        org.kisu.units.special.Power(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.chemistry.MolarHeatCapacity
    ): org.kisu.units.chemistry.MolarEnergy =
        org.kisu.units.chemistry.MolarEnergy(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.thermodynamics.HeatCapacity
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.thermodynamics.SpecificHeatCapacity
    ): org.kisu.units.mechanics.SpecificEnergy =
        org.kisu.units.mechanics.SpecificEnergy(canonical.component1() * other.canonical.component1())
}

class Kelvin private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Kelvin>(algebra, prefix, unit, ::Kelvin) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The SI symbol for temperature: "K". */
        internal val UNIT = Unit("K", 1)
    }
}
