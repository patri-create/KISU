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
        this(magnitude, Quotient(Kilogram(prefix to BigDecimal.ONE), SquareMetre()))

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
}
