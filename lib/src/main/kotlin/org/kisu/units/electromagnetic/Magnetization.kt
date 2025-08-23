package org.kisu.units.electromagnetic

import org.kisu.units.Measure
import org.kisu.units.base.Ampere
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

/**
 * Represents the SI unit **ampere per metre (A/m)**.
 *
 * This unit measures **magnetization**, i.e., the magnetic moment per unit
 * length of a material.
 * It is defined as the [Quotient] of [Ampere] (electric current) and [Metre] (length).
 *
 * Example usages include:
 * - Characterizing the magnetic response of materials
 * - Designing magnetic circuits and devices
 *
 * @see Magnetization for the physical quantity represented by this unit.
 */
typealias AmperePerMetre = Quotient<Ampere, Metre>

/**
 * Represents **magnetization** (M), a measure of the magnetic moment per unit
 * volume of a material.
 *
 * - **Dimension**: electric current per length (A/m)
 * - **SI Unit**: ampere per metre (A/m)
 *
 * Magnetization describes how strongly a material is magnetized in response
 * to an applied magnetic field. It is related to the magnetic flux density (B)
 * and the magnetic field strength (H) via:
 *
 *     B = μ₀(H + M)
 *
 * Example usages include:
 * - Characterizing ferromagnetic, paramagnetic, and diamagnetic materials
 * - Designing magnetic devices like transformers and inductors
 * - Modeling material response in electromagnetic simulations
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in ampere per metre (A/m)
 */
class Magnetization(
    magnitude: BigDecimal,
    expression: AmperePerMetre
) : Measure<AmperePerMetre, Magnetization>(magnitude, expression, ::Magnetization)
