package org.kisu.units.derived

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import java.math.BigDecimal

/**
 * Represents the digital information unit **byte (B)**.
 *
 * One byte consists of 8 bits. This class helps express storage or transmission size
 * in units such as kilobytes (kB), megabytes (MB), etc., using metric prefixes.
 *
 * This class expresses information as a combination of a [magnitude] and a [prefix], supporting values such as
 * kilobytes (kB), megabytes (MB), or gigabytes (GB).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Byte private constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
    Measure<Metric, Byte>(magnitude, prefix, SYMBOL, ::Byte) {

    companion object {
        /** The symbol for byte: "B". */
        private const val SYMBOL = "B"

        operator fun invoke(magnitude: BigDecimal, prefix: Metric = Metric.BASE): Byte {
            return Byte(magnitude, prefix)
        }
    }
}
