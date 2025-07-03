package org.kisu.units.derived

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Temperature
import java.math.BigDecimal

/**
 * Represents **temperature in degrees Celsius (°C)**.
 *
 * Celsius temperature is defined as the temperature in kelvins minus 273.15.
 * While the unit interval is the same as for kelvin (1 °C = 1 K), the zero point differs.
 *
 * Internally, this class may store temperature in kelvin for consistency.
 * Use [toKelvin] and [fromKelvin] to convert between units.
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Celsius internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
    Measure<Metric, Celsius>(magnitude, prefix, SYMBOL, ::Celsius)  {

    companion object {
        /** The SI symbol for Celsius temperature: "°C". */
        const val SYMBOL = "°C"
    }
}
