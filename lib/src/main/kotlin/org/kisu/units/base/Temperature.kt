package org.kisu.units.base

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
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
 * This class models temperature as a combination of a [magnitude] and an optional metric [expression],
 * enabling precise representation of values such as millikelvin (mK) or kilokelvin (kK).
 *
 * The magnitude is stored using [BigDecimal] for accuracy. All instances are validated to ensure they
 * respect physical constraints and are immutable once created.
 */
class Temperature internal constructor(magnitude: BigDecimal, expression: Kelvin) :
    Measure<Kelvin, Temperature>(magnitude, expression, ::Temperature) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Kelvin(prefix))
}

class Kelvin private constructor(
    scale: Scale<Metric> = ExponentialScale(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Kelvin>(scale, prefix, unit, ::Kelvin) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The SI symbol for temperature: "K". */
        internal val UNIT = Unit("K", 1)
    }
}
