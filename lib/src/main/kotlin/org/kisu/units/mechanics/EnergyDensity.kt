package org.kisu.units.mechanics

import org.kisu.units.Measure
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import org.kisu.units.special.Joule
import java.math.BigDecimal

/**
 * Unit of [EnergyDensity].
 *
 * Represents the unit of **energy density**, i.e., the physical quantity measuring
 * energy per unit volume.
 *
 * Symbol: `J/m³`
 * SI: `kg·m⁻¹·s⁻²`
 *
 * @see EnergyDensity
 */
typealias JoulePerCubicMetre = Quotient<Joule, CubicMetre>

/**
 * Measure of energy density expressed in [JoulePerCubicMetre].
 *
 * Energy density quantifies how much energy is stored or contained in a given volume.
 *
 * Common applications include:
 * - Material science (energy stored in fuels or batteries)
 * - Thermodynamics (energy content of gases or liquids)
 * - Electromagnetism (energy density in electric and magnetic fields)
 *
 * @property magnitude Numerical value of the energy density.
 * @property expression Unit of the energy density, here [JoulePerCubicMetre].
 *
 * @see JoulePerCubicMetre
 */
class EnergyDensity(
    magnitude: BigDecimal,
    expression: JoulePerCubicMetre
) : Measure<JoulePerCubicMetre, EnergyDensity>(magnitude, expression, ::EnergyDensity)
