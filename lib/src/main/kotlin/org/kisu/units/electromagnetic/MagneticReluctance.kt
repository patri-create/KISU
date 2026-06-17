package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.special.Henry

/**
 * Represents the physical quantity of **magnetic reluctance**, measured in
 * [ReciprocalHenry].
 *
 * Magnetic reluctance quantifies how strongly a magnetic circuit opposes the formation
 * of magnetic flux. It plays a role analogous to electrical resistance in magnetic
 * circuit models.
 *
 * Typical examples include magnetic core analysis, air-gap design, transformers, and
 * inductors.
 *
 * The associated unit representation is [ReciprocalHenry] (`H⁻¹`).
 */
class MagneticReluctance(
    magnitude: Magnitude,
    expression: ReciprocalHenry
) : Measure<ReciprocalHenry, MagneticReluctance>(magnitude, expression, ::MagneticReluctance) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, ReciprocalHenry(prefix))
}

/**
 * Represents the unit **reciprocal henry** (`H⁻¹`), used by [MagneticReluctance].
 *
 * Reciprocal henry quantifies the opposition a magnetic circuit presents to magnetic
 * flux when reluctance is expressed as the inverse of inductance.
 *
 * This unit is useful in magnetic-circuit analysis for cores, gaps, coils, and related
 * electromagnetic devices.
 *
 * In this library, [ReciprocalHenry] is defined as the inverse of [Henry.UNIT].
 *
 * @see MagneticReluctance
 * @see Henry
 */
class ReciprocalHenry private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit,
) : Scalar<Metric, ReciprocalHenry>(algebra, prefix, unit, ::ReciprocalHenry) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The SI unit for reciprocal henry, used in magnetic reluctance calculations. */
        internal val UNIT = Henry.UNIT.inverted
    }
}
