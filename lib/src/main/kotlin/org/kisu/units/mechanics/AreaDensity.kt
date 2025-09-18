package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

/**
 * Measure of area density expressed in [KilogramPerSquareMetre].
 *
 * Area density quantifies the amount of mass distributed over a given area.
 *
 * Common applications include:
 * - Material science (sheet metal thickness and weight)
 * - Civil engineering (surface density of soil layers)
 * - Textile and paper industries (fabric or paper weight per area)
 *
 * @property magnitude Numerical value of the area density.
 * @property expression Unit of the area density, here [KilogramPerSquareMetre].
 *
 * @see KilogramPerSquareMetre
 */
class AreaDensity(
    magnitude: BigDecimal,
    expression: KilogramPerSquareMetre
) : Measure<AreaDensity.KilogramPerSquareMetre, AreaDensity>(magnitude, expression, ::AreaDensity) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, KilogramPerSquareMetre(prefix))

    /**
     * Unit of [AreaDensity].
     *
     * Represents the unit of **area density**, i.e., the physical quantity measuring
     * mass per unit area.
     *
     * Symbol: `kg/m²`
     * SI: `kg·m⁻²`
     *
     * @see AreaDensity
     */
    typealias KilogramPerSquareMetre = Quotient<Kilogram, SquareMetre>

    companion object {
        /**
         * Creates a measure of **kilograms per square metre** (kg/m²).
         *
         * This derived unit expresses **surface mass density** —
         * how much mass is distributed per unit area.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Kilogram] (mass) with the specified [prefix]
         *  - divided by a [SquareMetre] (area)
         *
         * @param prefix Metric prefix to apply to the kilogram unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [KilogramPerSquareMetre] representing kg/m².
         */
        @Suppress("FunctionNaming")
        internal fun KilogramPerSquareMetre(prefix: Metric = Metric.BASE): KilogramPerSquareMetre =
            Quotient(Kilogram(prefix), SquareMetre())
    }
}
