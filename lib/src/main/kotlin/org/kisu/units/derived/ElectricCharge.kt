package org.kisu.units.derived

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
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
class ElectricCharge internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
    Measure<Metric, ElectricCharge>(magnitude, prefix, SYMBOL, ::ElectricCharge) {

    companion object {
        /** The SI symbol for electric charge: "C" (coulomb). */
        internal const val SYMBOL = "C"
    }
}
