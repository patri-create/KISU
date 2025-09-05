package org.kisu.units.photometric

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Product
import org.kisu.units.special.Lux
import java.math.BigDecimal

typealias LuxSecond = Product<Lux, Second>

/**
 * Creates a measure of **lux-seconds** (lx·s).
 *
 * This derived unit expresses luminous exposure — the total
 * illumination received over a period of time.
 *
 * Internally this returns a [Product] of:
 *  - a [Lux] (illuminance) with the specified [prefix]
 *  - multiplied by a [Second] (time)
 *
 * @param prefix Metric prefix to apply to the lux unit.
 * Defaults to [Metric.BASE] (no prefix).
 *
 * @return A [Product] representing lx·s.
 */
@Suppress("FunctionNaming")
internal fun LuxSecond(prefix: Metric = Metric.BASE): Product<Lux, Second> =
    Product(Lux(prefix), Second())

/**
 * Represents **luminous exposure**.
 *
 * Luminous exposure is the measure of the total luminous energy
 * received per unit area, calculated as the product of illuminance
 * (in lux) and time (in seconds).
 *
 * Expressed in **lux-seconds (lx·s)**.
 *
 * Commonly used in **photography** and **radiometry** to quantify the
 * total light incident on a surface during an exposure.
 *
 * @constructor Creates a luminous exposure measure with the given [magnitude]
 * and [expression].
 * @param magnitude The numeric value of the measure.
 * @param expression The unit expression in lux-seconds.
 */
class Exposure(
    magnitude: BigDecimal,
    expression: LuxSecond
) : Measure<LuxSecond, Exposure>(magnitude, expression, ::Exposure) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, LuxSecond(prefix))
}
