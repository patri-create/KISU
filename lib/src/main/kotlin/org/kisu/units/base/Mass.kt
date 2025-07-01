package org.kisu.units.base

import org.kisu.negative
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.exceptions.NegativeMass
import java.math.BigDecimal

/**
 * Represents the physical quantity of **mass**, measured in grams (g).
 *
 * Mass quantifies the amount of matter contained in a physical object. It is one of the most fundamental physical
 * properties and a key SI base quantity.
 *
 * Mass values must not be negative. A negative mass is not physically meaningful — it would imply the existence of
 * “negative matter,” which is not observed in any real-world context. A mass of zero may be used to represent the
 * absence of matter, but any valid amount of substance must have a non-negative mass.
 *
 * This class models mass as a combination of a [magnitude] and a [prefix], allowing precise values such as milligrams
 * (mg), kilograms (kg), or megagrams (Mg). All values are represented using [BigDecimal] for high-precision
 * calculations.
 *
 * Instances of this class are immutable and validated at construction.
 */
class Mass private constructor(magnitude: BigDecimal, prefix: Metric) :
    Measure<Metric, Mass>(magnitude, prefix, SYMBOL, ::invoke) {

    companion object {
        /** The symbol for mass: "g" (gram). */
        private const val SYMBOL = "g"

        /**
         * Creates a new [Mass] quantity with the given [magnitude] and [prefix].
         *
         * The [magnitude] must be zero or positive. Negative mass is not permitted, as it represents a physically
         * invalid state — mass can be absent (zero), but never less than zero.
         *
         * @param magnitude The numeric value of the mass.
         * @param prefix The metric prefix to apply (e.g., m, k, M).
         * @return A validated [Mass] instance.
         * @throws NegativeMass if the magnitude is less than zero.
         */
        operator fun invoke(
            magnitude: BigDecimal,
            prefix: Metric = Metric.BASE,
        ): Mass {
            if (magnitude.negative) {
                throw NegativeMass(magnitude, prefix, SYMBOL)
            }
            return Mass(magnitude, prefix)
        }
    }
}
