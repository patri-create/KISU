package org.kisu.units.electromagnetic

import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Henry
import java.math.BigDecimal

/**
 * Represents the SI unit **henry per metre (H/m)**.
 *
 * This unit measures **magnetic permeability**, i.e., the ability of a material
 * to support the formation of a magnetic field.
 * It is defined as the [Quotient] of [Henry] (inductance) and [Metre] (length).
 *
 * Example usages include:
 * - Characterizing magnetic properties of materials
 * - Designing inductors, transformers, and magnetic circuits
 *
 * @see MagneticPermittivity for the physical quantity represented by this unit.
 */
typealias HenryPerMetre = Quotient<Henry, Metre>

/**
 * Represents **magnetic permeability** (μ), a measure of a material's ability
 * to support the formation of a magnetic field within itself.
 *
 * - **Dimension**: inductance per length (H/m)
 * - **SI Unit**: henry per metre (H/m)
 *
 * Magnetic permeability quantifies how a material responds to a magnetic field,
 * influencing the relationship between the magnetic field strength (H) and the
 * magnetic flux density (B) in the material:
 *
 *     B = μ * H
 *
 * Example usages include:
 * - Characterizing ferromagnetic, paramagnetic, or diamagnetic materials
 * - Designing inductors, transformers, and electromagnetic devices
 * - Electromagnetic field simulations
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in henry per metre (H/m)
 */
class MagneticPermittivity(
    magnitude: BigDecimal,
    expression: HenryPerMetre
) : Measure<HenryPerMetre, MagneticPermittivity>(magnitude, expression, ::MagneticPermittivity)
