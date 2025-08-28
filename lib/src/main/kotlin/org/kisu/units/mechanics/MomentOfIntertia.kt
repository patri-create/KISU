package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.representation.Product
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

/**
 * Unit of [MomentOfIntertia].
 *
 * Represents the unit of **moment of inertia**, i.e., the physical quantity measuring
 * an object's resistance to rotational acceleration about an axis.
 *
 * Symbol: `kg·m²`
 * SI: `kg·m²`
 *
 * @see MomentOfIntertia
 */
typealias KilogramSquareMetre = Product<Kilogram, SquareMetre>

/**
 * Measure of moment of inertia expressed in [KilogramSquareMetre].
 *
 * Moment of inertia quantifies how mass is distributed relative to a rotation axis,
 * determining the torque required for a desired angular acceleration.
 *
 * Common applications include:
 * - Mechanical engineering (rotating machinery, flywheels)
 * - Physics (rotational dynamics of rigid bodies)
 * - Aerospace and automotive design (moment calculations for vehicles and satellites)
 *
 * @property magnitude Numerical value of the moment of inertia.
 * @property expression Unit of the moment of inertia, here [KilogramSquareMetre].
 *
 * @see KilogramSquareMetre
 */
class MomentOfIntertia(
    magnitude: BigDecimal,
    expression: KilogramSquareMetre
) : Measure<KilogramSquareMetre, MomentOfIntertia>(magnitude, expression, ::MomentOfIntertia) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Product(Kilogram(prefix to BigDecimal.ONE), SquareMetre()))
}
