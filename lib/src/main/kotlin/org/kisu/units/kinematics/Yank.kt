package org.kisu.units.kinematics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.base.Metre
import org.kisu.units.base.SecondCubed
import org.kisu.units.kinematics.Yank.Companion.KilogramMetrePerSecondCubed
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient

/**
 * Represents the physical quantity of **yank**, measured in
 * [KilogramMetrePerSecondCubed].
 *
 * Yank quantifies how rapidly force changes over time. It is the time derivative of
 * force and appears in high-order dynamics where abrupt force variation matters.
 *
 * Typical examples include shock loading, vibration control, impact modeling, and
 * precision motion systems.
 *
 * The associated unit representation is [KilogramMetrePerSecondCubed] (`kg·m/s³`).
 */
class Yank internal constructor(
    magnitude: Magnitude,
    expression: KilogramMetrePerSecondCubed
) : Measure<Yank.KilogramMetrePerSecondCubed, Yank>(magnitude, expression, ::Yank) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(
            magnitude,
            KilogramMetrePerSecondCubed(prefix)
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
     * @see KilogramMetrePerSecondCubed
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
    typealias KilogramMetrePerSecondCubed = Quotient<KilogramMetre, SecondCubed>

    companion object {
        /**
         * Creates a [KilogramMetrePerSecondCubed] expression for **kilogram metre per second cubed** (`kg·m/s³`).
         *
         * @param prefix Metric prefix applied to the kilogram unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [KilogramMetrePerSecondCubed] expression for `kg·m/s³`.
         */
        @Suppress("FunctionNaming")
        internal fun KilogramMetrePerSecondCubed(prefix: Metric = Metric.BASE): KilogramMetrePerSecondCubed =
            Quotient(
                Product(Kilogram(prefix), Metre()),
                SecondCubed()
            )
    }
}
