package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

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
) : Measure<JoulePerSquareMetreSecond, EnergyFluxDensity>(magnitude, expression, ::EnergyFluxDensity) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(
            magnitude,
            Quotient(
                Joule(prefix),
                Product(SquareMetre(), Second())
            )
        )
}
