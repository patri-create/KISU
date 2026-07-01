@file:Suppress("TooManyFunctions")

package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.mechanics.Momentum.Companion.NewtonSecond
import org.kisu.units.representation.Product
import org.kisu.units.special.Newton

/**
 * Represents the physical quantity of **momentum**, measured in [NewtonSecond].
 *
 * Momentum quantifies the amount of motion carried by a body. Because it is conserved in
 * isolated systems, it is one of the central bookkeeping quantities of mechanics.
 *
 * Typical examples include collisions, propulsion, recoil, and particle-beam dynamics.
 *
 * The associated unit representation is [NewtonSecond].
 */
class Momentum(
    magnitude: Magnitude,
    expression: NewtonSecond
) : Measure<Momentum.NewtonSecond, Momentum>(magnitude, expression, ::Momentum) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, NewtonSecond(prefix))

    /**
     * Unit of [Momentum].
     *
     * Represents the unit of **momentum**, i.e., the physical quantity measuring
     * the product of mass and velocity of a body.
     *
     * Symbol: `N·s`
     * SI: `kg·m·s⁻¹`
     *
     * @see Momentum
     */
    typealias NewtonSecond = Product<Newton, Second>

    companion object {
        /**
         * Creates a [NewtonSecond] expression for **newton second** (`N·s`).
         *
         * @param prefix Metric prefix applied to the newton unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [NewtonSecond] expression for `N·s`.
         */
        @Suppress("FunctionNaming")
        internal fun NewtonSecond(prefix: Metric = Metric.BASE): NewtonSecond =
            Product(Newton(prefix), Second())
    }

    // Dimension-aware arithmetic
    /**
     * Divides this [Momentum] by [Mass][org.kisu.units.base.Mass],
     * yielding [Speed][org.kisu.units.kinematics.linear.Speed].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.kinematics.linear.Speed =
        org.kisu.units.kinematics.linear.Speed(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Momentum] by [Time][org.kisu.units.base.Time],
     * yielding [Force][org.kisu.units.special.Force].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.special.Force =
        org.kisu.units.special.Force(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Momentum] by [Speed][org.kisu.units.kinematics.linear.Speed],
     * yielding [Mass][org.kisu.units.base.Mass].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.kinematics.linear.Speed
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Momentum] by [Force][org.kisu.units.special.Force],
     * yielding [Time][org.kisu.units.base.Time].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Force
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [Momentum] by [Length][org.kisu.units.base.Length],
     * yielding [AngularMomentum][org.kisu.units.mechanics.AngularMomentum].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.mechanics.AngularMomentum =
        org.kisu.units.mechanics.AngularMomentum(canonical.component1() * other.canonical.component1())
}
