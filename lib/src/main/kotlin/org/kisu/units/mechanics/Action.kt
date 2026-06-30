@file:Suppress("TooManyFunctions")

package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.mechanics.Action.Companion.JouleSecond
import org.kisu.units.representation.Product
import org.kisu.units.special.Joule

/**
 * Represents the physical quantity of **action**, measured in [JouleSecond].
 *
 * Action combines energy with time and plays a foundational role in analytical
 * mechanics and quantum theory. It is the quantity minimized or extremized in
 * variational formulations of motion.
 *
 * Typical examples include the action integral in Lagrangian mechanics and constants
 * such as Planck's constant.
 *
 * The associated unit representation is [JouleSecond] (`J·s`).
 */
class Action(
    magnitude: Magnitude,
    expression: JouleSecond
) : Measure<Action.JouleSecond, Action>(magnitude, expression, ::Action) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, JouleSecond(prefix))

    /**
     * Unit of [Action].
     *
     * Represents the unit of **action**, i.e., the physical quantity measuring
     * energy multiplied by time.
     *
     * Symbol: `J·s`
     * SI: `m²·kg·s⁻¹`
     *
     * @see Action
     */
    typealias JouleSecond = Product<Joule, Second>

    companion object {
        /**
         * Creates a [JouleSecond] expression for **joule second** (`J·s`).
         *
         * @param prefix Metric prefix applied to the joule unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [JouleSecond] expression for `J·s`.
         */
        @Suppress("FunctionNaming")
        internal fun JouleSecond(prefix: Metric = Metric.BASE): JouleSecond =
            Product(Joule(prefix), Second())
    }

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Energy
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())
}
