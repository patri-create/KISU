package org.kisu.units.chemistry

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Mole
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import java.math.BigDecimal

/**
 * Represents the **molarity** (also called **molar concentration**) of a solution.
 *
 * Molarity is the number of moles of solute present in one cubic metre of solution.
 * It expresses how concentrated a substance is within a given volume of solvent.
 *
 * Expressed in **moles per cubic metre (mol·m⁻³)**.
 *
 * ## Example
 * A typical laboratory solution of sodium chloride (NaCl) might have a molarity of
 * **1000 mol·m⁻³**, which is equivalent to a 1 mol/L solution. This means the solution
 * contains one mole of NaCl dissolved in each litre of water.
 *
 * @constructor Creates a [Molarity] with the given [magnitude] and [expression].
 * @property magnitude Numerical value of the molarity.
 * @property expression The unit expression, [MolePerCubicMetre].
 */
class Molarity(
    magnitude: BigDecimal,
    expression: MolePerCubicMetre
) : Measure<Molarity.MolePerCubicMetre, Molarity>(magnitude, expression, ::Molarity) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, MolePerCubicMetre(prefix))

    /**
     * Represents the SI unit **mole per cubic metre (mol/m³)**.
     *
     * This unit measures **molarity**, i.e., the number of moles of a substance
     * present in a unit volume of solution.
     * It is defined as the [Quotient] of [Mole] (amount of substance) divided by [CubicMetre] (volume).
     *
     * Example usages include:
     * - Expressing the concentration of a solution in chemistry and chemical engineering
     * - Calculating reactant availability in volumetric reactions
     * - Thermodynamic and solution property analyses
     *
     * @see Molarity for the physical quantity represented by this unit.
     */
    typealias MolePerCubicMetre = Quotient<Mole, CubicMetre>

    companion object {
        /**
         * Creates a measure of **moles per cubic metre** (mol/m³).
         *
         * This derived unit is widely used in chemistry and physics
         * to express the amount of substance per unit volume
         * (molar concentration in SI base units).
         *
         * Internally this returns a [Quotient] of:
         *  - a [Mole] (amount of substance) with the specified [prefix]
         *  - divided by a [CubicMetre] (volume)
         *
         * @param prefix Metric prefix to apply to the mole unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [Quotient] representing mol/m³.
         */
        @Suppress("FunctionNaming")
        internal fun MolePerCubicMetre(prefix: Metric = Metric.BASE): Quotient<Mole, CubicMetre> =
            Quotient(Mole(prefix), CubicMetre())
    }
}
