package org.kisu.units.kinematics.angular

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.SecondCubed
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Radian
import java.math.BigDecimal

/**
 * Represents the physical quantity of **angular jerk**, the third derivative of angular position with respect to time.
 *
 * Angular jerk quantifies how **angular acceleration** changes over time.
 * Its SI unit is the **radian per second cubed (rad/s³)**, represented here by [RadianPerSecondCubed].
 *
 * Typical applications include:
 * - Rotational motion control in robotics and machinery
 * - Vehicle or spacecraft rotational dynamics
 * - Physics simulations requiring higher-order angular derivatives
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [AngularJerk] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([RadianPerSecondCubed]).
 *
 * @property magnitude The numeric value of the angular jerk.
 * @property expression The unit expression of the angular jerk, always [RadianPerSecondCubed].
 */
class Jerk(
    magnitude: BigDecimal,
    expression: RadianPerSecondCubed
) : Measure<Jerk.RadianPerSecondCubed, Jerk>(magnitude, expression, ::Jerk) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Radian(prefix), SecondCubed()))

    /**
     * Represents the SI unit **radian per second cubed (rad/s³)**.
     *
     * This unit is used to measure the **third time derivative of angular position**,
     * commonly called **angular jerk** or **angular jolt**,
     * i.e., the rate of change of angular acceleration with respect to time.
     * It is defined as the [Quotient] of [Radian] (angle) divided by [SecondCubed] (time³).
     *
     * Example usages include:
     * - Analysing rapid changes in angular acceleration in robotics or aerospace
     * - Modelling rotational dynamics of precision mechanisms
     * - Designing control systems where higher-order derivatives of motion are relevant
     *
     * @see Jerk
     */
    typealias RadianPerSecondCubed = Quotient<Radian, SecondCubed>
}
