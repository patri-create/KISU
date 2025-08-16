package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **electric potential difference**, measured in volts (V).
 *
 * One volt is defined as the potential difference across a conductor when a current of one ampere dissipates one watt
 * of power. In SI base units, it is kg·m²·s⁻³·A⁻¹.
 *
 * Volts are used extensively in electrical circuits to describe voltage, electromotive force, and potential energy
 * per charge.
 *
 * This class expresses electric potential as a combination of a [magnitude] and an [expression], supporting values
 * such as millivolts (mV), microvolts (µV), or kilovolts (kV).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class ElectricPotential internal constructor(magnitude: BigDecimal, expression: Volt) :
    Measure<Volt, ElectricPotential>(magnitude, expression, ::ElectricPotential) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Volt(prefix))
}

/**
 * Represents the SI derived unit of electric potential: **volt** (V).
 *
 * One volt is the potential difference between two points of a conductor
 * carrying a constant current of one ampere, when the power dissipated between
 * these points is one watt.
 *
 * SI definition: `V = m²·kg·s⁻³·A⁻¹`.
 */
class Volt private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Volt>(prefix, overflow, unit, ::Volt) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for volt: "V". */
        internal val UNIT = Unit("V", 1)
    }
}
