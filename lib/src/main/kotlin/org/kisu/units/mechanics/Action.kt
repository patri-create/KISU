package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Product
import org.kisu.units.special.Joule
import java.math.BigDecimal

/**
 * Measure of action expressed in [JouleSecond].
 *
 * Action quantifies the product of energy and the time over which it is applied.
 * It is a fundamental concept in classical and quantum mechanics.
 *
 * Common applications include:
 * - Calculating the action in Lagrangian mechanics
 * - Quantum mechanics (Planck’s constant has units of action)
 * - Dynamical systems and oscillatory motion
 *
 * @property magnitude Numerical value of the action.
 * @property expression Unit of the action, here [JouleSecond].
 *
 * @see JouleSecond
 */
class Action(
    magnitude: BigDecimal,
    expression: JouleSecond
) : Measure<Action.JouleSecond, Action>(magnitude, expression, ::Action) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
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
         * Creates a measure of **joule-seconds** (J·s).
         *
         * This compound unit represents the product of:
         *  - a [Joule] (energy) with the specified [prefix]
         *  - multiplied by a [Second] (time)
         *
         * It is commonly used to express **action** or **angular momentum**
         * (for example, Planck's constant is given in J·s).
         *
         * @param prefix Metric prefix to apply to the joule unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [JouleSecond] representing J·s.
         */
        @Suppress("FunctionNaming")
        internal fun JouleSecond(prefix: Metric = Metric.BASE): JouleSecond =
            Product(Joule(prefix), Second())
    }
}
