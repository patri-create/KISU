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
 * Represents the physical quantity of **electrical elastance**, measured in [InverseFarad].
 *
 * Elastance quantifies the inverse of capacitance: a larger elastance means less charge
 * is stored for a given voltage. It is useful in circuit models where reciprocal
 * capacitance is the natural parameter.
 *
 * The associated unit representation is [InverseFarad] (`F⁻¹`).
 */
class Elastance internal constructor(magnitude: Magnitude, expression: InverseFarad) :
    Measure<InverseFarad, Elastance>(magnitude, expression, ::Elastance) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, InverseFarad(prefix))

    /**
     * Returns the capacitance associated with this elastance by inverting its canonical magnitude.
     */
    val capacitance: Capacitance
        get() = Capacitance(canonical.component1().inverted)

    // Dimension-aware arithmetic
    operator fun times(
        other: org.kisu.units.special.ElectricCharge
    ): org.kisu.units.special.ElectricPotential =
        org.kisu.units.special.ElectricPotential(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the unit **reciprocal farad** (`F⁻¹`), used to express [Elastance].
 *
 * Reciprocal farads are the direct inverse of farads and therefore describe the inverse
 * of electrical capacitance.
 *
 * In this library, [InverseFarad] is defined as the inverse of [Farad.UNIT].
 *
 * @see Elastance
 * @see Farad
 */
class InverseFarad private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, InverseFarad>(algebra, prefix, unit, ::InverseFarad) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for reciprocal farad: "F⁻¹". */
        internal val UNIT = Farad.UNIT.inverted
    }
}
