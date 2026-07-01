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
 * Represents the physical quantity of **magnetic flux**, measured in [Weber].
 *
 * Magnetic flux quantifies how much magnetic field passes through a surface. It is the
 * quantity that appears naturally in electromagnetic induction: changing flux produces
 * induced voltage.
 *
 * Typical examples include the flux linked by a transformer winding, the flux through
 * a loop in a magnetic field, or the working flux inside a magnetic core.
 *
 * The canonical SI unit is the [Weber] (`Wb`), often scaled to `mWb` or `µWb`.
 */
class MagneticFlux internal constructor(magnitude: Magnitude, expression: Weber) :
    Measure<Weber, MagneticFlux>(magnitude, expression, ::MagneticFlux) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Weber(prefix))

    // Dimension-aware arithmetic
    /**
     * Divides this [MagneticFlux] by [Current][org.kisu.units.base.Current],
     * yielding [Inductance][org.kisu.units.special.Inductance].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Current
    ): org.kisu.units.special.Inductance =
        org.kisu.units.special.Inductance(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [MagneticFlux] by [Length][org.kisu.units.base.Length],
     * yielding [MagneticVectorPotential][org.kisu.units.electromagnetic.MagneticVectorPotential].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.electromagnetic.MagneticVectorPotential =
        org.kisu.units.electromagnetic.MagneticVectorPotential(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [MagneticFlux] by [Time][org.kisu.units.base.Time],
     * yielding [ElectricPotential][org.kisu.units.special.ElectricPotential].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.special.ElectricPotential =
        org.kisu.units.special.ElectricPotential(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [MagneticFlux] by [MagneticVectorPotential][org.kisu.units.electromagnetic.MagneticVectorPotential],
     * yielding [Length][org.kisu.units.base.Length].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.electromagnetic.MagneticVectorPotential
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [MagneticFlux] by [Area][org.kisu.units.special.Area],
     * yielding [MagneticFluxDensity][org.kisu.units.special.MagneticFluxDensity].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Area
    ): org.kisu.units.special.MagneticFluxDensity =
        org.kisu.units.special.MagneticFluxDensity(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [MagneticFlux] by [ElectricPotential][org.kisu.units.special.ElectricPotential],
     * yielding [Time][org.kisu.units.base.Time].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.ElectricPotential
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [MagneticFlux] by [Inductance][org.kisu.units.special.Inductance],
     * yielding [Current][org.kisu.units.base.Current].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Inductance
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [MagneticFlux] by [MagneticFluxDensity][org.kisu.units.special.MagneticFluxDensity],
     * yielding [Area][org.kisu.units.special.Area].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.MagneticFluxDensity
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [MagneticFlux] by [Current][org.kisu.units.base.Current],
     * yielding [Energy][org.kisu.units.special.Energy].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Current
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [MagneticFlux] by [Length][org.kisu.units.base.Length],
     * yielding [MagneticMoment][org.kisu.units.electromagnetic.MagneticMoment].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.electromagnetic.MagneticMoment =
        org.kisu.units.electromagnetic.MagneticMoment(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [MagneticFlux] by [MagneticReluctance][org.kisu.units.electromagnetic.MagneticReluctance],
     * yielding [Current][org.kisu.units.base.Current].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.electromagnetic.MagneticReluctance
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the unit **weber** (`Wb`), used to express [MagneticFlux].
 *
 * A weber quantifies the total magnetic field passing through a surface or circuit. It
 * is especially useful when discussing induction, where changing magnetic flux induces
 * an electromotive force.
 *
 * This unit appears in transformers, electric machines, magnetic cores, and field
 * calculations involving loops, coils, and enclosed areas.
 *
 * In unit form, `Wb = V·s = T·m² = m²·kg·s⁻²·A⁻¹`.
 *
 * @see MagneticFlux
 * @see Tesla
 * @see Volt
 */
class Weber private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Weber>(algebra, prefix, unit, ::Weber) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for weber: "Wb". */
        internal val UNIT = Unit("Wb", 1)
    }
}
