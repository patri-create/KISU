package org.kisu.units.thermodynamics

import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.base.Kelvin
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **thermal expansion coefficient**, measured in
 * [ReciprocalKelvin].
 *
 * Thermal expansion coefficient quantifies how strongly a material's dimensions change
 * in response to temperature. It is central when predicting expansion, contraction, and
 * thermally induced stress.
 *
 * Typical examples include dimensional tolerances in structures, expansion joints,
 * heated pipelines, and material selection for precision assemblies.
 *
 * The associated SI unit representation is [ReciprocalKelvin] (`K⁻¹`).
 */
class ThermalExpansionCoefficient(
    magnitude: BigDecimal,
    expression: ReciprocalKelvin
) : Measure<ReciprocalKelvin, ThermalExpansionCoefficient>(magnitude, expression, ::ThermalExpansionCoefficient) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, ReciprocalKelvin(prefix))
}

/**
 * Represents the unit **reciprocal kelvin** (`K⁻¹`), used by
 * [ThermalExpansionCoefficient].
 *
 * Reciprocal kelvin quantifies how strongly a quantity changes for each unit change in
 * temperature. It appears naturally when temperature sits in a denominator, especially
 * in expansion laws and exponential thermodynamic relations.
 *
 * Typical examples include coefficients describing how solids expand when heated or
 * how equilibrium relations vary with temperature.
 *
 * In this library, [ReciprocalKelvin] is built by inverting [Kelvin.UNIT].
 *
 * @see ThermalExpansionCoefficient
 * @see Kelvin
 */
class ReciprocalKelvin private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit,
) : Scalar<Metric, ReciprocalKelvin>(algebra, prefix, unit, ::ReciprocalKelvin) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The SI unit for reciprocal kelvin (K⁻¹), defined as the inverse of [Kelvin.UNIT]. */
        internal val UNIT = Kelvin.UNIT.inverted
    }
}
