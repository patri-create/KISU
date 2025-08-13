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
class LuminousIntensity internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, LuminousIntensity>(magnitude, expression, ::LuminousIntensity) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, unit = UNIT))

    companion object {
        /** The SI symbol for luminous intensity: "cd" (candela). */
        internal val UNIT = Unit("cd", 1)
    }
}
