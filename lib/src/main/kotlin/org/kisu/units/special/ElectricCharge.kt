package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **electric charge**, measured in coulombs (C).
 *
 * One coulomb corresponds to the amount of electric charge transferred by a current of one ampere in one second.
 * It is defined as s·A in SI base units.
 *
 * Coulombs are used in physics and electrical engineering to quantify electric charge and electrochemical processes.
 *
 * This class expresses charge as a combination of a [magnitude] and a [prefix], supporting values such as
 * millicoulombs (mC), microcoulombs (µC), or kilocoulombs (kC).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class ElectricCharge internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, ElectricCharge>(magnitude, expression, ::ElectricCharge) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, unit = UNIT))

    companion object {
        /** The SI symbol for electric charge: "C" (coulomb). */
        internal val UNIT = Unit("C", 1)
    }
}
