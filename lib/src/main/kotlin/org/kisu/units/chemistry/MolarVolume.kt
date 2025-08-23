package org.kisu.units.chemistry

import org.kisu.units.Measure
import org.kisu.units.base.Mol
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import java.math.BigDecimal

/**
 * Represents the SI unit **cubic metre per mole (m³/mol)**.
 *
 * This unit measures **molar volume**, i.e., the volume occupied by one mole
 * of a substance.
 * It is defined as the [Quotient] of [CubicMetre] (volume) divided by [Mol] (amount of substance).
 *
 * Example usages include:
 * - Calculating the volume of gases using the ideal gas law
 * - Determining molar volumes of liquids and solids
 * - Chemical and thermodynamic calculations
 *
 * @see MolarVolume for the physical quantity represented by this unit.
 */
typealias CubicMetrePerMole = Quotient<CubicMetre, Mol>

/**
 * Represents the physical quantity of **molar volume**.
 *
 * Molar volume quantifies the **volume occupied by one mole of a substance**.
 * Its SI unit is the **cubic metre per mole (m³/mol)**, represented here by [CubicMetrePerMole].
 *
 * Example usages include:
 * - Calculating the volume of gases using the ideal gas law
 * - Determining molar volumes of liquids and solids
 * - Chemical and thermodynamic calculations
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision.
 * Instances of [MolarVolume] are immutable, and the [expression] parameter ties the measurement
 * to its unit representation ([CubicMetrePerMole]).
 *
 * @property magnitude The numeric value of the molar volume.
 * @property expression The unit expression of the molar volume, always [CubicMetrePerMole].
 */
class MolarVolume(
    magnitude: BigDecimal,
    expression: CubicMetrePerMole
) : Measure<CubicMetrePerMole, MolarVolume>(magnitude, expression, ::MolarVolume)
