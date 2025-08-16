package org.kisu.units.base

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
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
 * This class expresses current as a combination of a [magnitude] and an [expression], supporting values such as
 * milliamperes (mA), microamperes (µA), or kiloamperes (kA).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Current internal constructor(magnitude: BigDecimal, expression: Ampere) :
    Measure<Ampere, Current>(magnitude, expression, ::Current) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude,Ampere(prefix))
}

/**
 * Represents the SI base unit of **electric current**.
 *
 * The ampere (A) is the standard unit for measuring electric current.
 */
class Ampere private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Ampere>(prefix, overflow, unit, ::Ampere) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical SI symbol for electric current: "A". */
        internal val UNIT = Unit("A", 1)
    }
}