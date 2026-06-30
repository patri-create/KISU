@file:Suppress("TooManyFunctions")

package org.kisu.units.special

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents the physical quantity of **power**, measured in [Watt].
 *
 * Power quantifies how quickly energy is transferred, converted, or expended. It is
 * the rate form of energy, appearing in electrical systems, mechanical devices,
 * heating, radiation, and fluid transport.
 *
 * Typical examples include the output of a motor, the consumption of an appliance, the
 * thermal power of a heater, or the radiant output of a source.
 *
 * The canonical SI unit is the [Watt] (`W`), commonly scaled as `mW`, `kW`, or `MW`.
 */
class Power internal constructor(magnitude: Magnitude, expression: Watt) :
    Measure<Watt, Power>(magnitude, expression, ::Power) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Watt(prefix))

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Current
    ): org.kisu.units.special.ElectricPotential =
        org.kisu.units.special.ElectricPotential(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.mechanics.SpectralPower =
        org.kisu.units.mechanics.SpectralPower(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.kinematics.linear.Speed
    ): org.kisu.units.special.Force =
        org.kisu.units.special.Force(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.HeatFluxDensity
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.RadiantIntensity
    ): org.kisu.units.special.SolidAngle =
        org.kisu.units.special.SolidAngle(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.SpectralPower
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Area
    ): org.kisu.units.mechanics.HeatFluxDensity =
        org.kisu.units.mechanics.HeatFluxDensity(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.ElectricPotential
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Force
    ): org.kisu.units.kinematics.linear.Speed =
        org.kisu.units.kinematics.linear.Speed(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.SolidAngle
    ): org.kisu.units.mechanics.RadiantIntensity =
        org.kisu.units.mechanics.RadiantIntensity(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.photometric.Efficacy
    ): org.kisu.units.special.LuminousFlux =
        org.kisu.units.special.LuminousFlux(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.thermodynamics.ThermalResistance
    ): org.kisu.units.base.Temperature =
        org.kisu.units.base.Temperature(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the unit **watt** (`W`), used to express [Power].
 *
 * A watt quantifies the rate at which energy is transferred or work is performed. One
 * watt means one [Joule] of energy transferred each second.
 *
 * This unit is used for light bulb ratings, motor output, appliance consumption,
 * battery charging, and thermal transfer rates.
 *
 * In unit form, `W = J/s = m²·kg·s⁻³`.
 *
 * @see Power
 * @see Joule
 * @see Volt
 */
class Watt private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Watt>(algebra, prefix, unit, ::Watt) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for watt: "W". */
        internal val UNIT = Unit("W", 1)
    }
}
