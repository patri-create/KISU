package org.kisu.units.kinematics.angular

import org.kisu.units.Measure
import org.kisu.units.base.SecondSextic
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian
import java.math.BigDecimal

typealias RadianPerSecondSixth = Quotient<Radian, SecondSextic>

/**
 * Represents the physical quantity of **angular pop**, the sixth derivative of angular position with respect to time.
 *
 * Angular pop quantifies how the **angular crackle** (fifth derivative of angular position) changes over time.
 * Its SI unit is the **radian per second to the sixth (rad/s⁶)**, represented here by [RadianPerSecondSixth].
 *
 * Typical applications include:
 * - Advanced robotics for precise rotational trajectory planning
 * - High-fidelity rotational physics simulations
 * - Systems requiring control over higher-order derivatives of angular motion for smoothness or stability
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [AngularPop] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([RadianPerSecondSixth]).
 *
 * @property magnitude The numeric value of the angular pop.
 * @property expression The unit expression of the angular pop, always [RadianPerSecondSixth].
 */
class Pop internal constructor(
    magnitude: BigDecimal,
    expression: RadianPerSecondSixth
) : Measure<RadianPerSecondSixth, Pop>(magnitude, expression, ::Pop)
