package org.kisu.units.base

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

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
 * All values are stored with high precision using [BigDecimal], and instances are immutable.
 */
class LuminousIntensity internal constructor(magnitude: BigDecimal, expression: Candela) :
    Measure<Candela, LuminousIntensity>(magnitude, expression, ::LuminousIntensity) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Candela(prefix))
}

/**
 * Represents the SI base unit of **luminous intensity**.
 *
 * The candela (cd) is the standard unit for measuring luminous intensity.
 */
class Candela private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Candela>(prefix, overflow, unit, ::Candela) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical SI symbol for luminous intensity: "cd". */
        internal val UNIT = Unit("cd", 1)
    }
}
