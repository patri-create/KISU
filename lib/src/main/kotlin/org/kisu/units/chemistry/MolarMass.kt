package org.kisu.units.chemistry

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.base.Mole
import org.kisu.units.chemistry.MolarMass.Companion.KilogramPerMole
import org.kisu.units.representation.Quotient

/**
 * Represents the physical quantity of **molar mass**, measured in [KilogramPerMole].
 *
 * Molar mass quantifies the mass associated with one mole of a substance. It provides
 * the bridge between microscopic counting in moles and macroscopic mass measured in the
 * laboratory.
 *
 * This quantity is fundamental in stoichiometry, solution preparation, and molecular
 * property calculations.
 *
 * The associated SI unit representation is [KilogramPerMole] (`kg/mol`).
 */
class MolarMass(
    magnitude: Magnitude,
    expression: KilogramPerMole
) : Measure<MolarMass.KilogramPerMole, MolarMass>(magnitude, expression, ::MolarMass) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, KilogramPerMole(prefix))

    val molality: Molality
        get() = Molality(canonical.component1().inverted)

    /**
     * Represents the SI unit **kilogram per mole (kg/mol)**.
     *
     * This unit measures **molar mass**, i.e., the mass of one mole of a substance.
     * It is defined as the [Quotient] of [Kilogram] (mass) divided by [Mole] (amount of substance).
     *
     * Example usages include:
     * - Determining the mass of one mole of water (~0.018 kg/mol)
     * - Stoichiometric calculations in chemical reactions
     * - Molecular and thermodynamic property analyses
     *
     * @see MolarMass for the physical quantity represented by this unit.
     */
    typealias KilogramPerMole = Quotient<Kilogram, Mole>

    companion object {
        /**
         * Creates a [KilogramPerMole] expression for **kilogram per mole** (`kg/mol`).
         *
         * @param prefix Metric prefix applied to the kilogram unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [KilogramPerMole] expression for `kg/mol`.
         */
        @Suppress("FunctionNaming")
        internal fun KilogramPerMole(prefix: Metric = Metric.BASE): KilogramPerMole =
            Quotient(Kilogram(prefix), Mole())
    }
}
