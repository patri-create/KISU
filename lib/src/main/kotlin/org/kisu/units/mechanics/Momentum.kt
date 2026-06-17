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
}
