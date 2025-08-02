package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import java.math.BigDecimal

/**
 * Represents the physical quantity of **capacitance**, measured in farads (F).
 *
 * One farad is the capacitance of a capacitor in which a charge of one coulomb causes a potential difference of one
 * volt. In base SI units, it is kg⁻¹·m⁻²·s⁴·A².
 *
 * Farads are commonly used in electronics to describe how much electric charge a component can store.
 *
 * This class expresses capacitance as a combination of a [magnitude] and a [prefix], supporting values such as
 * millifarads (mF), microfarads (µF), nanofarads (nF), or picofarads (pF).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Capacitance internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, Capacitance>(magnitude, expression, ::Capacitance) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, SYMBOL))

    companion object {
        /** The SI symbol for capacitance: "F" (farad). */
        internal const val SYMBOL = "F"
    }
}
