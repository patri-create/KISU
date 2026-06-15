package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **electric potential difference**, measured in
 * [Volt].
 *
 * Electric potential difference quantifies how much energy is available per unit charge
 * between two points. It is the quantity usually called voltage in circuits and field
 * problems.
 *
 * Typical examples include the output of a battery, the voltage across a resistor, the
 * supply level of a device, or the potential between two electrodes.
 *
 * The canonical SI unit is the [Volt] (`V`), with common practical forms such as `mV`,
 * `kV`, and everything in between.
 */
class ElectricPotential internal constructor(magnitude: BigDecimal, expression: Volt) :
    Measure<Volt, ElectricPotential>(magnitude, expression, ::ElectricPotential) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Volt(prefix))
}

/**
 * Represents the unit **volt** (`V`), used to express [ElectricPotential].
 *
 * A volt quantifies electric potential difference, or energy available per unit charge.
 * It tells how strongly a source can drive charges through a circuit.
 *
 * Everyday examples include the output of a battery cell, the charging voltage of a
 * USB power supply, or the potential difference across a component in an electric
 * circuit.
 *
 * In unit form, `V = W/A = J/C = m²·kg·s⁻³·A⁻¹`.
 *
 * @see ElectricPotential
 * @see Watt
 * @see Coulomb
 */
class Volt private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Volt>(algebra, prefix, unit, ::Volt) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for volt: "V". */
        internal val UNIT = Unit("V", 1)
    }
}
