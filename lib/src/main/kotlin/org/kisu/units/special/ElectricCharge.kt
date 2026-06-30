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
 * Represents the physical quantity of **electric charge**, measured in [Coulomb].
 *
 * Electric charge quantifies how much electrically active matter or imbalance is
 * present. It is the conserved quantity behind electrostatics, current flow, and the
 * behavior of capacitors, batteries, and charged particles.
 *
 * Typical examples include the charge stored on a capacitor plate, the charge moved
 * through a circuit during a pulse, or the charge associated with ions in an
 * electrochemical process.
 *
 * The canonical SI unit is the [Coulomb] (`C`), often written as `mC` or `µC` for
 * laboratory-scale quantities.
 */
class ElectricCharge internal constructor(magnitude: Magnitude, expression: Coulomb) :
    Measure<Coulomb, ElectricCharge>(magnitude, expression, ::ElectricCharge) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Coulomb(prefix))

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Current
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.electromagnetic.LinearChargeDensity =
        org.kisu.units.electromagnetic.LinearChargeDensity(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.electromagnetic.Exposure =
        org.kisu.units.electromagnetic.Exposure(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.electromagnetic.ElectricChargeDensity
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.electromagnetic.ElectricDisplacementField
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.electromagnetic.Exposure
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.electromagnetic.LinearChargeDensity
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Area
    ): org.kisu.units.electromagnetic.ElectricDisplacementField =
        org.kisu.units.electromagnetic.ElectricDisplacementField(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Capacitance
    ): org.kisu.units.special.ElectricPotential =
        org.kisu.units.special.ElectricPotential(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.ElectricPotential
    ): org.kisu.units.special.Capacitance =
        org.kisu.units.special.Capacitance(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Volume
    ): org.kisu.units.electromagnetic.ElectricChargeDensity =
        org.kisu.units.electromagnetic.ElectricChargeDensity(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.Elastance
    ): org.kisu.units.special.ElectricPotential =
        org.kisu.units.special.ElectricPotential(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.ElectricPotential
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the unit **coulomb** (`C`), used to express [ElectricCharge].
 *
 * A coulomb quantifies the amount of electric charge. One coulomb is the charge
 * transported by a current of one ampere flowing for one second.
 *
 * This unit appears in electrostatics, capacitor calculations, battery analysis, and
 * any context where the quantity of charge itself matters rather than only current or
 * voltage.
 *
 * In unit form, `C = A·s`.
 *
 * @see ElectricCharge
 * @see Volt
 * @see Farad
 */
class Coulomb private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Coulomb>(algebra, prefix, unit, ::Coulomb) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for coulomb: "C". */
        internal val UNIT = Unit("C", 1)
    }
}
