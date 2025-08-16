package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **energy**, **work**, or **amount of heat**, measured in joules (J).
 *
 * One joule is equal to the work done when a force of one newton displaces an object by one meter.
 * In base SI units, it is defined as kg·m²·s⁻².
 *
 * Joules are widely used in physics and engineering to quantify energy, heat, and work.
 *
 * This class expresses energy as a combination of a [magnitude] and an [expression], supporting values such as
 * kilojoules (kJ), megajoules (MJ), or millijoules (mJ).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Energy internal constructor(magnitude: BigDecimal, expression: Joule) :
    Measure<Joule, Energy>(magnitude, expression, ::Energy) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Joule(prefix))
}

/**
 * Represents the SI derived unit of energy: **joule** (J).
 *
 * One joule is the energy transferred when a force of one newton
 * acts over a displacement of one metre.
 *
 * SI definition: `J = m²·kg·s⁻²`.
 */
class Joule private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Joule>(prefix, overflow, unit, ::Joule) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for joule: "J". */
        internal val UNIT = Unit("J", 1)
    }
}
