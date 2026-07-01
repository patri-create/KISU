@file:Suppress("TooManyFunctions")

package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.mechanics.AbsorbedDoseRate.Companion.GrayPerSecond
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Gray

/**
 * Represents the physical quantity of **absorbed dose rate**, measured in
 * [GrayPerSecond].
 *
 * Absorbed dose rate quantifies how quickly radiation energy is deposited in matter per
 * unit mass. It is the time rate of change of absorbed dose and is central whenever not
 * only total dose but delivery speed matters.
 *
 * Typical examples include radiotherapy beam characterization, radiation safety
 * monitoring, and irradiation process control.
 *
 * The associated unit representation is [GrayPerSecond] (`Gy/s`).
 */
class AbsorbedDoseRate(
    magnitude: Magnitude,
    expression: GrayPerSecond
) : Measure<AbsorbedDoseRate.GrayPerSecond, AbsorbedDoseRate>(magnitude, expression, ::AbsorbedDoseRate) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, GrayPerSecond(prefix))

    /**
     * Unit of [AbsorbedDoseRate].
     *
     * Represents the unit of **absorbed dose rate**, i.e. the rate at which
     * ionizing radiation energy is absorbed per unit of mass over time.
     *
     * Measured in **gray per second (Gy/s)**, which is defined as:
     * ```
     * 1 Gy/s = 1 J·kg⁻¹·s⁻¹ = 1 m²·s⁻³
     * ```
     * where 1 gray (Gy) = 1 joule per kilogram.
     *
     * @see AbsorbedDoseRate
     */
    typealias GrayPerSecond = Quotient<Gray, Second>

    companion object {
        /**
         * Creates a [GrayPerSecond] expression for **gray per second** (`Gy/s`).
         *
         * @param prefix Metric prefix applied to the gray unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [GrayPerSecond] expression for `Gy/s`.
         */
        @Suppress("FunctionNaming")
        internal fun GrayPerSecond(prefix: Metric = Metric.BASE): GrayPerSecond =
            Quotient(Gray(prefix), Second())
    }

    // Dimension-aware arithmetic
    /**
     * Multiplies this [AbsorbedDoseRate] by [Time][org.kisu.units.base.Time],
     * yielding [AbsorbedDose][org.kisu.units.special.AbsorbedDose].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Time
    ): org.kisu.units.special.AbsorbedDose =
        org.kisu.units.special.AbsorbedDose(canonical.component1() * other.canonical.component1())
}
