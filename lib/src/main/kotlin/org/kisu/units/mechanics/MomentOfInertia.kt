package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.representation.Product
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

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
    magnitude: BigDecimal,
    expression: KilogramSquareMetre
) : Measure<MomentOfInertia.KilogramSquareMetre, MomentOfInertia>(
    magnitude = magnitude,
    expression = expression,
    create = ::MomentOfInertia
) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
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
         * Creates a measure of **kilogram-square metres** (kg·m²).
         *
         * This compound unit represents the product of:
         *  - a [Kilogram] (mass) with the specified [prefix]
         *  - multiplied by a [SquareMetre] (area)
         *
         * It is commonly used to express **moment of inertia** or similar
         * quantities involving mass distributed over an area.
         *
         * @param prefix Metric prefix to apply to the kilogram unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [KilogramSquareMetre] representing kg·m².
         */
        @Suppress("FunctionNaming")
        internal fun KilogramSquareMetre(prefix: Metric = Metric.BASE): KilogramSquareMetre =
            Product(Kilogram(prefix), SquareMetre())
    }
}
