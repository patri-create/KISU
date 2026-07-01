@file:Suppress("TooManyFunctions")

package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.mechanics.EnergyFluxDensity.Companion.JoulePerSquareMetreSecond
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import org.kisu.units.special.SquareMetre

/**
 * Represents the physical quantity of **energy flux density**, measured in
 * [JoulePerSquareMetreSecond].
 *
 * Energy flux density quantifies how much energy crosses a unit area per unit time. It
 * is a transport quantity used whenever energy flow through a surface is of interest.
 *
 * Typical examples include radiative transport, wave intensity, and conductive or
 * convective energy transfer analyses.
 *
 * The associated unit representation is [JoulePerSquareMetreSecond] (`J/(m²·s)`).
 */
class EnergyFluxDensity(
    magnitude: Magnitude,
    expression: JoulePerSquareMetreSecond
) : Measure<EnergyFluxDensity.JoulePerSquareMetreSecond, EnergyFluxDensity>(
    magnitude = magnitude,
    expression = expression,
    create = ::EnergyFluxDensity
) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, JoulePerSquareMetreSecond(prefix))

    /**
     * Unit of [EnergyFluxDensity].
     *
     * Represents the unit of **energy flux density**, i.e., the physical quantity measuring
     * energy transferred per unit area per unit time.
     *
     * Symbol: `J/(m²·s)`
     * SI: `kg·s⁻³`
     *
     * @see EnergyFluxDensity
     */
    typealias JoulePerSquareMetreSecond = Quotient<Joule, Product<SquareMetre, Second>>

    companion object {
        /**
         * Creates a [JoulePerSquareMetreSecond] expression for **joule per square metre second** (`J/(m²·s)`).
         *
         * @param prefix Metric prefix applied to the joule unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [JoulePerSquareMetreSecond] expression for `J/(m²·s)`.
         */
        @Suppress("FunctionNaming")
        internal fun JoulePerSquareMetreSecond(prefix: Metric = Metric.BASE): JoulePerSquareMetreSecond =
            Quotient(Joule(prefix), Product(SquareMetre(), Second()))
    }

    // Dimension-aware arithmetic
    /**
     * Multiplies this [EnergyFluxDensity] by [Time][org.kisu.units.base.Time],
     * yielding [RadiantExposure][org.kisu.units.mechanics.RadiantExposure].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Time
    ): org.kisu.units.mechanics.RadiantExposure =
        org.kisu.units.mechanics.RadiantExposure(canonical.component1() * other.canonical.component1())
}
