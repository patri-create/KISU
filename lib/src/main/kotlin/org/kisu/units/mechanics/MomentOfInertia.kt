@file:Suppress("TooManyFunctions")

package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.mechanics.MomentOfInertia.Companion.KilogramSquareMetre
import org.kisu.units.representation.Product
import org.kisu.units.special.SquareMetre

/**
 * Represents the physical quantity of **moment of inertia**, measured in
 * [KilogramSquareMetre].
 *
 * Moment of inertia quantifies how mass is distributed relative to an axis of rotation.
 * It determines how resistant a body is to angular acceleration under applied torque.
 *
 * Typical examples include flywheels, rotating machinery, vehicle dynamics, and
 * spacecraft attitude analysis.
 *
 * The associated unit representation is [KilogramSquareMetre] (`kg·m²`).
 */
class MomentOfInertia(
    magnitude: Magnitude,
    expression: KilogramSquareMetre
) : Measure<MomentOfInertia.KilogramSquareMetre, MomentOfInertia>(
    magnitude = magnitude,
    expression = expression,
    create = ::MomentOfInertia
) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, KilogramSquareMetre(prefix))

    /**
     * Unit of [MomentOfInertia].
     *
     * Represents the unit of **moment of inertia**, i.e., the physical quantity measuring
     * an object's resistance to rotational acceleration about an axis.
     *
     * Symbol: `kg·m²`
     * SI: `kg·m²`
     *
     * @see MomentOfInertia
     */
    typealias KilogramSquareMetre = Product<Kilogram, SquareMetre>

    companion object {
        /**
         * Creates a [KilogramSquareMetre] expression for **kilogram square metre** (`kg·m²`).
         *
         * @param prefix Metric prefix applied to the kilogram unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [KilogramSquareMetre] expression for `kg·m²`.
         */
        @Suppress("FunctionNaming")
        internal fun KilogramSquareMetre(prefix: Metric = Metric.BASE): KilogramSquareMetre =
            Product(Kilogram(prefix), SquareMetre())
    }

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Area
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() / other.canonical.component1())
}
