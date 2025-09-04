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

/**
 * Creates a measure of **joules per kelvin-mole** (J/(K·mol)).
 *
 * This derived unit is used in thermodynamics to express energy,
 * heat capacity or entropy per unit temperature per amount of substance.
 *
 * Internally this returns a [Quotient] of:
 *  - a [Joule] (energy) with the specified [prefix]
 *  - divided by a [Product] of [Kelvin] (temperature)
 *    and [Mole] (amount of substance)
 *
 * @param prefix Metric prefix to apply to the joule unit.
 * Defaults to [Metric.BASE] (no prefix).
 *
 * @return A [Quotient] representing J/(K·mol).
 */
@Suppress("FunctionNaming")
internal fun JoulePerKelvinMole(prefix: Metric = Metric.BASE): Quotient<Joule, Product<Kelvin, Mole>> =
    Quotient(
        Joule(prefix),
        Product(Kelvin(), Mole())
    )

/**
 * Represents the **molar heat capacity** of a substance.
 *
 * Molar heat capacity is the amount of heat required to raise the temperature
 * of one mole of a substance by one kelvin. It combines energy, temperature,
 * and amount of substance into a single quantity.
 *
 * Expressed in **joules per kelvin mole (J·K⁻¹·mol⁻¹)**.
 *
 * ## Example
 * The molar heat capacity of liquid water at room temperature is approximately
 * **75.3 J·K⁻¹·mol⁻¹**, meaning it takes 75.3 joules of energy to increase the
 * temperature of one mole of water by one kelvin.
 *
 * @constructor Creates a [MolarHeatCapacity] with the given [magnitude] and [expression].
 * @property magnitude Numerical value of the molar heat capacity.
 * @property expression The unit expression, [JoulePerKelvinMole].
 */
class MolarHeatCapacity(
    magnitude: BigDecimal,
    expression: JoulePerKelvinMole
) : Measure<JoulePerKelvinMole, MolarHeatCapacity>(magnitude, expression, ::MolarHeatCapacity) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(
            magnitude,
            JoulePerKelvinMole(prefix)
        )
}
