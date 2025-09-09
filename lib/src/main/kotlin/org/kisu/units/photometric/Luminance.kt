package org.kisu.units.photometric

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Candela
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

/**
 * Represents **luminance**.
 *
 * Luminance is the measure of the luminous intensity emitted,
 * transmitted, or reflected from a surface in a given direction
 * per unit area.
 *
 * Expressed in **candelas per square metre (cd/m²)**, also known as
 * **nits** in display technology.
 *
 * It quantifies how bright a surface will appear to the human eye,
 * making it a central concept in **optics, lighting engineering, and
 * display calibration**.
 *
 * @constructor Creates a luminance measure with the given [magnitude]
 * and [expression].
 * @param magnitude The numeric value of the measure.
 * @param expression The unit expression in candelas per square metre.
 */
class Luminance(
    magnitude: BigDecimal,
    expression: CandelaPerSquareMetre
) : Measure<Luminance.CandelaPerSquareMetre, Luminance>(magnitude, expression, ::Luminance) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, CandelaPerSquareMetre(prefix))

    /**
     * Represents the SI unit **candela per square metre (cd/m²)**.
     *
     * This unit is used to measure **luminance**,
     * i.e., the luminous intensity emitted per unit area in a given direction.
     * It is defined as the [Quotient] of [Candela] (luminous intensity) divided by [SquareMetre] (area).
     *
     * Example usages include:
     * - Specifying the brightness of screens, displays, or monitors
     * - Measuring surface luminance in lighting design or photometry
     * - Analysing visual comfort and visibility in architectural lighting
     *
     * @see Luminance
     */
    typealias CandelaPerSquareMetre = Quotient<Candela, SquareMetre>

    companion object {
        /**
         * Creates a measure of **candelas per square metre** (cd/m²).
         *
         * This derived unit expresses luminance — the amount of luminous
         * intensity emitted or reflected per unit area.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Candela] (luminous intensity) with the specified [prefix]
         *  - divided by a [SquareMetre] (area)
         *
         * @param prefix Metric prefix to apply to the candela unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [Quotient] representing cd/m².
         */
        @Suppress("FunctionNaming")
        internal fun CandelaPerSquareMetre(prefix: Metric = Metric.BASE): Quotient<Candela, SquareMetre> =
            Quotient(Candela(prefix), SquareMetre())
    }
}
