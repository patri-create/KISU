package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.mechanics.EnergyFluxDensity.Companion.JoulePerSquareMetreSecond
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

/**
 * Measure of energy flux density expressed in [JoulePerSquareMetreSecond].
 *
 * Energy flux density quantifies the rate at which energy passes through a unit area.
 *
 * Common applications include:
 * - Heat transfer analysis (radiative or conductive flux)
 * - Electromagnetic wave intensity calculations
 * - Energy transport in fluids and solids
 *
 * @property magnitude Numerical value of the energy flux density.
 * @property expression Unit of the energy flux density, here [JoulePerSquareMetreSecond].
 *
 * @see JoulePerSquareMetreSecond
 */
class EnergyFluxDensity(
    magnitude: BigDecimal,
    expression: JoulePerSquareMetreSecond
) : Measure<EnergyFluxDensity.JoulePerSquareMetreSecond, EnergyFluxDensity>(
    magnitude = magnitude,
    expression = expression,
    create = ::EnergyFluxDensity
) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
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
         * Creates a measure of **joules per square metre per second** (J/(m²·s)).
         *
         * This derived unit expresses **energy flux density** —
         * how much energy passes through a unit area per unit time.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Joule] (energy) with the specified [prefix]
         *  - divided by the product of a [SquareMetre] (area) and a [Second] (time)
         *
         * @param prefix Metric prefix to apply to the joule unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [JoulePerSquareMetreSecond] representing J/(m²·s).
         */
        @Suppress("FunctionNaming")
        internal fun JoulePerSquareMetreSecond(prefix: Metric = Metric.BASE): JoulePerSquareMetreSecond =
            Quotient(Joule(prefix), Product(SquareMetre(), Second()))
    }
}
