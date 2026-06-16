package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre

/**
 * Represents the physical quantity of **area density**, measured in
 * [KilogramPerSquareMetre].
 *
 * Area density quantifies how much mass is distributed across a surface. It is useful
 * whenever mass is naturally associated with sheets, layers, or coatings rather than
 * with bulk volume.
 *
 * Typical examples include fabric weight, paper grammage, roofing membranes, and soil
 * or snow loading per ground area.
 *
 * The associated unit representation is [KilogramPerSquareMetre] (`kg/m²`).
 */
class AreaDensity(
    magnitude: Magnitude,
    expression: KilogramPerSquareMetre
) : Measure<AreaDensity.KilogramPerSquareMetre, AreaDensity>(magnitude, expression, ::AreaDensity) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
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
         * Creates a [KilogramPerSquareMetre] expression for **kilogram per square metre** (`kg/m²`).
         *
         * @param prefix Metric prefix applied to the kilogram unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [KilogramPerSquareMetre] expression for `kg/m²`.
         */
        @Suppress("FunctionNaming")
        internal fun KilogramPerSquareMetre(prefix: Metric = Metric.BASE): KilogramPerSquareMetre =
            Quotient(Kilogram(prefix), SquareMetre())
    }
}
