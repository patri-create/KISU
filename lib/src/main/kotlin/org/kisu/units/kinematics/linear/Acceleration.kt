@file:Suppress("TooManyFunctions")

package org.kisu.units.kinematics.linear

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.SecondSquared
import org.kisu.units.kinematics.linear.Acceleration.Companion.MetrePerSecondSquared
import org.kisu.units.representation.Quotient

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
    magnitude: Magnitude,
    expression: MetrePerSecondSquared
) : Measure<Acceleration.MetrePerSecondSquared, Acceleration>(magnitude, expression, ::Acceleration) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
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
     * - Calculating forces in Newtonian mechanics using [Newton][org.kisu.units.special.Newton] = kg·m/s²
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

    // Dimension-aware arithmetic
    /**
     * Divides this [Acceleration] by [Time][org.kisu.units.base.Time],
     * yielding [Jerk][org.kisu.units.kinematics.linear.Jerk].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.linear.Jerk =
        org.kisu.units.kinematics.linear.Jerk(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Acceleration] by [Jerk][org.kisu.units.kinematics.linear.Jerk],
     * yielding [Time][org.kisu.units.base.Time].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.kinematics.linear.Jerk
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [Acceleration] by [Mass][org.kisu.units.base.Mass],
     * yielding [Force][org.kisu.units.special.Force].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.special.Force =
        org.kisu.units.special.Force(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Acceleration] by [Time][org.kisu.units.base.Time],
     * yielding [Speed][org.kisu.units.kinematics.linear.Speed].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.linear.Speed =
        org.kisu.units.kinematics.linear.Speed(canonical.component1() * other.canonical.component1())
}
