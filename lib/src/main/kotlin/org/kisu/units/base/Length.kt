package org.kisu.units.base

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents the physical quantity of **length**, measured in metres (m).
 *
 * Length describes the extent of objects or the distance between points in space. This class models length
 * as defined by the SI system, using the metre as the base unit and supporting metric prefixes such as millimetre (mm),
 * centimetre (cm), and kilometre (km).
 *
 * The quantity is expressed with a [magnitude] and an [expression], enabling precise representation of both small- and
 * large-scale measurements using [Magnitude] for accuracy.
 */
class Length internal constructor(magnitude: Magnitude, expression: Metre) :
    Measure<Metre, Length>(magnitude, expression, ::Length) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Metre(prefix))
}

/**
 * Represents the SI base unit of **length**.
 *
 * The metre (m) is the standard unit for measuring distance.
 */
class Metre private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Metre>(algebra, prefix, unit, ::Metre) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical SI symbol for length: "m". */
        internal val UNIT = Unit("m", 1)
    }
}
