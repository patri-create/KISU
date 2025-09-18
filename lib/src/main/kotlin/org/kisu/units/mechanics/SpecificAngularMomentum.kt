package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.base.Metre
import org.kisu.units.base.Second
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Newton
import java.math.BigDecimal

/**
 * Measure of specific angular momentum expressed in [NewtonMetreSecondPerKilogram].
 *
 * Specific angular momentum quantifies the rotational motion of an object normalized by its mass,
 * describing how mass distribution affects angular velocity.
 *
 * Common applications include:
 * - Astrophysics (orbital mechanics of planets and stars)
 * - Classical mechanics (rotational dynamics of bodies)
 * - Engineering (rotating machinery and flywheel analysis)
 *
 * @property magnitude Numerical value of the specific angular momentum.
 * @property expression Unit of the specific angular momentum, here [NewtonMetreSecondPerKilogram].
 *
 * @see NewtonMetreSecondPerKilogram
 */
class SpecificAngularMomentum(
    magnitude: BigDecimal,
    expression: NewtonMetreSecondPerKilogram
) : Measure<SpecificAngularMomentum.NewtonMetreSecondPerKilogram, SpecificAngularMomentum>(
    magnitude,
    expression,
    ::SpecificAngularMomentum
) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, NetwonMetreSecondPerKilogram(prefix))

    /**
     * Unit of [SpecificAngularMomentum].
     *
     * Represents the unit of **specific angular momentum**, i.e., the physical quantity measuring
     * angular momentum per unit mass.
     *
     * Symbol: `N·m·s/kg`
     * SI: `m²·s⁻¹`
     *
     * @see SpecificAngularMomentum
     */
    typealias NewtonMetreSecondPerKilogram = Quotient<AngularMomentum.NewtonMeterSecond, Kilogram>

    companion object {
        /**
         * Creates a measure of **newton-metre-seconds per kilogram** (N·m·s/kg).
         *
         * This derived unit expresses a quantity combining **force, distance, and time per unit mass** —
         * it can be used in contexts such as rotational dynamics or specific mechanical impulses.
         *
         * Internally this returns a [Quotient] of:
         *  - the product of a [Newton] (force) with the specified [prefix], a [Metre] (distance), and a [Second] (time)
         *  - divided by a [Kilogram] (mass)
         *
         * @param prefix Metric prefix to apply to the newton unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [NewtonMetreSecondPerKilogram] representing N·m·s/kg.
         */
        @Suppress("FunctionNaming")
        internal fun NetwonMetreSecondPerKilogram(prefix: Metric = Metric.BASE): NewtonMetreSecondPerKilogram =
            Quotient(
                Product(Newton(prefix), Product(Metre(), Second())),
                Kilogram()
            )
    }
}
