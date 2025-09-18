package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import java.math.BigDecimal

/**
 * Measure of density expressed in [KilogramPerCubicMetre].
 *
 * Density quantifies how much mass is contained in a given volume of a substance.
 *
 * Common applications include:
 * - Material science (characterizing solids, liquids, and gases)
 * - Fluid mechanics (buoyancy and flow calculations)
 * - Geophysics (rock and soil density measurements)
 *
 * @property magnitude Numerical value of the density.
 * @property expression Unit of the density, here [KilogramPerCubicMetre].
 *
 * @see KilogramPerCubicMetre
 */
class Density(
    magnitude: BigDecimal,
    expression: KilogramPerCubicMetre
) : Measure<Density.KilogramPerCubicMetre, Density>(magnitude, expression, ::Density) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
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
         * Creates a measure of **kilograms per cubic metre** (kg/m³).
         *
         * This derived unit expresses **mass density** —
         * how much mass is contained per unit volume.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Kilogram] (mass) with the specified [prefix]
         *  - divided by a [CubicMetre] (volume)
         *
         * @param prefix Metric prefix to apply to the kilogram unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [KilogramPerCubicMetre] representing kg/m³.
         */
        @Suppress("FunctionNaming")
        internal fun KilogramPerCubicMetre(prefix: Metric = Metric.BASE): KilogramPerCubicMetre =
            Quotient(Kilogram(prefix), CubicMetre())
    }
}
