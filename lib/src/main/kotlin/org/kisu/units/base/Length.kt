package org.kisu.units.base

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import java.math.BigDecimal

/**
 * Represents the physical quantity of **length**, measured in metres (m).
 *
 * Length describes the extent of objects or the distance between points in space. This class models length
 * as defined by the SI system, using the metre as the base unit and supporting metric prefixes such as millimetre (mm),
 * centimetre (cm), and kilometre (km).
 *
 * The quantity is expressed with a [magnitude] and a [prefix], enabling precise representation of both small- and
 * large-scale measurements using [BigDecimal] for accuracy.
 */
class Length internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
    Measure<Metric, Length>(magnitude, prefix, SYMBOL, ::Length) {

    companion object {
        /** The SI symbol for length: "m" (metre). */
        private const val SYMBOL = "m"
    }
}
