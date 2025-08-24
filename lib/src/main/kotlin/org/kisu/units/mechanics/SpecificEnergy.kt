package org.kisu.units.mechanics

import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import java.math.BigDecimal

/**
 * Unit of [SpecificEnergy].
 *
 * Represents the unit of **specific energy**, i.e., the physical quantity measuring
 * energy per unit mass.
 *
 * Symbol: `J/kg`
 * SI: `m²·s⁻²`
 *
 * @see SpecificEnergy
 */
typealias JoulePerKilogram = Quotient<Joule, Kilogram>

/**
 * Measure of specific energy expressed in [JoulePerKilogram].
 *
 * Specific energy quantifies the amount of energy contained or transferred per unit mass,
 * commonly used to describe energy content of fuels or energy absorption in materials.
 *
 * Common applications include:
 * - Thermodynamics (energy per unit mass of fluids or solids)
 * - Aerospace engineering (specific kinetic or potential energy)
 * - Material science (energy absorbed per unit mass)
 *
 * @property magnitude Numerical value of the specific energy.
 * @property expression Unit of the specific energy, here [JoulePerKilogram].
 *
 * @see JoulePerKilogram
 */
class SpecificEnergy(
    magnitude: BigDecimal,
    expression: JoulePerKilogram
) : Measure<JoulePerKilogram, SpecificEnergy>(magnitude, expression, ::SpecificEnergy)
