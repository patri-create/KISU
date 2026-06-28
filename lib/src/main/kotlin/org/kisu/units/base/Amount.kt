@file:Suppress("TooManyFunctions")

package org.kisu.units.base

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.base.Amount.Companion.AVOGADROS_NUMBER
import org.kisu.units.chemistry.ReciprocalAmount
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents the physical quantity of **amount of substance**, measured in moles (mol).
 *
 * This class models the SI base unit for counting discrete entities like atoms, molecules, or particles in a substance.
 * One mole corresponds to [AVOGADROS_NUMBER] elementary entities, typically used in chemistry and physics.
 *
 * The amount is composed of a [magnitude] and an optional metric [expression], allowing expressions such as millimoles
 * (mmol), micromoles (µmol), or kilomoles (kmol).
 *
 * The value must not be negative, as a physical quantity representing a count of real entities cannot be less than
 * zero.
 *
 * Negative amounts would be physically meaningless in the context of matter.
 *
 * Instances of this class are immutable and preserve their precision using [Magnitude].
 */
class Amount internal constructor(magnitude: Magnitude, expression: Mole) :
    Measure<Mole, Amount>(magnitude, expression, ::Amount) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Mole(prefix))

    /**
     * Returns this amount as a reciprocal amount (`mol⁻¹`) by inverting its canonical magnitude.
     */
    val reciprocalAmount: ReciprocalAmount
        get() = ReciprocalAmount(canonical.component1().inverted)

    companion object {
        /**
         * Avogadro's number — the number of entities in one mole:
         * 6.02214076 × 10²³ entities per mole.
         *
         * This is a fundamental physical constant.
         */
        val AVOGADROS_NUMBER: Magnitude = Magnitude("6.02214076e23")
    }

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.chemistry.Molality =
        org.kisu.units.chemistry.Molality(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.special.CatalyticActivity =
        org.kisu.units.special.CatalyticActivity(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.chemistry.Molality
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.chemistry.Molarity
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.CatalyticActivity
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Volume
    ): org.kisu.units.chemistry.Molarity =
        org.kisu.units.chemistry.Molarity(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.chemistry.CatalyticEfficiency
    ): org.kisu.units.kinematics.VolumetricFlow =
        org.kisu.units.kinematics.VolumetricFlow(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.chemistry.MolarEnergy
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.chemistry.MolarHeatCapacity
    ): org.kisu.units.thermodynamics.HeatCapacity =
        org.kisu.units.thermodynamics.HeatCapacity(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.chemistry.MolarMass
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.chemistry.MolarVolume
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() * other.canonical.component1())
}

class Mole private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit,
) : Scalar<Metric, Mole>(algebra, prefix, unit, ::Mole) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The SI symbol for amount of substance: "mol". */
        internal val UNIT = Unit("mol", 1)
    }
}
