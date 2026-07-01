@file:Suppress("TooManyFunctions")

package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.mechanics.SpecificVolume.Companion.CubicMetrePerKilogram
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre

/**
 * Represents the physical quantity of **specific volume**, measured in
 * [CubicMetrePerKilogram].
 *
 * Specific volume quantifies how much volume is occupied per unit mass. It is the
 * reciprocal viewpoint of density and is especially common in thermodynamics and fluid
 * property tables.
 *
 * Typical examples include steam tables, gas-property analysis, and compressible-flow
 * calculations.
 *
 * The associated unit representation is [CubicMetrePerKilogram] (`m³/kg`).
 */
class SpecificVolume(
    magnitude: Magnitude,
    expression: CubicMetrePerKilogram
) : Measure<SpecificVolume.CubicMetrePerKilogram, SpecificVolume>(magnitude, expression, ::SpecificVolume) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, CubicMetrePerKilogram(prefix))

    /**
     * Returns the density associated with this specific volume by inverting its canonical magnitude.
     */
    val density: Density
        get() = Density(canonical.component1().inverted)

    /**
     * Unit of [SpecificVolume].
     *
     * Represents the unit of **specific volume**, i.e., the physical quantity measuring
     * volume per unit mass.
     *
     * Symbol: `m³/kg`
     * SI: `m³·kg⁻¹`
     *
     * @see SpecificVolume
     */
    typealias CubicMetrePerKilogram = Quotient<CubicMetre, Kilogram>

    companion object {
        /**
         * Creates a [CubicMetrePerKilogram] expression for **cubic metre per kilogram** (`m³/kg`).
         *
         * @param prefix Metric prefix applied to the cubic metre unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [CubicMetrePerKilogram] expression for `m³/kg`.
         */
        @Suppress("FunctionNaming")
        internal fun CubicMetrePerKilogram(prefix: Metric = Metric.BASE): CubicMetrePerKilogram =
            Quotient(CubicMetre(prefix), Kilogram())
    }

    // Dimension-aware arithmetic
    /**
     * Multiplies this [SpecificVolume] by [Mass][org.kisu.units.base.Mass],
     * yielding [Volume][org.kisu.units.special.Volume].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() * other.canonical.component1())
}
