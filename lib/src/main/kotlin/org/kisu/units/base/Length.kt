package org.kisu.units.base

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **length**, measured in metres (m).
 *
 * Length describes the extent of objects or the distance between points in space. This class models length
 * as defined by the SI system, using the metre as the base unit and supporting metric prefixes such as millimetre (mm),
 * centimetre (cm), and kilometre (km).
 *
 * The quantity is expressed with a [magnitude] and an [expression], enabling precise representation of both small- and
 * large-scale measurements using [BigDecimal] for accuracy.
 */
class Length internal constructor(magnitude: BigDecimal, expression: Metre) :
    Measure<Metre, Length>(magnitude, expression, ::Length) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Metre(prefix))
}

/**
 * Represents the SI base unit of **length**.
 *
 * The metre (m) is the standard unit for measuring distance.
 */
class Metre private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Metre>(prefix, overflow, unit, ::Metre) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical SI symbol for length: "m". */
        internal val UNIT = Unit("m", 1)
    }
}