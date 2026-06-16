package org.kisu.units.chemistry

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Mole
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule

/**
 * Represents the physical quantity of **molar energy**, measured in [JoulePerMole].
 *
 * Molar energy quantifies how much energy is associated with one mole of substance. It
 * is a natural way to express reaction enthalpies, formation energies, phase-change
 * energies, and other thermodynamic quantities tied to amount of substance.
 *
 * Typical examples include the enthalpy of combustion of a fuel, the free energy of
 * formation of a compound, or the energy change accompanying vaporization.
 *
 * The associated SI unit representation is [JoulePerMole] (`J/mol`).
 */
class MolarEnergy(
    magnitude: Magnitude,
    expression: JoulePerMole
) : Measure<MolarEnergy.JoulePerMole, MolarEnergy>(magnitude, expression, ::MolarEnergy) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, JoulePerMole(prefix))

    /**
     * Represents the SI unit **joule per mole (J/mol)**.
     *
     * This unit measures **molar energy**, i.e., the amount of energy associated
     * with one mole of a substance.
     * It is defined as the [Quotient] of [Joule] (energy) divided by [Mole] (amount of substance).
     *
     * Example usages include:
     * - Enthalpy of reaction (ΔH, in J/mol)
     * - Gibbs free energy (ΔG, in J/mol)
     * - Energy per mole for phase transitions or chemical reactions
     *
     * @see MolarEnergy for the physical quantity represented by this unit.
     */
    typealias JoulePerMole = Quotient<Joule, Mole>

    companion object {
        /**
         * Creates a [JoulePerMole] expression for **joule per mole** (`J/mol`).
         *
         * @param prefix Metric prefix applied to the joule unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [JoulePerMole] expression for `J/mol`.
         */
        @Suppress("FunctionNaming")
        internal fun JoulePerMole(prefix: Metric = Metric.BASE): JoulePerMole =
            Quotient(Joule(prefix), Mole())
    }
}
