@file:Suppress("TooManyFunctions")

package org.kisu.units.base

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents the physical quantity of **luminous intensity**, measured in candelas (cd).
 *
 * Luminous intensity quantifies the perceived brightness emitted by a light source in a specific direction.
 * It is one of the seven SI base quantities and is measured in **candelas (cd)**.
 *
 * The [magnitude] must not be negative. Negative luminous intensity is physically meaningless because intensity
 * describes an emission — light cannot be “less than none.” A value of zero represents no light output, and
 * any non-zero value indicates the intensity of light emitted.
 *
 * This class models the quantity as a combination of a [magnitude] and an [expression], enabling precise values
 * such as milllicandelas (mcd) or kilocandelas (kcd).
 *
 * All values are stored with high precision using [Magnitude], and instances are immutable.
 */
class LuminousIntensity internal constructor(magnitude: Magnitude, expression: Candela) :
    Measure<Candela, LuminousIntensity>(magnitude, expression, ::LuminousIntensity) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Candela(prefix))

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.photometric.Luminance
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Area
    ): org.kisu.units.photometric.Luminance =
        org.kisu.units.photometric.Luminance(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.SolidAngle
    ): org.kisu.units.special.LuminousFlux =
        org.kisu.units.special.LuminousFlux(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the SI base unit of **luminous intensity**.
 *
 * The candela (cd) is the standard unit for measuring luminous intensity.
 */
class Candela private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Candela>(algebra, prefix, unit, ::Candela) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical SI symbol for luminous intensity: "cd". */
        internal val UNIT = Unit("cd", 1)
    }
}
