@file:Suppress("TooManyFunctions")

package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.mechanics.FuelEfficiency.Companion.MetrePerCubicMetre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre

/**
 * Represents the physical quantity of **fuel efficiency**, measured in
 * [MetrePerCubicMetre].
 *
 * Fuel efficiency quantifies how much distance is obtained from a given volume of fuel.
 * It expresses travel or transport performance in terms of fuel use.
 *
 * Typical examples include vehicle economy ratings, engine benchmarking, and transport
 * energy analysis.
 *
 * The associated unit representation is [MetrePerCubicMetre].
 */
class FuelEfficiency(
    magnitude: Magnitude,
    expression: MetrePerCubicMetre
) : Measure<FuelEfficiency.MetrePerCubicMetre, FuelEfficiency>(magnitude, expression, ::FuelEfficiency) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, MetrePerCubicMetre(prefix))

    /**
     * Unit of [FuelEfficiency].
     *
     * Represents the unit of **fuel efficiency**, i.e., the physical quantity measuring
     * distance traveled per unit volume of fuel consumed.
     *
     * Symbol: `m/m³`
     * SI: `m⁻²`
     *
     * @see FuelEfficiency
     */
    typealias MetrePerCubicMetre = Quotient<Metre, CubicMetre>

    companion object {
        /**
         * Creates a [MetrePerCubicMetre] expression for **metre per cubic metre** (`m/m³`).
         *
         * @param prefix Metric prefix applied to the metre unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [MetrePerCubicMetre] expression for `m/m³`.
         */
        @Suppress("FunctionNaming")
        internal fun MetrePerCubicMetre(prefix: Metric = Metric.BASE): MetrePerCubicMetre =
            Quotient(Metre(prefix), CubicMetre())
    }

    // Dimension-aware arithmetic
    /**
     * Multiplies this [FuelEfficiency] by [Volume][org.kisu.units.special.Volume],
     * yielding [Length][org.kisu.units.base.Length].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.Volume
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() * other.canonical.component1())
}
