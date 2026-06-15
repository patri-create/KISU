package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents a quantity of digital information measured in [Byte].
 *
 * A byte-based measure quantifies stored or transmitted information in groups of eight
 * bits. This is the scale commonly used in everyday computing when discussing files,
 * memory, bandwidth, and device capacity.
 *
 * Typical examples include the size of a document, the memory occupied by an image,
 * or the amount of data moved across a network.
 *
 * The canonical unit is [Byte] (`B`), usually combined with metric prefixes such as
 * `kB`, `MB`, or `GB`.
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
 * Represents the unit **byte** (`B`), used to express [Bytes].
 *
 * A byte quantifies digital information in groups of eight bits. It is the unit most
 * commonly used when discussing file sizes, memory capacity, and data transfers in
 * everyday computing.
 *
 * Typical examples include the size of a text file, the memory occupied by an image,
 * or the amount of data sent across a network packet payload.
 *
 * In this library, [Byte] is the scalar unit representation behind [Bytes], and it
 * can be combined with metric prefixes such as kB, MB, or GB.
 *
 * @see Bytes
 */
class Byte private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Byte>(algebra, prefix, unit, ::Byte) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for byte: "B". */
        internal val UNIT = Unit("B", 1)
    }
}
