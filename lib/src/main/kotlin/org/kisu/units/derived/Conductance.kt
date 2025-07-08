package org.kisu.units.derived

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import java.math.BigDecimal

/**
 * Represents the physical quantity of **electrical conductance**, measured in siemens (S).
 *
 * One siemens is the reciprocal of one ohm. It measures how easily electricity flows through a conductor.
 * In base SI units, it is kg⁻¹·m⁻²·s³·A².
 *
 * Siemens are used to describe the conductance of materials and components in electrical systems.
 *
 * This class expresses conductance as a combination of a [magnitude] and a [prefix], supporting values such as
 * millisiemens (mS), microsiemens (µS), or kilosiemens (kS).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Conductance internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
    Measure<Metric, Conductance>(magnitude, prefix, SYMBOL, ::Conductance) {

    companion object {
        /** The SI symbol for electrical conductance: "S" (siemens). */
        internal const val SYMBOL = "S"
    }
}
