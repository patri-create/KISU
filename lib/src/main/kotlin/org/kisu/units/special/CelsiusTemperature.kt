package org.kisu.units.special

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.base.Temperature
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents temperature measured on the **Celsius scale**, using [Celsius].
 *
 * Celsius temperature quantifies thermal state on a scale chosen for everyday and
 * laboratory interpretation. Its interval matches the kelvin scale exactly, but its
 * zero point is shifted by `273.15` relative to absolute temperature.
 *
 * This is the quantity commonly used for weather reports, room temperature, body
 * temperature, cooking, refrigeration, and many practical measurements read directly by
 * people.
 *
 * The associated unit representation is [Celsius] (`°C`).
 */
class CelsiusTemperature internal constructor(magnitude: Magnitude, expression: Celsius) :
    Measure<Celsius, CelsiusTemperature>(magnitude, expression, ::CelsiusTemperature) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Celsius(prefix))

    /**
     * Returns this Celsius temperature as an absolute kelvin temperature.
     */
    val kelvin: Temperature
        get() = Temperature(canonical.component1() + Celsius.KELVIN_TO_CELSIUS)
}

/**
 * Represents the unit **degree Celsius** (`°C`), used to express [CelsiusTemperature].
 *
 * The degree Celsius quantifies temperature on a scale offset from the kelvin scale by
 * 273.15. A temperature difference of `1 °C` is exactly the same size as a difference
 * of `1 K`; the distinction lies in the zero point.
 *
 * Celsius is the unit most commonly used in weather reports, room and body
 * temperatures, cooking, and laboratory readings intended for human interpretation.
 *
 * In this library, [Celsius] models the named scale unit, while [CelsiusTemperature]
 * carries the measured value expressed with that unit.
 *
 * @see CelsiusTemperature
 */
class Celsius private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Celsius>(algebra, prefix, unit, ::Celsius) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for degree Celsius: "°C". */
        internal val UNIT = Unit("°C", 1)
        internal val KELVIN_TO_CELSIUS = Magnitude(273.15)
    }
}
