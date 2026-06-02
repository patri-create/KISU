package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.Second
import org.kisu.units.representation.Product
import org.kisu.units.special.Newton
import java.math.BigDecimal

/**
 * Represents the physical quantity of **angular momentum**, measured in
 * [NewtonMeterSecond].
 *
 * Angular momentum quantifies rotational motion and the resistance of that motion to
 * change. It is conserved in isolated systems and therefore fundamental in mechanics
 * from spinning rotors to orbiting bodies.
 *
 * Typical examples include flywheels, planetary orbits, gyroscopic stabilization, and
 * quantum angular momentum.
 *
 * The associated unit representation is [NewtonMeterSecond] (`N·m·s`).
 */
class AngularMomentum(
    magnitude: BigDecimal,
    expression: NewtonMeterSecond
) : Measure<AngularMomentum.NewtonMeterSecond, AngularMomentum>(magnitude, expression, ::AngularMomentum) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, NewtonMeterSecond(prefix))

    /**
     * Unit of [AngularMomentum].
     *
     * Represents the unit of **angular momentum**, i.e., the physical quantity measuring
     * rotational momentum of a body.
     *
     * Symbol: `N·m·s`
     * SI: `m²·kg·s⁻¹`
     *
     * @see AngularMomentum
     */
    typealias NewtonMeterSecond = Product<Newton, Product<Metre, Second>>

    companion object {
        /**
         * Creates a [NewtonMeterSecond] expression for **newton metre second** (`N·m·s`).
         *
         * @param prefix Metric prefix applied to the newton unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [NewtonMeterSecond] expression for `N·m·s`.
         */
        @Suppress("FunctionNaming")
        internal fun NewtonMeterSecond(prefix: Metric = Metric.BASE): NewtonMeterSecond =
            Product(Newton(prefix), Product(Metre(), Second()))
    }
}
