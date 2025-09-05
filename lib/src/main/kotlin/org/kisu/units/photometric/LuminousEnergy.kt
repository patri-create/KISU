package org.kisu.units.photometric

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Product
import org.kisu.units.special.Lumen
import java.math.BigDecimal

typealias LumenSecond = Product<Lumen, Second>

/**
 * Creates a measure of **lumen-seconds** (lm·s).
 *
 * This derived unit expresses luminous energy — the total
 * luminous flux emitted over a period of time.
 *
 * Internally this returns a [Product] of:
 *  - a [Lumen] (luminous flux) with the specified [prefix]
 *  - multiplied by a [Second] (time)
 *
 * @param prefix Metric prefix to apply to the lumen unit.
 * Defaults to [Metric.BASE] (no prefix).
 *
 * @return A [Product] representing lm·s.
 */
@Suppress("FunctionNaming")
internal fun LumenSecond(prefix: Metric = Metric.BASE): Product<Lumen, Second> =
    Product(Lumen(prefix), Second())

/**
 * Represents **luminous energy**.
 *
 * Luminous energy (also known as *quantity of light*) is the measure of the
 * perceived amount of light emitted over a period of time.
 *
 * It is defined as the time integral of luminous flux and is expressed in
 * **lumen-seconds (lm·s)**.
 *
 * This quantity is important in photometry when evaluating the total amount
 * of light delivered by a source during a given interval.
 *
 * @constructor Creates a luminous energy measure with the given [magnitude]
 * and [expression].
 * @param magnitude The numeric value of the measure.
 * @param expression The unit expression in lumen-seconds.
 */
class LuminousEnergy(
    magnitude: BigDecimal,
    expression: LumenSecond
) : Measure<LumenSecond, LuminousEnergy>(magnitude, expression, ::LuminousEnergy) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, LumenSecond(prefix))
}
