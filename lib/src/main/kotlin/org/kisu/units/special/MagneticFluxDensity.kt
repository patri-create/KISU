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
 * Represents the physical quantity of **magnetic flux density**, measured in [Tesla].
 *
 * Magnetic flux density quantifies how concentrated a magnetic field is over an area.
 * It is the quantity commonly denoted by `B` in electromagnetism and is especially
 * useful when describing field strength inside magnets, coils, and magnetic materials.
 *
 * Typical examples include Earth's magnetic field, the field inside an MRI scanner, or
 * the flux density in a motor or transformer core.
 *
 * The canonical SI unit is the [Tesla] (`T`), with `mT` and `µT` being common in
 * practice.
 */
class MagneticFluxDensity internal constructor(magnitude: Magnitude, expression: Tesla) :
    Measure<Tesla, MagneticFluxDensity>(magnitude, expression, ::MagneticFluxDensity) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Tesla(prefix))

    // Dimension-aware arithmetic
    /**
     * Multiplies this [MagneticFluxDensity] by [Length][org.kisu.units.base.Length],
     * yielding [MagneticRigidity][org.kisu.units.electromagnetic.MagneticRigidity].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.electromagnetic.MagneticRigidity =
        org.kisu.units.electromagnetic.MagneticRigidity(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [MagneticFluxDensity] by [Area][org.kisu.units.special.Area],
     * yielding [MagneticFlux][org.kisu.units.special.MagneticFlux].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.Area
    ): org.kisu.units.special.MagneticFlux =
        org.kisu.units.special.MagneticFlux(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the unit **tesla** (`T`), used to express [MagneticFluxDensity].
 *
 * A tesla quantifies magnetic flux density, describing how concentrated a magnetic
 * field is over an area. One tesla means one [Weber] of magnetic flux through one
 * [SquareMetre].
 *
 * This unit is used for MRI systems, permanent magnets, electric motors, particle
 * beam systems, and general electromagnetic field analysis.
 *
 * In unit form, `T = Wb/m² = m⁻²·kg·s⁻²·A⁻¹`.
 *
 * @see MagneticFluxDensity
 * @see Weber
 * @see SquareMetre
 */
class Tesla private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Tesla>(algebra, prefix, unit, ::Tesla) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for tesla: "T". */
        internal val UNIT = Unit("T", 1)
    }
}
