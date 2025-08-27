package org.kisu.units.kinematics.angular

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.SecondQuintic
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian
import java.math.BigDecimal

typealias RadianPerSecondFifth = Quotient<Radian, SecondQuintic>

/**
 * Represents the physical quantity of **angular crackle**, the fifth derivative of angular position with respect to
 * time.
 *
 * Angular crackle quantifies how the **angular snap** (fourth derivative of angular position) changes over time.
 * Its SI unit is the **radian per second to the fifth (rad/s⁵)**, represented here by [RadianPerSecondFifth].
 *
 * Typical applications include:
 * - Advanced robotics and precise rotational motion planning
 * - High-fidelity physics simulations of rotational systems
 * - Systems where higher-order derivatives of angular motion are relevant for stability or smoothness
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [AngularCrackle] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([RadianPerSecondFifth]).
 *
 * @property magnitude The numeric value of the angular crackle.
 * @property expression The unit expression of the angular crackle, always [RadianPerSecondFifth].
 */
class Crackle internal constructor(
    magnitude: BigDecimal,
    expression: RadianPerSecondFifth
) : Measure<RadianPerSecondFifth, Crackle>(magnitude, expression, ::Crackle) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Radian(prefix), SecondQuintic()))
}
