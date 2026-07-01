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
 * Represents the physical quantity of **electrical conductance**, measured in [Siemens].
 *
 * Conductance quantifies how readily electric current flows through a component,
 * material, or path. It is the reciprocal viewpoint of [Resistance]: larger values
 * indicate less opposition to current.
 *
 * This quantity is used for circuit elements, electrolytic solutions, semiconductor
 * materials, and transport models where current-carrying ability is the main concern.
 *
 * The canonical SI unit is the [Siemens] (`S`), often seen as `mS` or `µS` in practice.
 */
class Conductance internal constructor(magnitude: Magnitude, expression: Siemens) :
    Measure<Siemens, Conductance>(magnitude, expression, ::Conductance) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Siemens(prefix))

    /**
     * Returns the resistance associated with this conductance by inverting its canonical magnitude.
     */
    val resistance: Resistance
        get() = Resistance(canonical.component1().inverted)

    // Dimension-aware arithmetic
    /**
     * Divides this [Conductance] by [Length][org.kisu.units.base.Length],
     * yielding [ElectricConductivity][org.kisu.units.electromagnetic.ElectricConductivity].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.electromagnetic.ElectricConductivity =
        org.kisu.units.electromagnetic.ElectricConductivity(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Conductance] by [ElectricConductivity][org.kisu.units.electromagnetic.ElectricConductivity],
     * yielding [Length][org.kisu.units.base.Length].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.electromagnetic.ElectricConductivity
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [Conductance] by [ElectricPotential][org.kisu.units.special.ElectricPotential],
     * yielding [Current][org.kisu.units.base.Current].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.ElectricPotential
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the unit **siemens** (`S`), used to express [Conductance].
 *
 * A siemens quantifies how readily electric current flows through a component or
 * material. It is the reciprocal of the [Ohm]: a larger conductance means less
 * opposition to current.
 *
 * This unit is used for electrical components, electrolytic solutions, semiconductor
 * materials, and any system where ease of current flow matters more than resistance.
 *
 * In unit form, `S = 1/Ω = m⁻²·kg⁻¹·s³·A²`.
 *
 * @see Conductance
 * @see Ohm
 */
class Siemens private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Siemens>(algebra, prefix, unit, ::Siemens) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for siemens: "S". */
        internal val UNIT = Unit("S", 1)
    }
}
