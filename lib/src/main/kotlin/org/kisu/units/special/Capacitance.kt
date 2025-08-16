package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **capacitance**, measured in farads (F).
 *
 * One farad is the capacitance of a capacitor in which a charge of one coulomb causes a potential difference of one
 * volt. In base SI units, it is kg⁻¹·m⁻²·s⁴·A².
 *
 * Farads are commonly used in electronics to describe how much electric charge a component can store.
 *
 * This class expresses capacitance as a combination of a [magnitude] and an [expression], supporting values such as
 * millifarads (mF), microfarads (µF), nanofarads (nF), or picofarads (pF).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Capacitance internal constructor(magnitude: BigDecimal, expression: Farad) :
    Measure<Farad, Capacitance>(magnitude, expression, ::Capacitance) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Farad(prefix))
}

/**
 * Represents the SI derived unit of capacitance: **farad** (F).
 *
 * One farad is equal to one coulomb per volt.
 *
 * SI definition: `F = m⁻²·kg⁻¹·s⁴·A²`.
 */
class Farad private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Farad>(prefix, overflow, unit, ::Farad) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for farad: "F". */
        internal val UNIT = Unit("F", 1)
    }
}
