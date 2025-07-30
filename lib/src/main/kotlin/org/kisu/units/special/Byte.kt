package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
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
class Byte private constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, Byte>(magnitude, expression, ::Byte) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, unit = UNIT))

    companion object {
        /** The symbol for byte: "B". */
        internal val UNIT = Unit("B", 1)

        operator fun invoke(magnitude: BigDecimal, prefix: Metric = Metric.BASE): Byte =
            Byte(magnitude, prefix)
    }
}
