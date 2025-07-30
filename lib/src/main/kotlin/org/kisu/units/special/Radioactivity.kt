package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **radioactivity**, specifically the activity of a radionuclide, measured in
 * becquerels (Bq).
 *
 * One becquerel corresponds to one nuclear decay per second. In SI base units, it is defined as s⁻¹.
 *
 * Becquerels are used in nuclear physics, radiology, and environmental monitoring to measure the rate of radioactive
 * decay.
 *
 * This class expresses activity as a combination of a [magnitude] and a [prefix], supporting values such as
 * kilobecquerels (kBq), megabecquerels (MBq), or gigabecquerels (GBq).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Radioactivity internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, Radioactivity>(magnitude, expression, ::Radioactivity) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, unit = UNIT))

    companion object {
        /** The SI symbol for activity: "Bq" (becquerel). */
        internal val UNIT = Unit("Bq", 1)
    }
}
