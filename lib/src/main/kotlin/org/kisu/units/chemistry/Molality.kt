@file:Suppress("TooManyFunctions")

package org.kisu.units.chemistry

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.base.Mole
import org.kisu.units.representation.Quotient

/**
 * Represents the physical quantity of **molality**, measured in [MolePerKilogram].
 *
 * Molality quantifies amount of solute per unit mass of solvent. Unlike [Molarity], it
 * does not depend on the total solution volume, so it remains especially useful when
 * temperature or pressure changes would make volume-based concentration less stable.
 *
 * This quantity is common in solution chemistry, thermodynamics, and colligative
 * property calculations such as boiling-point elevation or freezing-point depression.
 *
 * The associated SI unit representation is [MolePerKilogram] (`mol/kg`).
 */
class Molality(
    magnitude: Magnitude,
    expression: MolePerKilogram
) : Measure<Molality.MolePerKilogram, Molality>(magnitude, expression, ::Molality) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, MolPerKilogram(prefix))

    /**
     * Returns the molar mass associated with this molality by inverting its canonical magnitude.
     */
    val molarMass: MolarMass
        get() = MolarMass(canonical.component1().inverted)

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
    typealias MolePerKilogram = Quotient<Mole, Kilogram>

    companion object {
        /**
         * Creates a [MolePerKilogram] expression for **mole per kilogram** (`mol/kg`).
         *
         * @param prefix Metric prefix applied to the mole unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [MolePerKilogram] expression for `mol/kg`.
         */
        @Suppress("FunctionNaming")
        internal fun MolPerKilogram(prefix: Metric = Metric.BASE): MolePerKilogram =
            Quotient(Mole(prefix), Kilogram())
    }

    // Dimension-aware arithmetic
    operator fun times(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.base.Amount =
        org.kisu.units.base.Amount(canonical.component1() * other.canonical.component1())
}
