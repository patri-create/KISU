package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

/**
 * Measure of linear mass density expressed in [KilogramPerMetre].
 *
 * Linear mass density quantifies the amount of mass distributed along a line or slender object.
 *
 * Common applications include:
 * - Mechanics of strings, cables, and rods
 * - Structural engineering (beam and truss analysis)
 * - Material characterization of fibers and wires
 *
 * @property magnitude Numerical value of the linear mass density.
 * @property expression Unit of the linear mass density, here [KilogramPerMetre].
 *
 * @see KilogramPerMetre
 */
class LinearMassDensity(
    magnitude: BigDecimal,
    expression: KilogramPerMetre
) : Measure<LinearMassDensity.KilogramPerMetre, LinearMassDensity>(
    magnitude = magnitude,
    expression = expression,
    create = ::LinearMassDensity
) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, KilogramPerMetre(prefix))

    /**
     * Unit of [LinearMassDensity].
     *
     * Represents the unit of **linear mass density**, i.e., the physical quantity measuring
     * mass per unit length.
     *
     * Symbol: `kg/m`
     * SI: `kg·m⁻¹`
     *
     * @see LinearMassDensity
     */
    typealias KilogramPerMetre = Quotient<Kilogram, Metre>

    companion object {
        /**
         * Creates a measure of **kilograms per metre** (kg/m).
         *
         * This derived unit expresses **linear mass density** —
         * how much mass is distributed along a unit length.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Kilogram] (mass) with the specified [prefix]
         *  - divided by a [Metre] (length)
         *
         * @param prefix Metric prefix to apply to the kilogram unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [KilogramPerMetre] representing kg/m.
         */
        @Suppress("FunctionNaming")
        internal fun KilogramPerMetre(prefix: Metric = Metric.BASE): KilogramPerMetre =
            Quotient(Kilogram(prefix), Metre())
    }
}
