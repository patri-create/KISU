package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import java.math.BigDecimal

/**
 * Measure of specific volume expressed in [CubicMetrePerKilogram].
 *
 * Specific volume quantifies the space occupied by a unit mass of a substance,
 * describing the relationship between mass and volume.
 *
 * Common applications include:
 * - Thermodynamics (gas and liquid properties)
 * - Fluid mechanics (density and compressibility analysis)
 * - Material science (characterizing porous or solid materials)
 *
 * @property magnitude Numerical value of the specific volume.
 * @property expression Unit of the specific volume, here [CubicMetrePerKilogram].
 *
 * @see CubicMetrePerKilogram
 */
class SpecificVolume(
    magnitude: BigDecimal,
    expression: CubicMetrePerKilogram
) : Measure<SpecificVolume.CubicMetrePerKilogram, SpecificVolume>(magnitude, expression, ::SpecificVolume) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, CubicMetrePerKilogram(prefix))

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
         * Creates a measure of **cubic metres per kilogram** (m³/kg).
         *
         * This derived unit expresses **specific volume** —
         * how much volume is associated with a unit mass.
         *
         * Internally this returns a [Quotient] of:
         *  - a [CubicMetre] (volume) with the specified [prefix]
         *  - divided by a [Kilogram] (mass)
         *
         * @param prefix Metric prefix to apply to the cubic metre unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [CubicMetrePerKilogram] representing m³/kg.
         */
        @Suppress("FunctionNaming")
        internal fun CubicMetrePerKilogram(prefix: Metric = Metric.BASE): CubicMetrePerKilogram =
            Quotient(CubicMetre(prefix), Kilogram())
    }
}
