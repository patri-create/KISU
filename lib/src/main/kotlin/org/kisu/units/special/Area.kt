@file:Suppress("TooManyFunctions")

package org.kisu.units.special

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

private const val SQUARE_METRE_SCALE_BASE = 100

/**
 * Represents the physical quantity of **area**, measured in [SquareMetre].
 *
 * Area quantifies the extent of a surface. It answers how much two-dimensional space a
 * region occupies, regardless of whether that surface is a floor plan, a field, a
 * sheet of material, or a cross section in an engineering model.
 *
 * Typical examples include the surface of a room, the footprint of a building, the
 * cross-sectional area of a pipe, or the exposed face of a solar panel.
 *
 * The canonical SI unit is the [SquareMetre] (`m²`), with smaller or larger metric
 * forms such as `cm²` or `km²` used when convenient.
 */
class Area internal constructor(magnitude: Magnitude, expression: SquareMetre) :
    Measure<SquareMetre, Area>(magnitude, expression, ::Area) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, SquareMetre(prefix))

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.mechanics.KinematicViscosity =
        org.kisu.units.mechanics.KinematicViscosity(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.Compressibility
    ): org.kisu.units.special.Force =
        org.kisu.units.special.Force(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.KinematicViscosity
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.mechanics.WaveNumber
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Force
    ): org.kisu.units.mechanics.Compressibility =
        org.kisu.units.mechanics.Compressibility(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Volume
    ): org.kisu.units.mechanics.WaveNumber =
        org.kisu.units.mechanics.WaveNumber(canonical.component1() / other.canonical.component1())

    operator fun times(
        other: org.kisu.units.base.Current
    ): org.kisu.units.electromagnetic.MagneticDipoleMoment =
        org.kisu.units.electromagnetic.MagneticDipoleMoment(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.mechanics.MomentOfInertia =
        org.kisu.units.mechanics.MomentOfInertia(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.electromagnetic.ElectricCurrentDensity
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.electromagnetic.ElectricDisplacementField
    ): org.kisu.units.special.ElectricCharge =
        org.kisu.units.special.ElectricCharge(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.AreaDensity
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.HeatFluxDensity
    ): org.kisu.units.special.Power =
        org.kisu.units.special.Power(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.Radiance
    ): org.kisu.units.mechanics.RadiantIntensity =
        org.kisu.units.mechanics.RadiantIntensity(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.RadiantExposure
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.SpectralIrradiance
    ): org.kisu.units.mechanics.SpectralPower =
        org.kisu.units.mechanics.SpectralPower(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.photometric.Luminance
    ): org.kisu.units.base.LuminousIntensity =
        org.kisu.units.base.LuminousIntensity(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.Illuminance
    ): org.kisu.units.special.LuminousFlux =
        org.kisu.units.special.LuminousFlux(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.MagneticFluxDensity
    ): org.kisu.units.special.MagneticFlux =
        org.kisu.units.special.MagneticFlux(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.Pressure
    ): org.kisu.units.special.Force =
        org.kisu.units.special.Force(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the unit **square metre** (`m²`), used to express [Area].
 *
 * A square metre quantifies the extent of a two-dimensional surface. It is the area
 * enclosed by a square whose sides are each one metre long.
 *
 * This unit is used for the floor area of a room, the footprint of a building, the
 * size of a solar panel, or the surface of a wall to be painted.
 *
 * In unit form, `m²` is the product of two lengths.
 *
 * @see Area
 * @see CubicMetre
 */
class SquareMetre private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(SQUARE_METRE_SCALE_BASE),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, SquareMetre>(algebra, prefix, unit, ::SquareMetre) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for square metre: "m²". */
        internal val UNIT = Unit("m²", 1)
    }
}
