package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre

/**
 * Represents the physical quantity of **density**, measured in
 * [KilogramPerCubicMetre].
 *
 * Density quantifies how much mass is packed into a given volume. It is one of the most
 * widely used material descriptors in mechanics, fluid dynamics, and materials science.
 *
 * Typical examples include the density of air, water, metals, soils, and fuels, as
 * well as buoyancy and stratification calculations.
 *
 * The associated unit representation is [KilogramPerCubicMetre] (`kg/m³`).
 */
class Density(
    magnitude: Magnitude,
    expression: KilogramPerCubicMetre
) : Measure<Density.KilogramPerCubicMetre, Density>(magnitude, expression, ::Density) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, KilogramPerCubicMetre(prefix))

    /**
     * Unit of [Density].
     *
     * Represents the unit of **density**, i.e., the physical quantity measuring
     * mass per unit volume.
     *
     * Symbol: `kg/m³`
     * SI: `kg·m⁻³`
     *
     * @see Density
     */
    typealias KilogramPerCubicMetre = Quotient<Kilogram, CubicMetre>

    companion object {
        /**
         * Creates a [KilogramPerCubicMetre] expression for **kilogram per cubic metre** (`kg/m³`).
         *
         * @param prefix Metric prefix applied to the kilogram unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [KilogramPerCubicMetre] expression for `kg/m³`.
         */
        @Suppress("FunctionNaming")
        internal fun KilogramPerCubicMetre(prefix: Metric = Metric.BASE): KilogramPerCubicMetre =
            Quotient(Kilogram(prefix), CubicMetre())
    }
}
