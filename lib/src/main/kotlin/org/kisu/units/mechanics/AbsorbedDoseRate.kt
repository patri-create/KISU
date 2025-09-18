package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Gray
import java.math.BigDecimal

/**
 * Measure of absorbed dose rate expressed in [GrayPerSecond].
 *
 * Absorbed dose rate quantifies the **intensity of ionizing radiation** in terms of
 * the rate at which energy is deposited in matter per unit of mass.
 *
 * It is expressed in **gray per second (Gy/s)**, linking the concept of *absorbed dose* (energy
 * imparted by radiation per unit mass, in grays) with the dimension of *time*.
 *
 * This measure is critical in fields such as:
 * - **Radiation protection**, to establish exposure limits for workers and the public.
 * - **Medical physics**, particularly in radiotherapy, where precise control of dose rate is
 *   essential to optimize treatment while minimizing harm.
 * - **Nuclear engineering**, in monitoring radiation levels and ensuring safety of materials
 *   and environments.
 *
 * Properties:
 * @property numerical value of the absorbed dose rate.
 * @property the unit system representation, here [GrayPerSecond].
 *
 * @see GrayPerSecond
 */
class AbsorbedDoseRate(
    magnitude: BigDecimal,
    expression: GrayPerSecond
) : Measure<AbsorbedDoseRate.GrayPerSecond, AbsorbedDoseRate>(magnitude, expression, ::AbsorbedDoseRate) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
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
         * Creates a measure of **grays per second** (Gy/s).
         *
         * This derived unit expresses an **absorbed dose rate** —
         * how much ionizing radiation energy (in grays) is absorbed per unit time.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Gray] (absorbed dose) with the specified [prefix]
         *  - divided by a [Second] (time)
         *
         * @param prefix Metric prefix to apply to the gray unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [GrayPerSecond] representing Gy/s.
         */
        @Suppress("FunctionNaming")
        internal fun GrayPerSecond(prefix: Metric = Metric.BASE): GrayPerSecond =
            Quotient(Gray(prefix), Second())
    }
}
