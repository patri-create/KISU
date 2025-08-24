package org.kisu.units.mechanics

import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Product
import org.kisu.units.special.Newton
import java.math.BigDecimal

/**
 * Unit of [Momentum].
 *
 * Represents the unit of **momentum**, i.e., the physical quantity measuring
 * the product of mass and velocity of a body.
 *
 * Symbol: `N·s`
 * SI: `kg·m·s⁻¹`
 *
 * @see Momentum
 */
typealias NewtonSecond = Product<Newton, Second>

/**
 * Measure of momentum expressed in [NewtonSecond].
 *
 * Momentum quantifies the motion of a body, combining its mass and velocity,
 * and determines the force needed to change its motion.
 *
 * Common applications include:
 * - Classical mechanics (collisions and motion analysis)
 * - Aerospace engineering (rocket propulsion, orbital mechanics)
 * - Particle physics (momentum conservation in reactions)
 *
 * @property magnitude Numerical value of the momentum.
 * @property expression Unit of the momentum, here [NewtonSecond].
 *
 * @see NewtonSecond
 */
class Momentum(
    magnitude: BigDecimal,
    expression: NewtonSecond
) : Measure<NewtonSecond, Momentum>(magnitude, expression, ::Momentum)
