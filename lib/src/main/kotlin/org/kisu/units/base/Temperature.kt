package org.kisu.units.base

import org.kisu.negative
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.exceptions.NegativeTemperature
import java.math.BigDecimal

/**
 * Represents the physical quantity of **thermodynamic temperature**, measured in kelvin (K).
 *
 * Temperature quantifies the thermal state of a system in absolute terms,
 * using the **kelvin** as the SI base unit. Unlike degrees Celsius or Fahrenheit, the kelvin scale
 * starts at **absolute zero**, the lowest possible temperature in nature, where particles have
 * minimal thermal motion.
 *
 * Because the kelvin scale is absolute, **negative values are not physically meaningful** — no system
 * can exist below absolute zero. A temperature of zero kelvin represents a complete absence of thermal energy.
 *
 * This class models temperature as a combination of a [magnitude] and an optional metric [prefix],
 * enabling precise representation of values such as millikelvin (mK) or kilokelvin (kK).
 *
 * The magnitude is stored using [BigDecimal] for accuracy. All instances are validated to ensure they
 * respect physical constraints and are immutable once created.
 */
class Temperature private constructor(magnitude: BigDecimal, prefix: Metric) :
    Measure<Metric, Temperature>(magnitude, prefix, SYMBOL, ::invoke) {

    companion object {
        /** The SI symbol for temperature: "K" (kelvin). */
        private const val SYMBOL = "K"

        /**
         * Creates a new [Temperature] quantity with the given [magnitude] and [prefix].
         *
         * The [magnitude] must be zero or positive. Negative temperatures are not permitted in kelvin,
         * as they would represent values below absolute zero — a condition that is physically impossible
         * in classical thermodynamics.
         *
         * @param magnitude The numeric value of the temperature.
         * @param prefix The metric prefix to apply (e.g., m, k).
         * @return A validated [Temperature] instance.
         * @throws NegativeTemperature if the magnitude is less than zero.
         */
        operator fun invoke(
            magnitude: BigDecimal,
            prefix: Metric = Metric.BASE,
        ): Temperature {
            if (magnitude.negative) {
                throw NegativeTemperature(magnitude, prefix, SYMBOL)
            }
            return Temperature(magnitude, prefix)
        }

    }
}
