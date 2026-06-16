package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import org.kisu.units.special.Joule

/**
 * Represents the physical quantity of **energy density**, measured in
 * [JoulePerCubicMetre].
 *
 * Energy density quantifies how much energy is stored, carried, or deposited within a
 * given volume. It is useful when the spatial concentration of energy matters more than
 * the total amount alone.
 *
 * Typical examples include battery materials, fuels, compressed gases, and electric or
 * magnetic field energy in space.
 *
 * The associated unit representation is [JoulePerCubicMetre] (`J/m³`).
 */
class EnergyDensity(
    magnitude: Magnitude,
    expression: JoulePerCubicMetre
) : Measure<EnergyDensity.JoulePerCubicMetre, EnergyDensity>(magnitude, expression, ::EnergyDensity) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, JoulePerCubicMetre(prefix))

    /**
     * Unit of [EnergyDensity].
     *
     * Represents the unit of **energy density**, i.e., the physical quantity measuring
     * energy per unit volume.
     *
     * Symbol: `J/m³`
     * SI: `kg·m⁻¹·s⁻²`
     *
     * @see EnergyDensity
     */
    typealias JoulePerCubicMetre = Quotient<Joule, CubicMetre>

    companion object {
        /**
         * Creates a [JoulePerCubicMetre] expression for **joule per cubic metre** (`J/m³`).
         *
         * @param prefix Metric prefix applied to the joule unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [JoulePerCubicMetre] expression for `J/m³`.
         */
        @Suppress("FunctionNaming")
        internal fun JoulePerCubicMetre(prefix: Metric = Metric.BASE): JoulePerCubicMetre =
            Quotient(Joule(prefix), CubicMetre())
    }
}
