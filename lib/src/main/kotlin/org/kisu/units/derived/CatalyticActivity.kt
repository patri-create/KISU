package org.kisu.units.derived

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import java.math.BigDecimal

/**
 * Represents the physical quantity of **catalytic activity**, measured in katals (kat).
 *
 * One katal corresponds to the catalytic activity that converts one mole of substrate per second.
 * In SI base units, it is mol·s⁻¹.
 *
 * Katals are used in biochemistry and enzymology to quantify the rate of enzymatic reactions.
 *
 * This class expresses catalytic activity as a combination of a [magnitude] and a [prefix], supporting values such as
 * millikatals (mkat), microkatals (µkat), or kilokatals (kkat).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class CatalyticActivity internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
    Measure<Metric, CatalyticActivity>(magnitude, prefix, SYMBOL, ::CatalyticActivity) {

    companion object {
        /** The SI symbol for catalytic activity: "kat" (katal). */
        internal const val SYMBOL = "kat"
    }
}
