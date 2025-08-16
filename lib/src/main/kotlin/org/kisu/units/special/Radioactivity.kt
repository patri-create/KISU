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
 * This class expresses activity as a combination of a [magnitude] and an [expression], supporting values such as
 * kilobecquerels (kBq), megabecquerels (MBq), or gigabecquerels (GBq).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Radioactivity internal constructor(magnitude: BigDecimal, expression: Becquerel) :
    Measure<Becquerel, Radioactivity>(magnitude, expression, ::Radioactivity) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Becquerel(prefix))
}

/**
 * Represents the SI derived unit of radioactivity: **becquerel** (Bq).
 *
 * One becquerel is one radioactive decay per second.
 *
 * SI definition: `Bq = s⁻¹`.
 */
class Becquerel private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Becquerel>(prefix, overflow, unit, ::Becquerel) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for becquerel: "Bq". */
        internal val UNIT = Unit("Bq", 1)
    }
}
