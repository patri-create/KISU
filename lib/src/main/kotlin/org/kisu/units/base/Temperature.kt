package org.kisu.units.base

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.Scalar
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
class Temperature internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, Temperature>(magnitude, expression, ::Temperature) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, SYMBOL))

    companion object {
        /** The SI symbol for temperature: "K" (kelvin). */
        internal const val SYMBOL = "K"
    }
}
