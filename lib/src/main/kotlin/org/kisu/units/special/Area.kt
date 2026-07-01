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
    /**
     * Divides this [Area] by [Length][org.kisu.units.base.Length],
     * yielding [Length][org.kisu.units.base.Length].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Area] by [Time][org.kisu.units.base.Time],
     * yielding [KinematicViscosity][org.kisu.units.mechanics.KinematicViscosity].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.mechanics.KinematicViscosity =
        org.kisu.units.mechanics.KinematicViscosity(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Area] by [Compressibility][org.kisu.units.mechanics.Compressibility],
     * yielding [Force][org.kisu.units.special.Force].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.mechanics.Compressibility
    ): org.kisu.units.special.Force =
        org.kisu.units.special.Force(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Area] by [KinematicViscosity][org.kisu.units.mechanics.KinematicViscosity],
     * yielding [Time][org.kisu.units.base.Time].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.mechanics.KinematicViscosity
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Area] by [WaveNumber][org.kisu.units.mechanics.WaveNumber],
     * yielding [Volume][org.kisu.units.special.Volume].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.mechanics.WaveNumber
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Area] by [Force][org.kisu.units.special.Force],
     * yielding [Compressibility][org.kisu.units.mechanics.Compressibility].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Force
    ): org.kisu.units.mechanics.Compressibility =
        org.kisu.units.mechanics.Compressibility(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [Area] by [Volume][org.kisu.units.special.Volume],
     * yielding [WaveNumber][org.kisu.units.mechanics.WaveNumber].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Volume
    ): org.kisu.units.mechanics.WaveNumber =
        org.kisu.units.mechanics.WaveNumber(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [Area] by [Current][org.kisu.units.base.Current],
     * yielding [MagneticDipoleMoment][org.kisu.units.electromagnetic.MagneticDipoleMoment].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Current
    ): org.kisu.units.electromagnetic.MagneticDipoleMoment =
        org.kisu.units.electromagnetic.MagneticDipoleMoment(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Area] by [Length][org.kisu.units.base.Length],
     * yielding [Volume][org.kisu.units.special.Volume].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Area] by [Mass][org.kisu.units.base.Mass],
     * yielding [MomentOfInertia][org.kisu.units.mechanics.MomentOfInertia].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.mechanics.MomentOfInertia =
        org.kisu.units.mechanics.MomentOfInertia(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Area] by [ElectricCurrentDensity][org.kisu.units.electromagnetic.ElectricCurrentDensity],
     * yielding [Current][org.kisu.units.base.Current].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.electromagnetic.ElectricCurrentDensity
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Area] by [ElectricDisplacementField][org.kisu.units.electromagnetic.ElectricDisplacementField],
     * yielding [ElectricCharge][org.kisu.units.special.ElectricCharge].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.electromagnetic.ElectricDisplacementField
    ): org.kisu.units.special.ElectricCharge =
        org.kisu.units.special.ElectricCharge(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Area] by [AreaDensity][org.kisu.units.mechanics.AreaDensity],
     * yielding [Mass][org.kisu.units.base.Mass].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.mechanics.AreaDensity
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Area] by [HeatFluxDensity][org.kisu.units.mechanics.HeatFluxDensity],
     * yielding [Power][org.kisu.units.special.Power].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.mechanics.HeatFluxDensity
    ): org.kisu.units.special.Power =
        org.kisu.units.special.Power(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Area] by [Radiance][org.kisu.units.mechanics.Radiance],
     * yielding [RadiantIntensity][org.kisu.units.mechanics.RadiantIntensity].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.mechanics.Radiance
    ): org.kisu.units.mechanics.RadiantIntensity =
        org.kisu.units.mechanics.RadiantIntensity(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Area] by [RadiantExposure][org.kisu.units.mechanics.RadiantExposure],
     * yielding [Energy][org.kisu.units.special.Energy].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.mechanics.RadiantExposure
    ): org.kisu.units.special.Energy =
        org.kisu.units.special.Energy(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Area] by [SpectralIrradiance][org.kisu.units.mechanics.SpectralIrradiance],
     * yielding [SpectralPower][org.kisu.units.mechanics.SpectralPower].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.mechanics.SpectralIrradiance
    ): org.kisu.units.mechanics.SpectralPower =
        org.kisu.units.mechanics.SpectralPower(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Area] by [Luminance][org.kisu.units.photometric.Luminance],
     * yielding [LuminousIntensity][org.kisu.units.base.LuminousIntensity].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.photometric.Luminance
    ): org.kisu.units.base.LuminousIntensity =
        org.kisu.units.base.LuminousIntensity(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Area] by [Illuminance][org.kisu.units.special.Illuminance],
     * yielding [LuminousFlux][org.kisu.units.special.LuminousFlux].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.Illuminance
    ): org.kisu.units.special.LuminousFlux =
        org.kisu.units.special.LuminousFlux(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Area] by [MagneticFluxDensity][org.kisu.units.special.MagneticFluxDensity],
     * yielding [MagneticFlux][org.kisu.units.special.MagneticFlux].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.special.MagneticFluxDensity
    ): org.kisu.units.special.MagneticFlux =
        org.kisu.units.special.MagneticFlux(canonical.component1() * other.canonical.component1())

    /**
     * Multiplies this [Area] by [Pressure][org.kisu.units.special.Pressure],
     * yielding [Force][org.kisu.units.special.Force].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
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
