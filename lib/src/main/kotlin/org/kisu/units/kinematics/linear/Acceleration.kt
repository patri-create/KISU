package org.kisu.units.kinematics.linear

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.SecondSquared
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

/**
 * Represents the physical quantity of **linear acceleration**, measured in
 * [MetrePerSecondSquared].
 *
 * Acceleration quantifies how rapidly velocity changes in time. It describes speeding
 * up, slowing down, and changes in direction, making it one of the core quantities of
 * kinematics and dynamics.
 *
 * Typical examples include gravitational acceleration, vehicle launch and braking, and
 * motion of moving parts in machinery.
 *
 * The associated unit representation is [MetrePerSecondSquared] (`m/s²`).
 */
class Acceleration(
    magnitude: BigDecimal,
    expression: MetrePerSecondSquared
) : Measure<Acceleration.MetrePerSecondSquared, Acceleration>(magnitude, expression, ::Acceleration) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, MetrePerSecondSquared(prefix))

    /**
     * Represents the SI unit **metre per second squared (m/s²)**.
     *
     * This unit is used to measure **linear acceleration**,
     * i.e., the rate of change of velocity with respect to time.
     * It is defined as the [Quotient] of [Metre] (length) divided by [SecondSquared] (time²).
     *
     * Example usages include:
     * - Describing the acceleration of vehicles, projectiles, or objects in free fall
     * - Calculating forces in Newtonian mechanics using [org.kisu.units.special.Newton] = kg·m/s²
     * - Analysing motion in physics and engineering contexts
     *
     * @see Acceleration
     */
    typealias MetrePerSecondSquared = Quotient<Metre, SecondSquared>

    companion object {
        /**
         * Creates a [MetrePerSecondSquared] expression for **metre per second squared** (`m/s²`).
         *
         * @param prefix Metric prefix applied to the metre unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [MetrePerSecondSquared] expression for `m/s²`.
         */
        @Suppress("FunctionNaming")
        internal fun MetrePerSecondSquared(prefix: Metric = Metric.BASE): MetrePerSecondSquared =
            Quotient(Metre(prefix), SecondSquared())
    }
}
