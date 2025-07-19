package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.Scalar
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
 * This class expresses electric potential as a combination of a [magnitude] and a [prefix], supporting values such as
 * millivolts (mV), microvolts (µV), or kilovolts (kV).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class ElectricPotential internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, ElectricPotential>(magnitude, expression, ::ElectricPotential) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, SYMBOL))

    companion object {
        /** The SI symbol for electric potential: "V" (volt). */
        internal const val SYMBOL = "V"
    }
}
