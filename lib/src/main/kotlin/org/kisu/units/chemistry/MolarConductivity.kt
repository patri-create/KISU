package org.kisu.units.chemistry

import org.kisu.units.Measure
import org.kisu.units.base.Mole
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Siemens
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

/**
 * Represents the SI unit **siemens square metre per mole (S·m²/mol)**.
 *
 * This unit is used to measure **molar conductivity**, i.e., the electrical
 * conductivity of an electrolyte solution normalized by its molar concentration.
 * It is defined as the [Quotient] of the [Product] of [Siemens] (conductance) and
 * [SquareMetre] (area) divided by [Mole] (amount of substance).
 *
 * Example usages include:
 * - Determining ion mobility in electrolyte solutions
 * - Characterising strong and weak electrolytes
 * - Electrochemistry and materials science applications
 *
 * @see MolarConductivity for the physical quantity represented by this unit.
 */
typealias SiemesSquareMetrePerMole = Quotient<Product<Siemens, SquareMetre>, Mole>

/**
 * Represents the physical quantity of **molar conductivity**.
 *
 * Molar conductivity quantifies the **conductivity of an electrolyte solution,
 * normalized by its molar concentration**.
 * It indicates how effectively ions contribute to electrical conduction on a
 * per-mole basis.
 * Its SI unit is the **siemens square metre per mole (S·m²/mol)**, represented here
 * by [SiemesSquareMetrePerMole].
 *
 * Example usages include:
 * - Determining ion mobility in electrolyte solutions
 * - Characterising strong and weak electrolytes
 * - Applications in electrochemistry and materials science
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision.
 * Instances of [MolarConductivity] are immutable, and the [expression] parameter ties
 * the measurement to its unit representation ([SiemesSquareMetrePerMole]).
 *
 * @property magnitude The numeric value of the molar conductivity.
 * @property expression The unit expression of the molar conductivity, always [SiemesSquareMetrePerMole].
 */
class MolarConductivity(
    magnitude: BigDecimal,
    expression: SiemesSquareMetrePerMole
) : Measure<SiemesSquareMetrePerMole, MolarConductivity>(magnitude, expression, ::MolarConductivity)
