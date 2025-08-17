package org.kisu.units.kinematics

import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.base.Metre
import org.kisu.units.base.SecondCubed
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

typealias KilogramMetre = Product<Kilogram, Metre>
typealias KilogramMetreSecondThird = Quotient<KilogramMetre, SecondCubed>

/**
 * Represents the physical quantity of **yank**, the rate of change of force over time.
 *
 * Yank quantifies how quickly a force changes, i.e., the derivative of force with respect to time.
 * Its SI unit is **kilogram metre per second cubed (kg·m/s³)**, represented here by [KilogramMetreSecondThird].
 *
 * Typical applications include:
 * - Analysis of rapidly changing forces in mechanical systems
 * - Vibration and shock studies in engineering
 * - Dynamics simulations requiring higher-order force derivatives
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [Yank] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation
 * ([KilogramMetreSecondThird]).
 *
 * @property magnitude The numeric value of the yank.
 * @property expression The unit expression of the yank, always [KilogramMetreSecondThird].
 */
class Yank internal constructor(
    magnitude: BigDecimal,
    expression: KilogramMetreSecondThird
) : Measure<KilogramMetreSecondThird, Yank>(magnitude, expression, ::Yank)
