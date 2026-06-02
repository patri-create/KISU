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
 * Represents the physical quantity of **specific angular momentum**, measured in
 * [NewtonMetreSecondPerKilogram].
 *
 * Specific angular momentum quantifies angular momentum per unit mass. It is especially
 * useful in orbital mechanics and continuum descriptions where total mass varies or is
 * not the natural comparison basis.
 *
 * Typical examples include planetary orbits, rotating flows, and trajectory analysis in
 * astrodynamics.
 *
 * The associated unit representation is [NewtonMetreSecondPerKilogram] (`N·m·s/kg`).
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
         * Creates a [NewtonMetreSecondPerKilogram] expression for **newton metre second per kilogram** (`N·m·s/kg`).
         *
         * @param prefix Metric prefix applied to the newton unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [NewtonMetreSecondPerKilogram] expression for `N·m·s/kg`.
         */
        @Suppress("FunctionNaming")
        internal fun NetwonMetreSecondPerKilogram(prefix: Metric = Metric.BASE): NewtonMetreSecondPerKilogram =
            Quotient(
                Product(Newton(prefix), Product(Metre(), Second())),
                Kilogram()
            )
    }
}
