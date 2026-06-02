package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre
import org.kisu.units.special.Steradian
import org.kisu.units.special.Watt
import java.math.BigDecimal

/**
 * Represents the physical quantity of **radiance**, measured in
 * [WattPerSteradianSquareMetre].
 *
 * Radiance quantifies radiant power per unit area and per unit solid angle in a given
 * direction. It is one of the most informative directional quantities in radiometry
 * because it combines emission geometry with surface distribution.
 *
 * Typical examples include thermal emission from surfaces, optical source
 * characterization, and remote-sensing observations.
 *
 * The associated unit representation is [WattPerSteradianSquareMetre].
 */
class Radiance(
    magnitude: BigDecimal,
    expression: WattPerSteradianSquareMetre
) : Measure<Radiance.WattPerSteradianSquareMetre, Radiance>(magnitude, expression, ::Radiance) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, WattPerSteradianSquareMetre(prefix))

    /**
     * Unit of [Radiance].
     *
     * Represents the unit of **radiance**, i.e., the physical quantity measuring
     * radiant power emitted per unit area per unit solid angle.
     *
     * Symbol: `W/(sr·m²)`
     * SI: `kg·s⁻³`
     *
     * @see Radiance
     */
    typealias WattPerSteradianSquareMetre = Quotient<Watt, Product<Steradian, SquareMetre>>

    companion object {
        /**
         * Creates a [WattPerSteradianSquareMetre] expression for **watt per steradian square metre** (`W/(sr·m²)`).
         *
         * @param prefix Metric prefix applied to the watt unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [WattPerSteradianSquareMetre] expression for `W/(sr·m²)`.
         */
        @Suppress("FunctionNaming")
        internal fun WattPerSteradianSquareMetre(prefix: Metric = Metric.BASE): WattPerSteradianSquareMetre =
            Quotient(Watt(prefix), Product(Steradian(), SquareMetre()))
    }
}
