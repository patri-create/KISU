package org.kisu.units.base

import org.kisu.negative
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.exceptions.NegativeLuminousIntensity
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
 * This class models the quantity as a combination of a [magnitude] and a [prefix], enabling precise values
 * such as milllicandelas (mcd) or kilocandelas (kcd).
 *
 * All values are stored with high precision using [BigDecimal], and instances are immutable.
 */
class LuminousIntensity private constructor(magnitude: BigDecimal, prefix: Metric) :
    Measure<Metric, LuminousIntensity>(magnitude, prefix, SYMBOL, ::invoke) {

    companion object {
        /** The SI symbol for luminous intensity: "cd" (candela). */
        private const val SYMBOL = "cd"

        /**
         * Creates a new [LuminousIntensity] quantity with the given [magnitude] and [prefix].
         *
         * The magnitude must be zero or positive. Negative luminous intensity is not permitted, as it would imply
         * a negative emission of light, which is not physically possible.
         *
         * @param magnitude The numeric value of the luminous intensity.
         * @param prefix The metric prefix to apply (e.g., m, k).
         * @return A validated [LuminousIntensity] instance.
         * @throws NegativeLuminousIntensity if the magnitude is less than zero.
         */
        operator fun invoke(
            magnitude: BigDecimal,
            prefix: Metric,
        ): LuminousIntensity {
            if (magnitude.negative) {
                throw NegativeLuminousIntensity(magnitude, prefix, SYMBOL)
            }
            return LuminousIntensity(magnitude, prefix)
        }
    }
}
