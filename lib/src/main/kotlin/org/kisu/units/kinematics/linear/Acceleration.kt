package org.kisu.units.kinematics.linear

import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.SecondSquared
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

typealias MetrePerSecondSquared = Quotient<Metre, SecondSquared>

/**
 * Represents the physical quantity of **acceleration**.
 *
 * Acceleration quantifies the **rate of change of velocity** over time.
 * Its SI unit is the **metre per second squared (m/s²)**, represented here by [MetrePerSecondSquared].
 *
 * Example usages include:
 * - Free-fall acceleration due to gravity (~9.81 m/s²)
 * - Vehicle acceleration
 * - Any system where velocity changes over time
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [Acceleration] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([MetrePerSecondSquared]).
 *
 * @property magnitude The numeric value of the acceleration.
 * @property expression The unit expression of the acceleration, always [MetrePerSecondSquared].
 */
class Acceleration(
    magnitude: BigDecimal,
    expression: MetrePerSecondSquared
) : Measure<MetrePerSecondSquared, Acceleration>(magnitude, expression, ::Acceleration)
