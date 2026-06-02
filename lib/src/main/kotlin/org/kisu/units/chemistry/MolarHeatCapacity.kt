package org.kisu.units.chemistry

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kelvin
import org.kisu.units.base.Mole
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import java.math.BigDecimal

/**
 * Represents the physical quantity of **molar heat capacity**, measured in
 * [JoulePerKelvinMole].
 *
 * Molar heat capacity quantifies how much heat must be supplied to raise the
 * temperature of one mole of a substance by one kelvin. It links thermal behavior to
 * amount of substance rather than to total mass.
 *
 * This quantity is widely used in thermodynamics, physical chemistry, and materials
 * science when comparing substances on a molar basis.
 *
 * The associated SI unit representation is [JoulePerKelvinMole] (`J/(K·mol)`).
 */
class MolarHeatCapacity(
    magnitude: BigDecimal,
    expression: JoulePerKelvinMole
) : Measure<MolarHeatCapacity.JoulePerKelvinMole, MolarHeatCapacity>(
    magnitude = magnitude,
    expression = expression,
    create = ::MolarHeatCapacity
) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(
            magnitude,
            JoulePerKelvinMole(prefix)
        )

    /**
     * Represents the SI unit **joule per kelvin mole (J/(K·mol))**.
     *
     * This unit measures **molar heat capacity**, i.e., the amount of heat required
     * to raise the temperature of one mole of a substance by one kelvin.
     * It is defined as the [Quotient] of [Joule] (energy) divided by the [Product] of
     * [Kelvin] (temperature) and [Mole] (amount of substance).
     *
     * Example usages include:
     * - Determining the molar heat capacity of water (~75.3 J/(K·mol))
     * - Thermodynamic calculations in chemistry and materials science
     * - Analyzing energy changes per mole during temperature variations
     *
     * @see MolarHeatCapacity for the physical quantity represented by this unit.
     */
    typealias JoulePerKelvinMole = Quotient<Joule, Product<Kelvin, Mole>>

    companion object {
        /**
         * Creates a [JoulePerKelvinMole] expression for **joule per kelvin mole** (`J/(K·mol)`).
         *
         * @param prefix Metric prefix applied to the joule unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [JoulePerKelvinMole] expression for `J/(K·mol)`.
         */
        @Suppress("FunctionNaming")
        internal fun JoulePerKelvinMole(prefix: Metric = Metric.BASE): JoulePerKelvinMole =
            Quotient(
                Joule(prefix),
                Product(Kelvin(), Mole())
            )
    }
}
