package org.kisu.units.special

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents the physical quantity of **power**, measured in [Watt].
 *
 * Power quantifies how quickly energy is transferred, converted, or expended. It is
 * the rate form of energy, appearing in electrical systems, mechanical devices,
 * heating, radiation, and fluid transport.
 *
 * Typical examples include the output of a motor, the consumption of an appliance, the
 * thermal power of a heater, or the radiant output of a source.
 *
 * The canonical SI unit is the [Watt] (`W`), commonly scaled as `mW`, `kW`, or `MW`.
 */
class Power internal constructor(magnitude: Magnitude, expression: Watt) :
    Measure<Watt, Power>(magnitude, expression, ::Power) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Watt(prefix))
}

/**
 * Represents the unit **watt** (`W`), used to express [Power].
 *
 * A watt quantifies the rate at which energy is transferred or work is performed. One
 * watt means one [Joule] of energy transferred each second.
 *
 * This unit is used for light bulb ratings, motor output, appliance consumption,
 * battery charging, and thermal transfer rates.
 *
 * In unit form, `W = J/s = m²·kg·s⁻³`.
 *
 * @see Power
 * @see Joule
 * @see Volt
 */
class Watt private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Watt>(algebra, prefix, unit, ::Watt) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for watt: "W". */
        internal val UNIT = Unit("W", 1)
    }
}
