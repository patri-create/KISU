package org.kisu.units.chemistry

import org.kisu.units.Measure
import org.kisu.units.base.Mol
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import java.math.BigDecimal

/**
 * Represents the SI unit **joule per mole (J/mol)**.
 *
 * This unit measures **molar energy**, i.e., the amount of energy associated
 * with one mole of a substance.
 * It is defined as the [Quotient] of [Joule] (energy) divided by [Mol] (amount of substance).
 *
 * Example usages include:
 * - Enthalpy of reaction (ΔH, in J/mol)
 * - Gibbs free energy (ΔG, in J/mol)
 * - Energy per mole for phase transitions or chemical reactions
 *
 * @see MolarEnergy for the physical quantity represented by this unit.
 */
typealias JoulePerMole = Quotient<Joule, Mol>

/**
 * Represents the physical quantity of **molar energy**.
 *
 * Molar energy quantifies the **amount of energy per mole of substance**.
 * It is commonly used in thermodynamics and chemistry to describe the energy
 * content or energy change associated with chemical reactions or phase transitions.
 * Its SI unit is the **joule per mole (J/mol)**, represented here by [JoulePerMole].
 *
 * Example usages include:
 * - Enthalpy of reaction (ΔH, in J/mol)
 * - Gibbs free energy of formation (ΔG, in J/mol)
 * - Energy per mole in phase changes (e.g., vaporization, fusion)
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision.
 * Instances of [MolarEnergy] are immutable, and the [expression] parameter ties
 * the measurement to its unit representation ([JoulePerMole]).
 *
 * @property magnitude The numeric value of the molar energy.
 * @property expression The unit expression of the molar energy, always [JoulePerMole].
 */
class MolarEnergy(
    magnitude: BigDecimal,
    expression: JoulePerMole
) : Measure<JoulePerMole, MolarEnergy>(magnitude, expression, ::MolarEnergy)
