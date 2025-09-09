package org.kisu.units.kinematics.angular

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.SecondQuartic
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian
import java.math.BigDecimal

/**
 * Represents the physical quantity of **angular snap**, the fourth derivative of angular position with respect to time.
 *
 * Angular snap quantifies how the **angular jerk** (third derivative of angular position) changes over time.
 * Its SI unit is the **radian per second to the fourth (rad/s⁴)**, represented here by [RadianPerSecondFourth].
 *
 * Typical applications include:
 * - Robotics and rotational motion control for smooth higher-order trajectories
 * - Physics simulations requiring precise modeling of angular derivatives beyond acceleration
 * - Systems where control of the change of angular jerk is necessary for stability or comfort
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [AngularSnap] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([RadianPerSecondFourth]).
 *
 * @property magnitude The numeric value of the angular snap.
 * @property expression The unit expression of the angular snap, always [RadianPerSecondFourth].
 */
class Snap(
    magnitude: BigDecimal,
    expression: RadianPerSecondFourth
) : Measure<Snap.RadianPerSecondFourth, Snap>(magnitude, expression, ::Snap) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Radian(prefix), SecondQuartic()))

    /**
     * Represents the SI unit **radian per second to the fourth power (rad/s⁴)**.
     *
     * This unit is used to measure the **fourth time derivative of angular position**,
     * sometimes called **angular snap**,
     * i.e., the rate of change of angular jerk with respect to time.
     * It is defined as the [Quotient] of [Radian] (angle) divided by [SecondQuartic] (time⁴).
     *
     * Example usages include:
     * - Analysing high-order rotational motion in robotics or aerospace
     * - Modelling dynamic systems where angular acceleration changes rapidly
     * - Designing precision control systems requiring higher-order derivatives
     *
     * @see Snap
     */
    typealias RadianPerSecondFourth = Quotient<Radian, SecondQuartic>
}
