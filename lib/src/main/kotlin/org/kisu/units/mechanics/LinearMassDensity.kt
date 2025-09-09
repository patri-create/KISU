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
        this(magnitude, Quotient(Kilogram(prefix to BigDecimal.ONE), Metre()))

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
}
