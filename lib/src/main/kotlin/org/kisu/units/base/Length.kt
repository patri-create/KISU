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
 * The quantity is expressed with a [magnitude] and an [expression], enabling precise representation of both small- and
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
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.linear.Speed =
        org.kisu.units.kinematics.linear.Speed(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.electromagnetic.MagneticSusceptibility
    ): org.kisu.units.special.Inductance =
        org.kisu.units.special.Inductance(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.kinematics.linear.Speed
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.FuelEfficiency
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Inductance
    ): org.kisu.units.electromagnetic.MagneticSusceptibility =
        org.kisu.units.electromagnetic.MagneticSusceptibility(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Volume
    ): org.kisu.units.mechanics.FuelEfficiency =
        org.kisu.units.mechanics.FuelEfficiency(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.electromagnetic.ElectricConductivity
    ): org.kisu.units.special.Conductance =
        org.kisu.units.special.Conductance(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.electromagnetic.ElectricFieldStrength
    ): org.kisu.units.special.ElectricPotential =
        org.kisu.units.special.ElectricPotential(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.electromagnetic.LinearChargeDensity
    ): org.kisu.units.special.ElectricCharge =
        org.kisu.units.special.ElectricCharge(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.electromagnetic.MagneticPermittivity
    ): org.kisu.units.special.Inductance =
        org.kisu.units.special.Inductance(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.electromagnetic.MagneticVectorPotential
    ): org.kisu.units.special.MagneticFlux =
        org.kisu.units.special.MagneticFlux(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.electromagnetic.Magnetization
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.electromagnetic.Permittivity
    ): org.kisu.units.special.Capacitance =
        org.kisu.units.special.Capacitance(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.LinearMassDensity
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.Momentum
    ): org.kisu.units.mechanics.AngularMomentum =
        org.kisu.units.mechanics.AngularMomentum(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.SpectralIntensity
    ): org.kisu.units.mechanics.RadiantIntensity =
        org.kisu.units.mechanics.RadiantIntensity(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.SpectralPower
    ): org.kisu.units.special.Power =
        org.kisu.units.special.Power(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.SpectralRadiance
    ): org.kisu.units.mechanics.Radiance =
        org.kisu.units.mechanics.Radiance(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.SurfaceTension
    ): org.kisu.units.special.Force =
        org.kisu.units.special.Force(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.Area
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.Force
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.MagneticFlux
    ): org.kisu.units.electromagnetic.MagneticMoment =
        org.kisu.units.electromagnetic.MagneticMoment(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.MagneticFluxDensity
    ): org.kisu.units.electromagnetic.MagneticRigidity =
        org.kisu.units.electromagnetic.MagneticRigidity(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.Resistance
    ): org.kisu.units.electromagnetic.Resistivity =
        org.kisu.units.electromagnetic.Resistivity(canonical.component1() * other.canonical.component1())

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
