@file:Suppress("TooManyFunctions")

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

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Amount
    ): org.kisu.units.chemistry.MolarMass =
        org.kisu.units.chemistry.MolarMass(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.mechanics.LinearMassDensity =
        org.kisu.units.mechanics.LinearMassDensity(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.mechanics.MassFlowRate =
        org.kisu.units.mechanics.MassFlowRate(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.chemistry.MolarMass
    ): org.kisu.units.base.Amount =
        org.kisu.units.base.Amount(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.AreaDensity
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.Density
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.LinearMassDensity
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.MassFlowRate
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Area
    ): org.kisu.units.mechanics.AreaDensity =
        org.kisu.units.mechanics.AreaDensity(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Volume
    ): org.kisu.units.mechanics.Density =
        org.kisu.units.mechanics.Density(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.chemistry.Molality
    ): org.kisu.units.base.Amount =
        org.kisu.units.base.Amount(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.electromagnetic.Exposure
    ): org.kisu.units.special.ElectricCharge =
        org.kisu.units.special.ElectricCharge(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.linear.Acceleration
    ): org.kisu.units.special.Force =
        org.kisu.units.special.Force(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.linear.Speed
    ): org.kisu.units.mechanics.Momentum =
        org.kisu.units.mechanics.Momentum(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.SpecificAngularMomentum
    ): org.kisu.units.mechanics.AngularMomentum =
        org.kisu.units.mechanics.AngularMomentum(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.SpecificEnergy
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.SpecificVolume
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.Area
    ): org.kisu.units.mechanics.MomentOfInertia =
        org.kisu.units.mechanics.MomentOfInertia(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.thermodynamics.SpecificHeatCapacity
    ): org.kisu.units.thermodynamics.HeatCapacity =
        org.kisu.units.thermodynamics.HeatCapacity(canonical.component1() * other.canonical.component1())
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
