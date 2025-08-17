package org.kisu.units.kinematics.linear

import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.SecondQuartic
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

typealias MetrePerSecondFourth = Quotient<Metre, SecondQuartic>

/**
 * Represents the physical quantity of **snap**, the fourth derivative of position with respect to time.
 *
 * Snap quantifies how the **jerk** (third derivative of position) changes over time.
 * Its SI unit is the **metre per second to the fourth (m/s⁴)**, represented here by [MetrePerSecondFourth].
 *
 * Snap is mainly used in advanced kinematics and precise motion analysis, such as:
 * - Robotics and trajectory planning, to ensure smooth higher-order motion profiles
 * - Physics simulations requiring accurate modeling of position derivatives beyond acceleration
 * - Systems where control over the change of jerk is necessary for stability or comfort
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [Snap] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([MetrePerSecondFourth]).
 *
 * @property magnitude The numeric value of the snap.
 * @property expression The unit expression of the snap, always [MetrePerSecondFourth].
 */
class Snap(
    magnitude: BigDecimal,
    expression: MetrePerSecondFourth
) : Measure<MetrePerSecondFourth, Snap>(magnitude, expression, ::Snap)
