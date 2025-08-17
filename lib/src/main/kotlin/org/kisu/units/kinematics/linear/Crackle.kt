package org.kisu.units.kinematics.linear

import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.SecondQuintic
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

typealias MetrePerSecondFifth = Quotient<Metre, SecondQuintic>

/**
 * Represents the physical quantity of **crackle**, the fifth derivative of position with respect to time.
 *
 * Crackle quantifies how the **jerk (third derivative of position)** changes over time.
 * Its SI unit is the **metre per second to the fifth (m/s⁵)**, represented here by [MetrePerSecondFifth].
 *
 * This quantity is mainly used in higher-order kinematics and precise motion analysis, such as:
 * - Advanced robotics and trajectory planning
 * - Smooth motion control systems
 * - Physics simulations requiring higher-order derivatives of position
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [Crackle] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([MetrePerSecondFifth]).
 *
 * @property magnitude The numeric value of the crackle.
 * @property expression The unit expression of the crackle, always [MetrePerSecondFifth].
 */
class Crackle internal constructor(
    magnitude: BigDecimal,
    expression: MetrePerSecondFifth
) : Measure<MetrePerSecondFifth, Crackle>(magnitude, expression, ::Crackle)
