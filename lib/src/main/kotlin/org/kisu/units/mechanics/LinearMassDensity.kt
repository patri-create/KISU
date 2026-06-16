package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient

/**
 * Represents the physical quantity of **linear mass density**, measured in
 * [KilogramPerMetre].
 *
 * Linear mass density quantifies how much mass is distributed along a line-like object.
 * It is useful whenever length is the natural geometric reference rather than area or
 * volume.
 *
 * Typical examples include strings, cables, wires, rails, fibers, and slender
 * structural members.
 *
 * The associated unit representation is [KilogramPerMetre] (`kg/m`).
 */
class LinearMassDensity(
    magnitude: Magnitude,
    expression: KilogramPerMetre
) : Measure<LinearMassDensity.KilogramPerMetre, LinearMassDensity>(
    magnitude = magnitude,
    expression = expression,
    create = ::LinearMassDensity
) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
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
         * Creates a [KilogramPerMetre] expression for **kilogram per metre** (`kg/m`).
         *
         * @param prefix Metric prefix applied to the kilogram unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [KilogramPerMetre] expression for `kg/m`.
         */
        @Suppress("FunctionNaming")
        internal fun KilogramPerMetre(prefix: Metric = Metric.BASE): KilogramPerMetre =
            Quotient(Kilogram(prefix), Metre())
    }
}
