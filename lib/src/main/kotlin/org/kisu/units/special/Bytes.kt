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
 * This class expresses information as a combination of a [magnitude] and an [expression], supporting values such as
 * kilobytes (kB), megabytes (MB), or gigabytes (GB).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Bytes private constructor(magnitude: BigDecimal, expression: Byte) :
    Measure<Byte, Bytes>(magnitude, expression, ::Bytes) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Byte(prefix))

    companion object {
        /** The symbol for byte: "B". */
        internal val UNIT = Unit("B", 1)

        operator fun invoke(magnitude: BigDecimal, prefix: Metric = Metric.BASE): Bytes =
            Bytes(magnitude, prefix)
    }
}

/**
 * Represents the unit of information: **byte** (B).
 *
 * One byte is equal to 8 bits.
 */
class Byte private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Byte>(prefix, overflow, unit, ::Byte) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for byte: "B". */
        internal val UNIT = Unit("B", 1)
    }
}
