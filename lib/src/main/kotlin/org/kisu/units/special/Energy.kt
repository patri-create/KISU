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
 * Represents the physical quantity of **energy**, measured in [Joule].
 *
 * Energy quantifies the capacity to perform work, transfer heat, or produce change. In
 * mechanics it appears as kinetic or potential energy; in thermodynamics as heat; in
 * electricity as energy delivered or stored.
 *
 * Typical examples include the energy consumed by an appliance, the work done lifting
 * a load, the heat delivered to a sample, or the stored energy in a battery.
 *
 * The canonical SI unit is the [Joule] (`J`), commonly scaled as `mJ`, `kJ`, or `MJ`.
 */
class Energy internal constructor(magnitude: Magnitude, expression: Joule) :
    Measure<Joule, Energy>(magnitude, expression, ::Energy) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Joule(prefix))

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Amount
    ): org.kisu.units.chemistry.MolarEnergy =
        org.kisu.units.chemistry.MolarEnergy(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Current
    ): org.kisu.units.special.MagneticFlux =
        org.kisu.units.special.MagneticFlux(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.special.Force =
        org.kisu.units.special.Force(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.mechanics.SpecificEnergy =
        org.kisu.units.mechanics.SpecificEnergy(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Temperature
    ): org.kisu.units.thermodynamics.HeatCapacity =
        org.kisu.units.thermodynamics.HeatCapacity(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.chemistry.MolarEnergy
    ): org.kisu.units.base.Amount =
        org.kisu.units.base.Amount(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.EnergyDensity
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.RadiantExposure
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.SpecificEnergy
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Area
    ): org.kisu.units.mechanics.RadiantExposure =
        org.kisu.units.mechanics.RadiantExposure(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.ElectricCharge
    ): org.kisu.units.special.ElectricPotential =
        org.kisu.units.special.ElectricPotential(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.ElectricPotential
    ): org.kisu.units.special.ElectricCharge =
        org.kisu.units.special.ElectricCharge(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Force
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.MagneticFlux
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Volume
    ): org.kisu.units.mechanics.EnergyDensity =
        org.kisu.units.mechanics.EnergyDensity(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.thermodynamics.HeatCapacity
    ): org.kisu.units.base.Temperature =
        org.kisu.units.base.Temperature(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.base.Time
    ): org.kisu.units.mechanics.Action =
        org.kisu.units.mechanics.Action(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the unit **joule** (`J`), used to express [Energy].
 *
 * A joule quantifies energy, work, or heat. One joule is the energy transferred when
 * a force of one [Newton] acts through a distance of one metre.
 *
 * It is used for mechanical work, thermal energy, electrical energy delivered over
 * time, and many everyday quantities such as the energy content of food or the energy
 * stored in a battery cell.
 *
 * In unit form, `J = N·m = m²·kg·s⁻²`.
 *
 * @see Energy
 * @see Newton
 * @see Watt
 */
class Joule private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Joule>(algebra, prefix, unit, ::Joule) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for joule: "J". */
        internal val UNIT = Unit("J", 1)
    }
}
