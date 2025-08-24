package org.kisu.units.mechanics

import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.base.Second
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

/**
 * Unit of [MassFlowRate].
 *
 * Represents the unit of **mass flow rate**, i.e., the physical quantity measuring
 * mass transported per unit time.
 *
 * Symbol: `kg/s`
 * SI: `kg·s⁻¹`
 *
 * @see MassFlowRate
 */
typealias KilogramPerSecond = Quotient<Kilogram, Second>

/**
 * Measure of mass flow rate expressed in [KilogramPerSecond].
 *
 * Mass flow rate quantifies how much mass passes through a given surface or section per unit time.
 *
 * Common applications include:
 * - Fluid and gas transport systems (pipes, ducts)
 * - Chemical and process engineering (reactor feed rates)
 * - Environmental studies (mass flux in rivers or air currents)
 *
 * @property magnitude Numerical value of the mass flow rate.
 * @property expression Unit of the mass flow rate, here [KilogramPerSecond].
 *
 * @see KilogramPerSecond
 */
class MassFlowRate(
    magnitude: BigDecimal,
    expression: KilogramPerSecond
) : Measure<KilogramPerSecond, MassFlowRate>(magnitude, expression, ::MassFlowRate)
