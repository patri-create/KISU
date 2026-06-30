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
 * Represents the physical quantity of **electric potential difference**, measured in
 * [Volt].
 *
 * Electric potential difference quantifies how much energy is available per unit charge
 * between two points. It is the quantity usually called voltage in circuits and field
 * problems.
 *
 * Typical examples include the output of a battery, the voltage across a resistor, the
 * supply level of a device, or the potential between two electrodes.
 *
 * The canonical SI unit is the [Volt] (`V`), with common practical forms such as `mV`,
 * `kV`, and everything in between.
 */
class ElectricPotential internal constructor(magnitude: Magnitude, expression: Volt) :
    Measure<Volt, ElectricPotential>(magnitude, expression, ::ElectricPotential) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Volt(prefix))

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Current
    ): org.kisu.units.special.Resistance =
        org.kisu.units.special.Resistance(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.electromagnetic.ElectricFieldStrength =
        org.kisu.units.electromagnetic.ElectricFieldStrength(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.electromagnetic.ElectricFieldStrength
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Elastance
    ): org.kisu.units.special.ElectricCharge =
        org.kisu.units.special.ElectricCharge(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.ElectricCharge
    ): org.kisu.units.special.Elastance =
        org.kisu.units.special.Elastance(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Resistance
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.base.Current
    ): org.kisu.units.special.Power =
        org.kisu.units.special.Power(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.base.Time
    ): org.kisu.units.special.MagneticFlux =
        org.kisu.units.special.MagneticFlux(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.electromagnetic.ElectronMobility
    ): org.kisu.units.mechanics.KinematicViscosity =
        org.kisu.units.mechanics.KinematicViscosity(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.Capacitance
    ): org.kisu.units.special.ElectricCharge =
        org.kisu.units.special.ElectricCharge(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.Conductance
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.ElectricCharge
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the unit **volt** (`V`), used to express [ElectricPotential].
 *
 * A volt quantifies electric potential difference, or energy available per unit charge.
 * It tells how strongly a source can drive charges through a circuit.
 *
 * Everyday examples include the output of a battery cell, the charging voltage of a
 * USB power supply, or the potential difference across a component in an electric
 * circuit.
 *
 * In unit form, `V = W/A = J/C = m²·kg·s⁻³·A⁻¹`.
 *
 * @see ElectricPotential
 * @see Watt
 * @see Coulomb
 */
class Volt private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Volt>(algebra, prefix, unit, ::Volt) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for volt: "V". */
        internal val UNIT = Unit("V", 1)
    }
}
