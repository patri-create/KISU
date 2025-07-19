package org.kisu.units.derived

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.Scalar
import java.math.BigDecimal

/**
 * Represents the physical quantity of **energy**, **work**, or **amount of heat**, measured in joules (J).
 *
 * One joule is equal to the work done when a force of one newton displaces an object by one meter.
 * In base SI units, it is defined as kg·m²·s⁻².
 *
 * Joules are widely used in physics and engineering to quantify energy, heat, and work.
 *
 * This class expresses energy as a combination of a [magnitude] and a [prefix], supporting values such as
 * kilojoules (kJ), megajoules (MJ), or millijoules (mJ).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Energy internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, Energy>(magnitude, expression, ::Energy) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, SYMBOL))

    companion object {
        /** The SI symbol for energy: "J" (joule). */
        internal const val SYMBOL = "J"
    }
}
