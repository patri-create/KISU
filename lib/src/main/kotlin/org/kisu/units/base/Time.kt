@file:Suppress("TooManyFunctions")

package org.kisu.units.base

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.special.Frequency
import org.kisu.units.special.Radioactivity

/**
 * Represents the physical quantity of **time**, measured in seconds (s).
 *
 * Time quantifies the duration of events or intervals. It is one of the fundamental SI base quantities
 * and is universally measured in **seconds**. This class allows precise modeling of durations such as
 * milliseconds (ms), microseconds (µs), or kiloseconds (ks), using metric prefixes.
 *
 * Time values must not be negative. In physical systems and real-world contexts, negative time has no
 * meaning — you cannot go back and create a duration with a negative length. Zero represents an
 * instantaneous or null duration, while positive values represent elapsed or measurable intervals.
 *
 * The magnitude is stored using [Magnitude] to ensure high precision. Instances of this class are immutable
 * and validated to reflect physical reality.
 */
class Time internal constructor(magnitude: Magnitude, expression: Second) :
    Measure<Second, Time>(magnitude, expression, ::Time) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Second(prefix))

    /**
     * Returns the frequency associated with this period by inverting its canonical magnitude.
     */
    val frequency: Frequency
        get() = Frequency(canonical.component1().inverted)

    /**
     * Returns the activity associated with this mean interval by inverting its canonical magnitude.
     */
    val activity: Radioactivity
        get() = Radioactivity(canonical.component1().inverted)

    // Dimension-aware arithmetic
    operator fun times(
        other: org.kisu.units.base.Current
    ): org.kisu.units.special.ElectricCharge =
        org.kisu.units.special.ElectricCharge(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.chemistry.CatalyticEfficiency
    ): org.kisu.units.chemistry.MolarVolume =
        org.kisu.units.chemistry.MolarVolume(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.FrequencyDrift
    ): org.kisu.units.special.Frequency =
        org.kisu.units.special.Frequency(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.VolumetricFlow
    ): org.kisu.units.special.Volume =
        org.kisu.units.special.Volume(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.Yank
    ): org.kisu.units.special.Force =
        org.kisu.units.special.Force(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.angular.Acceleration
    ): org.kisu.units.kinematics.angular.Velocity =
        org.kisu.units.kinematics.angular.Velocity(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.angular.Crackle
    ): org.kisu.units.kinematics.angular.Snap =
        org.kisu.units.kinematics.angular.Snap(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.angular.Jerk
    ): org.kisu.units.kinematics.angular.Acceleration =
        org.kisu.units.kinematics.angular.Acceleration(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.angular.Pop
    ): org.kisu.units.kinematics.angular.Crackle =
        org.kisu.units.kinematics.angular.Crackle(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.angular.Snap
    ): org.kisu.units.kinematics.angular.Jerk =
        org.kisu.units.kinematics.angular.Jerk(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.angular.Velocity
    ): org.kisu.units.special.PlaneAngle =
        org.kisu.units.special.PlaneAngle(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.linear.Acceleration
    ): org.kisu.units.kinematics.linear.Speed =
        org.kisu.units.kinematics.linear.Speed(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.linear.Crackle
    ): org.kisu.units.kinematics.linear.Snap =
        org.kisu.units.kinematics.linear.Snap(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.linear.Jerk
    ): org.kisu.units.kinematics.linear.Acceleration =
        org.kisu.units.kinematics.linear.Acceleration(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.linear.Pop
    ): org.kisu.units.kinematics.linear.Crackle =
        org.kisu.units.kinematics.linear.Crackle(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.linear.Snap
    ): org.kisu.units.kinematics.linear.Jerk =
        org.kisu.units.kinematics.linear.Jerk(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.kinematics.linear.Speed
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.AbsorbedDoseRate
    ): org.kisu.units.special.AbsorbedDose =
        org.kisu.units.special.AbsorbedDose(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.EnergyFluxDensity
    ): org.kisu.units.mechanics.RadiantExposure =
        org.kisu.units.mechanics.RadiantExposure(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.KinematicViscosity
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.mechanics.MassFlowRate
    ): org.kisu.units.base.Mass =
        org.kisu.units.base.Mass(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.CatalyticActivity
    ): org.kisu.units.base.Amount =
        org.kisu.units.base.Amount(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.ElectricPotential
    ): org.kisu.units.special.MagneticFlux =
        org.kisu.units.special.MagneticFlux(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.Energy
    ): org.kisu.units.mechanics.Action =
        org.kisu.units.mechanics.Action(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.Force
    ): org.kisu.units.mechanics.Momentum =
        org.kisu.units.mechanics.Momentum(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.Illuminance
    ): org.kisu.units.photometric.Exposure =
        org.kisu.units.photometric.Exposure(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.LuminousFlux
    ): org.kisu.units.photometric.LuminousEnergy =
        org.kisu.units.photometric.LuminousEnergy(canonical.component1() * other.canonical.component1())

    operator fun times(
        other: org.kisu.units.special.Pressure
    ): org.kisu.units.mechanics.DynamicViscosity =
        org.kisu.units.mechanics.DynamicViscosity(canonical.component1() * other.canonical.component1())
}

/**
 * Represents the SI base unit of **time**.
 *
 * The second (s) is the standard unit for measuring duration.
 */
class Second private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Second>(algebra, prefix, unit, ::Second) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical SI symbol for time: "s". */
        internal val UNIT = Unit("s", 1)
    }
}
