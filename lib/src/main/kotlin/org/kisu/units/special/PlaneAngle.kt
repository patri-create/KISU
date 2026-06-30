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
 * Represents the physical quantity of **plane angle**, measured in [Radian].
 *
 * Plane angle quantifies rotation or opening in a two-dimensional plane. Even though
 * the radian is dimensionless in SI, angle remains a distinct geometric concept that
 * is indispensable in trigonometry, rotational motion, oscillations, and phase
 * analysis.
 *
 * Typical examples include the rotation of a wheel, the angular position of a pendulum,
 * the phase of a wave, or the orientation of a robotic arm.
 *
 * The canonical SI unit is the [Radian] (`rad`), often written in `mrad` for small
 * angular deviations.
 */
class PlaneAngle internal constructor(magnitude: Magnitude, expression: Radian) :
    Measure<Radian, PlaneAngle>(magnitude, expression, ::PlaneAngle) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Radian(prefix))

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.angular.Velocity =
        org.kisu.units.kinematics.angular.Velocity(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.kinematics.angular.Velocity
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.base.Current
    ): org.kisu.units.electromagnetic.MagnetomotiveForce =
        org.kisu.units.electromagnetic.MagnetomotiveForce(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the unit **radian** (`rad`), used to express [PlaneAngle].
 *
 * A radian quantifies plane angle through geometry: it is the angle that subtends an
 * arc whose length equals the radius of the circle. Although dimensionless in SI, it
 * is kept as a named unit because that geometric meaning is important.
 *
 * This unit is used for rotational motion, trigonometry, wave phase, angular
 * frequency, and any analysis where angle is treated mathematically rather than as a
 * visual degree measure.
 *
 * In unit form, the radian is dimensionless and can be understood as `m/m`.
 *
 * @see PlaneAngle
 * @see Steradian
 */
class Radian private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Radian>(algebra, prefix, unit, ::Radian) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for radian: "rad". */
        internal val UNIT = Unit("rad", 1)
    }
}
