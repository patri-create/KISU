package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **solid angle**, measured in [Steradian].
 *
 * Solid angle quantifies angular spread in three dimensions. It plays the same role in
 * space that plane angle plays in a plane, making it essential for describing how wide
 * a cone of emission, detection, or view really is.
 *
 * Typical examples include the beam spread of a lamp, the field of view of a detector,
 * or the angular extent under which a surface is seen from a point.
 *
 * The canonical SI unit is the [Steradian] (`sr`), a named dimensionless unit.
 */
class SolidAngle internal constructor(magnitude: BigDecimal, expression: Steradian) :
    Measure<Steradian, SolidAngle>(magnitude, expression, ::SolidAngle) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Steradian(prefix))
}

/**
 * Represents the unit **steradian** (`sr`), used to express [SolidAngle].
 *
 * A steradian quantifies how wide a three-dimensional angular spread is. It is the
 * solid angle that cuts out an area on a sphere equal to the square of the sphere's
 * radius.
 *
 * This unit appears in photometry and radiometry, for example when describing the
 * spread of a lamp, LED, spotlight, or detector field of view.
 *
 * In unit form, the steradian is dimensionless and can be understood as `m²/m²`.
 *
 * @see SolidAngle
 * @see Lumen
 * @see Radian
 */
class Steradian private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Steradian>(algebra, prefix, unit, ::Steradian) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for steradian: "sr". */
        internal val UNIT = Unit("sr", 1)
    }
}
