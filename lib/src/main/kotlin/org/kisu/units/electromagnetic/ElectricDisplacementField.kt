package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Coulomb
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

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
) : Measure<CoulombPerSquareMetre, ElectricDisplacementField>(magnitude, expression, ::ElectricDisplacementField) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Coulomb(prefix), SquareMetre()))
}
