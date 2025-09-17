package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Coulomb
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

/**
 * Represents the **electric displacement field (D)** in SI units.
 *
 * The electric displacement field describes how electric fields interact with
 * materials, particularly in the presence of free and bound charges.
 * It is measured in **coulombs per square metre (C/m²)**.
 *
 * Defined as a [Measure] of [CoulombPerSquareMetre], this quantity is
 * central in electromagnetism, especially in **Gauss's law for electric displacement**:
 *
 * ```
 * ∇ · D = ρ_free
 * ```
 *
 * Example usages include:
 * - Calculating field distributions in dielectric materials
 * - Modeling capacitors and polarization
 * - Solving Maxwell’s equations in matter
 *
 * @see CoulombPerSquareMetre for the unit definition.
 * @see ElectricField for the related field quantity.
 */
class ElectricDisplacementField(
    magnitude: BigDecimal,
    expression: CoulombPerSquareMetre
) : Measure<ElectricDisplacementField.CoulombPerSquareMetre, ElectricDisplacementField>(
    magnitude = magnitude,
    expression = expression,
    create = ::ElectricDisplacementField
) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, CoulombPerSquareMetre(prefix))

    /**
     * Represents the SI unit **coulomb per square metre (C/m²)**.
     *
     * This unit measures **electric displacement field**, i.e., the electric charge
     * per unit area on a surface.
     * It is defined as the [Quotient] of [Coulomb] (electric charge) and [SquareMetre] (area).
     *
     * Example usages include:
     * - Calculating surface charge distributions
     * - Modeling dielectric materials and capacitors
     *
     * @see ElectricDisplacementField for the physical quantity represented by this unit.
     */
    typealias CoulombPerSquareMetre = Quotient<Coulomb, SquareMetre>

    companion object {
        /**
         * Creates a measure of **coulombs per square metre** (C/m²).
         *
         * This derived unit expresses **surface charge density** —
         * how much electric charge (in coulombs) is distributed per unit area.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Coulomb] (electric charge) with the specified [prefix]
         *  - divided by a [SquareMetre] (area)
         *
         * @param prefix Metric prefix to apply to the coulomb unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [CoulombPerSquareMetre] representing C/m².
         */
        @Suppress("FunctionNaming")
        internal fun CoulombPerSquareMetre(prefix: Metric = Metric.BASE): CoulombPerSquareMetre =
            Quotient(Coulomb(prefix), SquareMetre())
    }
}
