package org.kisu.units.base

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.Scalar
import java.math.BigDecimal

/**
 * Represents the physical quantity of **electric current**, measured in amperes (A).
 *
 * Electric current quantifies the flow of electric charge over time.
 * One ampere corresponds to one coulomb of charge passing through a point in a circuit per second.
 *
 * This quantity is one of the seven SI base units and is typically used to describe the intensity of electrical flow
 * in conductors, circuits, and electromagnetic systems.
 *
 * This class expresses current as a combination of a [magnitude] and a [prefix], supporting values such as
 * milliamperes (mA), microamperes (µA), or kiloamperes (kA).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Current internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, Current>(magnitude, expression, ::Current) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, SYMBOL))

    companion object {
        /** The SI symbol for electric current: "A" (ampere). */
        internal const val SYMBOL = "A"
    }
}
