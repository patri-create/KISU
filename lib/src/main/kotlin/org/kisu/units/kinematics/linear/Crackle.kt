package org.kisu.units.kinematics.linear

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.SecondQuintic
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

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
) : Measure<Crackle.MetrePerSecondFifth, Crackle>(magnitude, expression, ::Crackle) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Metre(prefix), SecondQuintic()))

    /**
     * Represents the SI unit **metre per second to the fifth power (m/s⁵)**.
     *
     * This unit is used to measure the **fifth time derivative of position**,
     * sometimes referred to as **fifth-order linear rate change**,
     * i.e., how rapidly the fourth derivative of displacement changes over time.
     * It is defined as the [Quotient] of [Metre] (length) divided by [SecondQuintic] (time⁵).
     *
     * Example usages include:
     * - Advanced dynamics simulations in physics or engineering
     * - Modelling high-order motion derivatives in vibration analysis
     * - Control systems requiring extremely precise motion prediction
     *
     * @see Crackle
     */
    typealias MetrePerSecondFifth = Quotient<Metre, SecondQuintic>
}
