package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Coulomb
import java.math.BigDecimal

/**
 * Represents the SI unit **coulomb per kilogram (C/kg)**.
 *
 * This unit measures **radiation exposure**, i.e., the amount of ionizing
 * radiation in terms of the electric charge produced per unit mass of air.
 * It is defined as the [Quotient] of [Coulomb] (electric charge) and [Kilogram] (mass).
 *
 * Example usages include:
 * - Measuring ionizing radiation in air
 * - Radiation protection and dosimetry calculations
 *
 * @see Exposure for the physical quantity represented by this unit.
 */
typealias CoulombPerKilogram = Quotient<Coulomb, Kilogram>

/**
 * Represents **radiation exposure** (X), which quantifies the amount of ionizing
 * radiation in terms of the electric charge produced per unit mass of air.
 *
 * - **Dimension**: electric charge per mass (C/kg)
 * - **SI Unit**: coulomb per kilogram (C/kg)
 *
 * Exposure measures the ability of ionizing radiation to ionize air molecules,
 * and it is primarily used in **radiation protection** and **dosimetry**.
 * While it characterizes the intensity of radiation in air, it does not directly
 * account for the absorbed dose within human tissue.
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in coulomb per kilogram (C/kg)
 */
class Exposure(
    magnitude: BigDecimal,
    expression: CoulombPerKilogram
) : Measure<CoulombPerKilogram, Exposure>(magnitude, expression, ::Exposure) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Coulomb(prefix), Kilogram()))
}
