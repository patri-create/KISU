package org.kisu.units.photometric

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Product
import org.kisu.units.special.Lux
import java.math.BigDecimal

typealias LuxSecond = Product<Lux, Second>

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
        this(magnitude, Product(Lux(prefix), Second()))
}
