package org.kisu.units.chemistry

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.base.Mole
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

/**
 * Represents the physical quantity of **molality**.
 *
 * Molality quantifies the **amount of substance of solute per unit mass of solvent**.
 * Unlike molarity, it does not depend on volume (and therefore is unaffected by temperature
 * or pressure changes), making it particularly useful in thermodynamic calculations.
 * Its SI unit is the **mole per kilogram (mol/kg)**, represented here by [MolPerKilogram].
 *
 * Example usages include:
 * - Expressing the concentration of a solute in a solution
 * - Thermodynamic property calculations (e.g., colligative properties)
 * - Chemistry and chemical engineering applications
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision.
 * Instances of [Molality] are immutable, and the [expression] parameter ties
 * the measurement to its unit representation ([MolPerKilogram]).
 *
 * @property magnitude The numeric value of the molality.
 * @property expression The unit expression of the molality, always [MolPerKilogram].
 */
class Molality(
    magnitude: BigDecimal,
    expression: MolPerKilogram
) : Measure<Molality.MolPerKilogram, Molality>(magnitude, expression, ::Molality) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, MolPerKilogram(prefix))

    /**
     * Represents the SI unit **mole per kilogram (mol/kg)**.
     *
     * This unit measures **molality**, i.e., the amount of substance per unit mass
     * of solvent.
     * It is defined as the [Quotient] of [Mole] (amount of substance) divided by [Kilogram] (mass).
     *
     * Example usages include:
     * - Expressing solute concentration in chemistry and chemical engineering
     * - Thermodynamic calculations involving colligative properties
     *
     * @see Molality for the physical quantity represented by this unit.
     */
    typealias MolPerKilogram = Quotient<Mole, Kilogram>

    companion object {
        /**
         * Creates a measure of **moles per kilogram** (mol/kg).
         *
         * This derived unit is commonly used in chemistry to express
         * the amount of substance per unit mass of a solvent or solution.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Mole] (amount of substance) with the specified [prefix]
         *  - divided by a [Kilogram] (mass)
         *
         * @param prefix Metric prefix to apply to the mole unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [Quotient] representing mol/kg.
         */
        @Suppress("FunctionNaming")
        internal fun MolPerKilogram(prefix: Metric = Metric.BASE): Quotient<Mole, Kilogram> =
            Quotient(Mole(prefix), Kilogram())
    }
}
