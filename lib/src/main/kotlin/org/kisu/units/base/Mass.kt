package org.kisu.units.base

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

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
 * This class models mass as a combination of a [magnitude] and an [expression], allowing precise values such as
 * milligrams (mg), kilograms (kg), or megagrams (Mg). All values are represented using [Magnitude] for high-precision
 * calculations.
 *
 * Instances of this class are immutable and validated at construction.
 */
class Mass internal constructor(magnitude: Magnitude, expression: Kilogram) :
    Measure<Kilogram, Mass>(magnitude, expression, ::Mass) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.KILO) :
        this(magnitude, Kilogram(prefix to Magnitude.ONE))
}

/**
 * Represents the SI base unit of **mass**.
 *
 * The kilogram (kg) is the SI base unit for mass.
 *
 * Internally, this scalar normalizes through grams (`g`) so metric prefixes work consistently,
 * while the public canonical symbol remains `kg`:
 * - Kilogram(Metric.BASE) = 1 kg
 * - Kilogram(Metric.KILO) = 1 Mg
 * - Kilogram(Metric.MILLI) = 1 g
 * - Kilogram(Metric.MICRO) = 1 mg
 */
class Kilogram private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Kilogram>(algebra, prefix, unit, ::Kilogram) {

    constructor(pair: Pair<Metric, Magnitude>) : this(
        algebra = ExponentialAlgebra<Metric>().adjustedBy(pair.second),
        prefix = pair.first,
        unit = UNIT,
    )

    constructor(prefix: Metric = Metric.BASE) : this(ExponentialAlgebra<Metric>().multiply(prefix, Metric.KILO))

    /** The public canonical mass unit is always kilogram (`kg`). */
    override val canonical: Kilogram by lazy { Kilogram() }

    companion object {
        /** The canonical unit symbol used internally: "g". */
        internal val UNIT = Unit("g", 1)
    }
}

private fun Algebra<Metric>.adjustedBy(remainder: Magnitude): Algebra<Metric> {
    if (remainder == Magnitude.ONE) {
        return this
    }

    val delegate = this
    return object : Algebra<Metric> {
        override fun factor(prefix: Metric): Magnitude = delegate.factor(prefix) * remainder

        override fun multiply(left: Metric, right: Metric): Pair<Metric, Magnitude> =
            delegate.multiply(left, right).let { (prefix, overflow) -> prefix to overflow * remainder }

        override fun divide(left: Metric, right: Metric): Pair<Metric, Magnitude> =
            delegate.divide(left, right).let { (prefix, overflow) ->
                prefix to overflow / remainder
            }
    }
}
