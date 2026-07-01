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
    /**
     * Divides this [ElectricCharge] by [Current][org.kisu.units.base.Current],
     * yielding [Time][org.kisu.units.base.Time].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Current
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [ElectricCharge] by [Length][org.kisu.units.base.Length],
     * yielding [LinearChargeDensity][org.kisu.units.electromagnetic.LinearChargeDensity].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.electromagnetic.LinearChargeDensity =
        org.kisu.units.electromagnetic.LinearChargeDensity(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [ElectricCharge] by [Mass][org.kisu.units.base.Mass],
     * yielding [Exposure][org.kisu.units.electromagnetic.Exposure].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.electromagnetic.Exposure =
        org.kisu.units.electromagnetic.Exposure(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [ElectricCharge] by [Time][org.kisu.units.base.Time],
     * yielding [Current][org.kisu.units.base.Current].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [ElectricCharge] by [ElectricChargeDensity][org.kisu.units.electromagnetic.ElectricChargeDensity],
     * yielding [Volume][org.kisu.units.special.Volume].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.electromagnetic.ElectricChargeDensity
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [ElectricCharge] by
     * [ElectricDisplacementField][org.kisu.units.electromagnetic.ElectricDisplacementField],
     * yielding [Area][org.kisu.units.special.Area].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.electromagnetic.ElectricDisplacementField
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [ElectricCharge] by [Exposure][org.kisu.units.electromagnetic.Exposure],
     * yielding [Mass][org.kisu.units.base.Mass].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.electromagnetic.Exposure
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [ElectricCharge] by [LinearChargeDensity][org.kisu.units.electromagnetic.LinearChargeDensity],
     * yielding [Length][org.kisu.units.base.Length].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.electromagnetic.LinearChargeDensity
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [ElectricCharge] by [Area][org.kisu.units.special.Area],
     * yielding [ElectricDisplacementField][org.kisu.units.electromagnetic.ElectricDisplacementField].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Area
    ): org.kisu.units.electromagnetic.ElectricDisplacementField =
        org.kisu.units.electromagnetic.ElectricDisplacementField(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [ElectricCharge] by [Capacitance][org.kisu.units.special.Capacitance],
     * yielding [ElectricPotential][org.kisu.units.special.ElectricPotential].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Capacitance
    ): org.kisu.units.special.ElectricPotential =
        org.kisu.units.special.ElectricPotential(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [ElectricCharge] by [ElectricPotential][org.kisu.units.special.ElectricPotential],
     * yielding [Capacitance][org.kisu.units.special.Capacitance].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.ElectricPotential
    ): org.kisu.units.special.Capacitance =
        org.kisu.units.special.Capacitance(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [ElectricCharge] by [Volume][org.kisu.units.special.Volume],
     * yielding [ElectricChargeDensity][org.kisu.units.electromagnetic.ElectricChargeDensity].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Volume
    ): org.kisu.units.electromagnetic.ElectricChargeDensity =
        org.kisu.units.electromagnetic.ElectricChargeDensity(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [ElectricCharge] by [Elastance][org.kisu.units.special.Elastance],
     * yielding [ElectricPotential][org.kisu.units.special.ElectricPotential].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.Elastance
    ): org.kisu.units.special.ElectricPotential =
        org.kisu.units.special.ElectricPotential(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [ElectricCharge] by [ElectricPotential][org.kisu.units.special.ElectricPotential],
     * yielding [Energy][org.kisu.units.special.Energy].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
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
