package org.kisu.units.kinematics.angular

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.SecondQuintic
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian
import java.math.BigDecimal

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
) : Measure<Crackle.RadianPerSecondFifth, Crackle>(magnitude, expression, ::Crackle) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Radian(prefix), SecondQuintic()))

    /**
     * Represents the SI unit **radian per second to the fifth power (rad/s⁵)**.
     *
     * This unit is used to measure the **fifth time derivative of angular position**,
     * sometimes called **angular crackle** or higher-order angular rate change,
     * i.e., how rapidly angular jerk or snap itself changes over time.
     * It is defined as the [Quotient] of [Radian] (angle) divided by [SecondQuintic] (time⁵).
     *
     * Example usages include:
     * - High-order rotational motion analysis in robotics or aerospace
     * - Modelling vibration or oscillatory systems with complex dynamics
     * - Advanced control systems requiring precise higher-order derivatives
     *
     * @see Crackle
     */
    typealias RadianPerSecondFifth = Quotient<Radian, SecondQuintic>
}
