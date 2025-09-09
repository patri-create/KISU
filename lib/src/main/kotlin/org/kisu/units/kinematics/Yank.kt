package org.kisu.units.kinematics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.base.Metre
import org.kisu.units.base.SecondCubed
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

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
) : Measure<Yank.KilogramMetreSecondThird, Yank>(magnitude, expression, ::Yank) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(
            magnitude,
            Quotient(
                Product(Kilogram(prefix to BigDecimal.ONE), Metre()),
                SecondCubed()
            )
        )

    /**
     * Represents the SI unit **kilogram metre (kg·m)**.
     *
     * This composite unit combines **mass and distance**.
     * It is defined as the [Product] of [Kilogram] (mass) and [Metre] (length).
     *
     * Although not an SI derived unit with a specific name, it appears in
     * intermediate steps of physics and engineering calculations.
     *
     * Example usages include:
     * - Expressing moments or torque before dividing by time or angle
     * - Serving as an intermediate quantity in mechanics or material science
     *
     * @see KilogramMetreSecondThird
     */
    typealias KilogramMetre = Product<Kilogram, Metre>

    /**
     * Represents the SI unit **kilogram metre per second cubed (kg·m/s³)**.
     *
     * This unit is used to measure **mass flow rate of acceleration or power per velocity**
     * (an uncommon but valid derived unit in mechanics and engineering).
     * It is defined as the [Quotient] of [KilogramMetre] (mass × length) divided by [SecondCubed] (time³).
     *
     * Example usages include:
     * - Appearing in intermediate formulas for dynamic systems
     * - Describing certain transport phenomena or rate-of-change quantities in physics
     *
     * @see Yank
     */
    typealias KilogramMetreSecondThird = Quotient<KilogramMetre, SecondCubed>
}
