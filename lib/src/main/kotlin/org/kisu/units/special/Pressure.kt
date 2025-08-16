package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **pressure** or **stress**, measured in pascals (Pa).
 *
 * One pascal equals one newton per square meter (N/m²), which is kg·m⁻¹·s⁻² in SI base units.
 * It is used to quantify internal pressure, stress, Young's modulus, and tensile strength.
 *
 * This class expresses pressure as a combination of a [magnitude] and an [expression], supporting values such as
 * kilopascals (kPa), megapascals (MPa), or hectopascals (hPa).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Pressure internal constructor(magnitude: BigDecimal, expression: Pascal) :
    Measure<Pascal, Pressure>(magnitude, expression, ::Pressure) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Pascal(prefix))
}

/**
 * Represents the SI derived unit of pressure: **pascal** (Pa).
 *
 * One pascal is equal to one newton per square metre.
 *
 * SI definition: `Pa = m⁻¹·kg·s⁻²`.
 */
class Pascal private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Pascal>(prefix, overflow, unit, ::Pascal) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for pascal: "Pa". */
        internal val UNIT = Unit("Pa", 1)
    }
}
