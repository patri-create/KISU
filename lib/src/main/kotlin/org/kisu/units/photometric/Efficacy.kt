package org.kisu.units.photometric

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Lumen
import org.kisu.units.special.Watt
import java.math.BigDecimal

typealias LumenPerWatt = Quotient<Lumen, Watt>

/**
 * Creates a measure of **lumens per watt** (lm/W).
 *
 * This derived unit expresses luminous efficacy — how much visible
 * light (luminous flux) is produced per unit of power.
 *
 * Internally this returns a [Quotient] of:
 *  - a [Lumen] (luminous flux) with the specified [prefix]
 *  - divided by a [Watt] (power)
 *
 * @param prefix Metric prefix to apply to the lumen unit.
 * Defaults to [Metric.BASE] (no prefix).
 *
 * @return A [Quotient] representing lm/W.
 */
@Suppress("FunctionNaming")
internal fun LumenPerWatt(prefix: Metric = Metric.BASE): Quotient<Lumen, Watt> =
    Quotient(Lumen(prefix), Watt())

/**
 * Represents the **luminous efficacy** of a light source.
 *
 * Luminous efficacy is the ratio between the luminous flux (in lumens)
 * and the power consumed (in watts). It indicates how efficiently a
 * light source produces visible light.
 *
 * Expressed in **lumens per watt (lm/W)**.
 *
 * @constructor Creates a luminous efficacy measure with the given [magnitude]
 * and [expression].
 * @param magnitude The numeric value of the measure.
 * @param expression The unit expression in lumens per watt.
 */
class Efficacy(
    magnitude: BigDecimal,
    expression: LumenPerWatt
) : Measure<LumenPerWatt, Efficacy>(magnitude, expression, ::Efficacy) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, LumenPerWatt(prefix))
}
