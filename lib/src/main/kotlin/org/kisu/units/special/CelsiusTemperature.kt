package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents **temperature in degrees Celsius (°C)**.
 *
 * Celsius temperature is defined as the temperature in kelvins minus 273.15.
 * While the unit interval is the same as for kelvin (1 °C = 1 K), the zero point differs.
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class CelsiusTemperature internal constructor(magnitude: BigDecimal, expression: Celsius) :
    Measure<Celsius, CelsiusTemperature>(magnitude, expression, ::CelsiusTemperature) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Celsius(prefix))
}

/**
 * Represents the Celsius temperature scale: **degree Celsius** (°C).
 *
 * Defined as kelvin offset by 273.15.
 */
class Celsius private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Celsius>(prefix, overflow, unit, ::Celsius) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for degree Celsius: "°C". */
        internal val UNIT = Unit("°C", 1)
    }
}
