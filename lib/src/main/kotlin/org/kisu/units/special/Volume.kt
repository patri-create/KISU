@file:Suppress("TooManyFunctions")

package org.kisu.units.special

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

private const val CUBIC_METRE_PREFIX_BASE = 1000

/**
 * Represents the physical quantity of **volume**, measured in [CubicMetre].
 *
 * Volume quantifies how much three-dimensional space is occupied or enclosed. It is
 * used for containers, rooms, solids, fluids, and any region with spatial extent in
 * three dimensions.
 *
 * Typical examples include the capacity of a tank, the internal space of a room, the
 * volume of fuel consumed, or the size of a solid object.
 *
 * The canonical SI unit is the [CubicMetre] (`m³`), with smaller or larger metric
 * forms such as `cm³` or `km³` used when appropriate.
 */
class Volume internal constructor(magnitude: Magnitude, expression: CubicMetre) :
    Measure<CubicMetre, Volume>(magnitude, expression, ::Volume) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, CubicMetre(prefix))

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Amount
    ): org.kisu.units.chemistry.MolarVolume =
        org.kisu.units.chemistry.MolarVolume(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.mechanics.SpecificVolume =
        org.kisu.units.mechanics.SpecificVolume(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.kinematics.VolumetricFlow =
        org.kisu.units.kinematics.VolumetricFlow(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.chemistry.MolarVolume
    ): org.kisu.units.base.Amount =
        org.kisu.units.base.Amount(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.kinematics.VolumetricFlow
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.SpecificVolume
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Area
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.chemistry.Molarity
    ): org.kisu.units.base.Amount =
        org.kisu.units.base.Amount(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.electromagnetic.ElectricChargeDensity
    ): org.kisu.units.special.ElectricCharge =
        org.kisu.units.special.ElectricCharge(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.Density
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.EnergyDensity
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.FuelEfficiency
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.WaveNumber
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the unit **cubic metre** (`m³`), used to express [Volume].
 *
 * A cubic metre quantifies three-dimensional space. It is the volume enclosed by a
 * cube whose edges are each one metre long.
 *
 * This unit is used for the capacity of rooms, tanks, containers, natural gas usage,
 * rainfall storage, and many engineering calculations involving occupied space.
 *
 * In unit form, `m³` is the product of three lengths.
 *
 * @see Volume
 * @see SquareMetre
 */
class CubicMetre private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(CUBIC_METRE_PREFIX_BASE),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, CubicMetre>(algebra, prefix, unit, ::CubicMetre) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for cubic metre: "m³". */
        internal val UNIT = Unit("m³", 1)
    }
}
