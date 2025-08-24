package org.kisu.units.mechanics

import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.Second
import org.kisu.units.representation.Product
import org.kisu.units.special.Newton
import java.math.BigDecimal

/**
 * Unit of [AngularMomentum].
 *
 * Represents the unit of **angular momentum**, i.e., the physical quantity measuring
 * rotational momentum of a body.
 *
 * Symbol: `N·m·s`
 * SI: `m²·kg·s⁻¹`
 *
 * @see AngularMomentum
 */
typealias NewtonMeterSecond = Product<Newton, Product<Metre, Second>>

/**
 * Measure of angular momentum expressed in [NewtonMeterSecond].
 *
 * Angular momentum quantifies the rotational motion of a body, combining
 * its moment of inertia with angular velocity.
 *
 * Common applications include:
 * - Mechanics of rotating bodies
 * - Orbital dynamics in astronomy
 * - Quantum mechanics (orbital and spin angular momentum)
 *
 * @property magnitude Numerical value of the angular momentum.
 * @property expression Unit of the angular momentum, here [NewtonMeterSecond].
 *
 * @see NewtonMeterSecond
 */
class AngularMomentum(
    magnitude: BigDecimal,
    expression: NewtonMeterSecond
) : Measure<NewtonMeterSecond, AngularMomentum>(magnitude, expression, ::AngularMomentum)
