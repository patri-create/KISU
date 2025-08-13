package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **force**, measured in newtons (N).
 *
 * One newton is the force required to accelerate a mass of one kilogram at a rate of one meter per second squared.
 * It is a derived SI unit defined as kg·m/s².
 *
 * Newtons are commonly used in mechanics, physics, and engineering to describe force interactions.
 *
 * This class expresses force as a combination of a [magnitude] and an [expression], supporting values such as
 * kilonewtons (kN), millinewtons (mN), or micronewtons (µN).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Force internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, Force>(magnitude, expression, ::Force) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, unit = UNIT))

    companion object {
        /** The SI symbol for force: "N" (newton). */
        internal val UNIT = Unit("N", 1)
    }
}
