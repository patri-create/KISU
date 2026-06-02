package org.kisu.units.photometric

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Product
import org.kisu.units.special.Lux
import java.math.BigDecimal

/**
 * Represents the physical quantity of **luminous exposure**, measured in [LuxSecond].
 *
 * Luminous exposure quantifies the total visible light received by a surface over a
 * period of time. It combines illuminance with duration, making it a cumulative measure
 * rather than an instantaneous one.
 *
 * This quantity is used in photography, lighting analysis, and exposure studies where
 * total incident light matters.
 *
 * The associated unit representation is [LuxSecond] (`lx·s`).
 */
class Exposure(
    magnitude: BigDecimal,
    expression: LuxSecond
) : Measure<Exposure.LuxSecond, Exposure>(magnitude, expression, ::Exposure) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, LuxSecond(prefix))

    /**
     * Represents the SI unit **lux second (lx·s)**.
     *
     * This unit is used to measure **illuminance exposure**,
     * i.e., the total light received over a period of time.
     * It is defined as the [Product] of [Lux] (illuminance) and [Second] (time).
     *
     * Example usages include:
     * - Quantifying light exposure for photographic or cinematographic purposes
     * - Measuring accumulated illumination in plant growth studies
     * - Assessing dosages in light therapy or photobiology
     *
     * @see Exposure
     */
    typealias LuxSecond = Product<Lux, Second>

    companion object {
        /**
         * Creates a [LuxSecond] expression for **lux second** (`lx·s`).
         *
         * @param prefix Metric prefix applied to the lux unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [LuxSecond] expression for `lx·s`.
         */
        @Suppress("FunctionNaming")
        internal fun LuxSecond(prefix: Metric = Metric.BASE): LuxSecond =
            Product(Lux(prefix), Second())
    }
}
