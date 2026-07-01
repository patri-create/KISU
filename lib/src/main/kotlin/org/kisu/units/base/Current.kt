@file:Suppress("TooManyFunctions")

package org.kisu.units.base

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents the physical quantity of **electric current**, measured in amperes (A).
 *
 * Electric current quantifies the flow of electric charge over time.
 * One ampere corresponds to one coulomb of charge passing through a point in a circuit per second.
 *
 * This quantity is one of the seven SI base units and is typically used to describe the intensity of electrical flow
 * in conductors, circuits, and electromagnetic systems.
 *
 * This class expresses current as a combination of a `magnitude` and an `expression`, supporting values such as
 * milliamperes (mA), microamperes (µA), or kiloamperes (kA).
 *
 * Instances of this class are immutable and use [Magnitude] for precision.
 */
class Current internal constructor(magnitude: Magnitude, expression: Ampere) :
    Measure<Ampere, Current>(magnitude, expression, ::Current) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Ampere(prefix))

    // Dimension-aware arithmetic
    /**
     * Divides this [Current] by [Length][org.kisu.units.base.Length],
     * yielding [Magnetization][org.kisu.units.electromagnetic.Magnetization].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.electromagnetic.Magnetization =
        org.kisu.units.electromagnetic.Magnetization(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Current] by [ElectricCurrentDensity][org.kisu.units.electromagnetic.ElectricCurrentDensity],
     * yielding [Area][org.kisu.units.special.Area].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.electromagnetic.ElectricCurrentDensity
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Current] by [MagneticReluctance][org.kisu.units.electromagnetic.MagneticReluctance],
     * yielding [MagneticFlux][org.kisu.units.special.MagneticFlux].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.electromagnetic.MagneticReluctance
    ): org.kisu.units.special.MagneticFlux =
        org.kisu.units.special.MagneticFlux(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Current] by [Magnetization][org.kisu.units.electromagnetic.Magnetization],
     * yielding [Length][org.kisu.units.base.Length].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.electromagnetic.Magnetization
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Current] by [Area][org.kisu.units.special.Area],
     * yielding [ElectricCurrentDensity][org.kisu.units.electromagnetic.ElectricCurrentDensity].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Area
    ): org.kisu.units.electromagnetic.ElectricCurrentDensity =
        org.kisu.units.electromagnetic.ElectricCurrentDensity(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Current] by [Conductance][org.kisu.units.special.Conductance],
     * yielding [ElectricPotential][org.kisu.units.special.ElectricPotential].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Conductance
    ): org.kisu.units.special.ElectricPotential =
        org.kisu.units.special.ElectricPotential(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Current] by [ElectricPotential][org.kisu.units.special.ElectricPotential],
     * yielding [Conductance][org.kisu.units.special.Conductance].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.ElectricPotential
    ): org.kisu.units.special.Conductance =
        org.kisu.units.special.Conductance(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Current] by [MagneticFlux][org.kisu.units.special.MagneticFlux],
     * yielding [MagneticReluctance][org.kisu.units.electromagnetic.MagneticReluctance].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.MagneticFlux
    ): org.kisu.units.electromagnetic.MagneticReluctance =
        org.kisu.units.electromagnetic.MagneticReluctance(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [Current] by [Time][org.kisu.units.base.Time],
     * yielding [ElectricCharge][org.kisu.units.special.ElectricCharge].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Time
    ): org.kisu.units.special.ElectricCharge =
        org.kisu.units.special.ElectricCharge(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Current] by [Area][org.kisu.units.special.Area],
     * yielding [MagneticDipoleMoment][org.kisu.units.electromagnetic.MagneticDipoleMoment].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.Area
    ): org.kisu.units.electromagnetic.MagneticDipoleMoment =
        org.kisu.units.electromagnetic.MagneticDipoleMoment(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Current] by [ElectricPotential][org.kisu.units.special.ElectricPotential],
     * yielding [Power][org.kisu.units.special.Power].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.ElectricPotential
    ): org.kisu.units.special.Power =
        org.kisu.units.special.Power(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Current] by [Inductance][org.kisu.units.special.Inductance],
     * yielding [MagneticFlux][org.kisu.units.special.MagneticFlux].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.Inductance
    ): org.kisu.units.special.MagneticFlux =
        org.kisu.units.special.MagneticFlux(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Current] by [MagneticFlux][org.kisu.units.special.MagneticFlux],
     * yielding [Energy][org.kisu.units.special.Energy].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.MagneticFlux
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Current] by [PlaneAngle][org.kisu.units.special.PlaneAngle],
     * yielding [MagnetomotiveForce][org.kisu.units.electromagnetic.MagnetomotiveForce].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.PlaneAngle
    ): org.kisu.units.electromagnetic.MagnetomotiveForce =
        org.kisu.units.electromagnetic.MagnetomotiveForce(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Current] by [Resistance][org.kisu.units.special.Resistance],
     * yielding [ElectricPotential][org.kisu.units.special.ElectricPotential].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.Resistance
    ): org.kisu.units.special.ElectricPotential =
        org.kisu.units.special.ElectricPotential(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the SI base unit of **electric current**.
 *
 * The ampere (A) is the standard unit for measuring electric current.
 */
class Ampere private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Ampere>(algebra, prefix, unit, ::Ampere) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical SI symbol for electric current: "A". */
        internal val UNIT = Unit("A", 1)
    }
}
