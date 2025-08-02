package org.kisu.units.base

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import java.math.BigDecimal

/**
 * Represents the physical quantity of **mass**, measured in grams (g).
 *
 * Mass quantifies the amount of matter contained in a physical object. It is one of the most fundamental physical
 * properties and a key SI base quantity.
 *
 * Mass values must not be negative. A negative mass is not physically meaningful — it would imply the existence of
 * “negative matter,” which is not observed in any real-world context. A mass of zero may be used to represent the
 * absence of matter, but any valid amount of substance must have a non-negative mass.
 *
 * This class models mass as a combination of a [magnitude] and a [prefix], allowing precise values such as milligrams
 * (mg), kilograms (kg), or megagrams (Mg). All values are represented using [BigDecimal] for high-precision
 * calculations.
 *
 * Instances of this class are immutable and validated at construction.
 */
class Mass internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, Mass>(magnitude, expression, ::Mass) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, SYMBOL))

    companion object {
        /** The symbol for mass: "g" (gram). */
        internal const val SYMBOL = "g"
    }
}
