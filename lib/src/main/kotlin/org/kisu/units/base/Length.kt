@file:Suppress("TooManyFunctions")

package org.kisu.units.base

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.mechanics.WaveNumber
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents the physical quantity of **length**, measured in metres (m).
 *
 * Length describes the extent of objects or the distance between points in space. This class models length
 * as defined by the SI system, using the metre as the base unit and supporting metric prefixes such as millimetre (mm),
 * centimetre (cm), and kilometre (km).
 *
 * The quantity is expressed with a `magnitude` and an `expression`, enabling precise representation of both small- and
 * large-scale measurements using [Magnitude] for accuracy.
 */
class Length internal constructor(magnitude: Magnitude, expression: Metre) :
    Measure<Metre, Length>(magnitude, expression, ::Length) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Metre(prefix))

    /**
     * Returns the wave number associated with this length by inverting its canonical magnitude.
     */
    val waveNumber: WaveNumber
        get() = WaveNumber(canonical.component1().inverted)

    // Dimension-aware arithmetic
    /**
     * Divides this [Length] by [Time][org.kisu.units.base.Time],
     * yielding [Speed][org.kisu.units.kinematics.linear.Speed].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.linear.Speed =
        org.kisu.units.kinematics.linear.Speed(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Length] by [MagneticSusceptibility][org.kisu.units.electromagnetic.MagneticSusceptibility],
     * yielding [Inductance][org.kisu.units.special.Inductance].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.electromagnetic.MagneticSusceptibility
    ): org.kisu.units.special.Inductance =
        org.kisu.units.special.Inductance(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Length] by [Speed][org.kisu.units.kinematics.linear.Speed],
     * yielding [Time][org.kisu.units.base.Time].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.kinematics.linear.Speed
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Length] by [FuelEfficiency][org.kisu.units.mechanics.FuelEfficiency],
     * yielding [Volume][org.kisu.units.special.Volume].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.mechanics.FuelEfficiency
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Length] by [Inductance][org.kisu.units.special.Inductance],
     * yielding [MagneticSusceptibility][org.kisu.units.electromagnetic.MagneticSusceptibility].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Inductance
    ): org.kisu.units.electromagnetic.MagneticSusceptibility =
        org.kisu.units.electromagnetic.MagneticSusceptibility(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Length] by [Volume][org.kisu.units.special.Volume],
     * yielding [FuelEfficiency][org.kisu.units.mechanics.FuelEfficiency].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Volume
    ): org.kisu.units.mechanics.FuelEfficiency =
        org.kisu.units.mechanics.FuelEfficiency(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [Length] by [Length][org.kisu.units.base.Length],
     * yielding [Area][org.kisu.units.special.Area].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [ElectricConductivity][org.kisu.units.electromagnetic.ElectricConductivity],
     * yielding [Conductance][org.kisu.units.special.Conductance].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.electromagnetic.ElectricConductivity
    ): org.kisu.units.special.Conductance =
        org.kisu.units.special.Conductance(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [ElectricFieldStrength][org.kisu.units.electromagnetic.ElectricFieldStrength],
     * yielding [ElectricPotential][org.kisu.units.special.ElectricPotential].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.electromagnetic.ElectricFieldStrength
    ): org.kisu.units.special.ElectricPotential =
        org.kisu.units.special.ElectricPotential(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [LinearChargeDensity][org.kisu.units.electromagnetic.LinearChargeDensity],
     * yielding [ElectricCharge][org.kisu.units.special.ElectricCharge].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.electromagnetic.LinearChargeDensity
    ): org.kisu.units.special.ElectricCharge =
        org.kisu.units.special.ElectricCharge(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [MagneticPermittivity][org.kisu.units.electromagnetic.MagneticPermittivity],
     * yielding [Inductance][org.kisu.units.special.Inductance].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.electromagnetic.MagneticPermittivity
    ): org.kisu.units.special.Inductance =
        org.kisu.units.special.Inductance(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [MagneticVectorPotential][org.kisu.units.electromagnetic.MagneticVectorPotential],
     * yielding [MagneticFlux][org.kisu.units.special.MagneticFlux].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.electromagnetic.MagneticVectorPotential
    ): org.kisu.units.special.MagneticFlux =
        org.kisu.units.special.MagneticFlux(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [Magnetization][org.kisu.units.electromagnetic.Magnetization],
     * yielding [Current][org.kisu.units.base.Current].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.electromagnetic.Magnetization
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [Permittivity][org.kisu.units.electromagnetic.Permittivity],
     * yielding [Capacitance][org.kisu.units.special.Capacitance].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.electromagnetic.Permittivity
    ): org.kisu.units.special.Capacitance =
        org.kisu.units.special.Capacitance(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [LinearMassDensity][org.kisu.units.mechanics.LinearMassDensity],
     * yielding [Mass][org.kisu.units.base.Mass].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.mechanics.LinearMassDensity
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [Momentum][org.kisu.units.mechanics.Momentum],
     * yielding [AngularMomentum][org.kisu.units.mechanics.AngularMomentum].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.mechanics.Momentum
    ): org.kisu.units.mechanics.AngularMomentum =
        org.kisu.units.mechanics.AngularMomentum(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [SpectralIntensity][org.kisu.units.mechanics.SpectralIntensity],
     * yielding [RadiantIntensity][org.kisu.units.mechanics.RadiantIntensity].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.mechanics.SpectralIntensity
    ): org.kisu.units.mechanics.RadiantIntensity =
        org.kisu.units.mechanics.RadiantIntensity(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [SpectralPower][org.kisu.units.mechanics.SpectralPower],
     * yielding [Power][org.kisu.units.special.Power].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.mechanics.SpectralPower
    ): org.kisu.units.special.Power =
        org.kisu.units.special.Power(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [SpectralRadiance][org.kisu.units.mechanics.SpectralRadiance],
     * yielding [Radiance][org.kisu.units.mechanics.Radiance].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.mechanics.SpectralRadiance
    ): org.kisu.units.mechanics.Radiance =
        org.kisu.units.mechanics.Radiance(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [SurfaceTension][org.kisu.units.mechanics.SurfaceTension],
     * yielding [Force][org.kisu.units.special.Force].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.mechanics.SurfaceTension
    ): org.kisu.units.special.Force =
        org.kisu.units.special.Force(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [Area][org.kisu.units.special.Area],
     * yielding [Volume][org.kisu.units.special.Volume].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.Area
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [Force][org.kisu.units.special.Force],
     * yielding [Energy][org.kisu.units.special.Energy].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.Force
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [MagneticFlux][org.kisu.units.special.MagneticFlux],
     * yielding [MagneticMoment][org.kisu.units.electromagnetic.MagneticMoment].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.MagneticFlux
    ): org.kisu.units.electromagnetic.MagneticMoment =
        org.kisu.units.electromagnetic.MagneticMoment(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [MagneticFluxDensity][org.kisu.units.special.MagneticFluxDensity],
     * yielding [MagneticRigidity][org.kisu.units.electromagnetic.MagneticRigidity].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.MagneticFluxDensity
    ): org.kisu.units.electromagnetic.MagneticRigidity =
        org.kisu.units.electromagnetic.MagneticRigidity(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [Resistance][org.kisu.units.special.Resistance],
     * yielding [Resistivity][org.kisu.units.electromagnetic.Resistivity].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.Resistance
    ): org.kisu.units.electromagnetic.Resistivity =
        org.kisu.units.electromagnetic.Resistivity(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Length] by [TemperatureGradient][org.kisu.units.thermodynamics.TemperatureGradient],
     * yielding [Temperature][org.kisu.units.base.Temperature].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.thermodynamics.TemperatureGradient
    ): org.kisu.units.base.Temperature =
        org.kisu.units.base.Temperature(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the SI base unit of **length**.
 *
 * The metre (m) is the standard unit for measuring distance.
 */
class Metre private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Metre>(algebra, prefix, unit, ::Metre) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical SI symbol for length: "m". */
        internal val UNIT = Unit("m", 1)
    }
}
