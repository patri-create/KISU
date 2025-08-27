package org.kisu.units.builders

import org.kisu.prefixes.Metric
import org.kisu.units.base.Amount
import org.kisu.units.base.Current
import org.kisu.units.base.Length
import org.kisu.units.base.LuminousIntensity
import org.kisu.units.base.Mass
import org.kisu.units.base.Temperature
import org.kisu.units.base.Time
import org.kisu.units.chemistry.CatalyticEfficiency
import org.kisu.units.chemistry.Molality
import org.kisu.units.chemistry.MolarConductivity
import org.kisu.units.chemistry.MolarEnergy
import org.kisu.units.chemistry.MolarHeatCapacity
import org.kisu.units.chemistry.MolarMass
import org.kisu.units.chemistry.MolarVolume
import org.kisu.units.chemistry.Molarity
import org.kisu.units.electromagnetic.ElectricChargeDensity
import org.kisu.units.electromagnetic.ElectricConductivity
import org.kisu.units.electromagnetic.ElectricCurrentDensity
import org.kisu.units.electromagnetic.ElectricDisplacementField
import org.kisu.units.electromagnetic.ElectricFieldStrength
import org.kisu.units.electromagnetic.ElectronMobility
import org.kisu.units.electromagnetic.Exposure
import org.kisu.units.electromagnetic.LinearChargeDensity
import org.kisu.units.electromagnetic.MagneticDipoleMoment
import org.kisu.units.electromagnetic.MagneticMoment
import org.kisu.units.electromagnetic.MagneticPermittivity
import org.kisu.units.electromagnetic.MagneticReluctance
import org.kisu.units.electromagnetic.MagneticRigidity
import org.kisu.units.electromagnetic.MagneticSusceptibility
import org.kisu.units.electromagnetic.MagneticVectorPotential
import org.kisu.units.electromagnetic.Magnetization
import org.kisu.units.electromagnetic.MagnetomotiveForce
import org.kisu.units.electromagnetic.Permittivity
import org.kisu.units.electromagnetic.Resistivity
import org.kisu.units.kinematics.FrequencyDrift
import org.kisu.units.kinematics.VolumetricFlow
import org.kisu.units.kinematics.Yank
import org.kisu.units.kinematics.angular.Velocity
import org.kisu.units.kinematics.linear.Acceleration
import org.kisu.units.kinematics.linear.Crackle
import org.kisu.units.kinematics.linear.Jerk
import org.kisu.units.kinematics.linear.Pop
import org.kisu.units.kinematics.linear.Snap
import org.kisu.units.kinematics.linear.Speed
import org.kisu.units.mechanics.AbsorbedDoseRate
import org.kisu.units.mechanics.Action
import org.kisu.units.mechanics.AngularMomentum
import org.kisu.units.mechanics.AreaDensity
import org.kisu.units.mechanics.Compressibility
import org.kisu.units.mechanics.Density
import org.kisu.units.mechanics.DynamicViscosity
import org.kisu.units.mechanics.EnergyDensity
import org.kisu.units.mechanics.EnergyFluxDensity
import org.kisu.units.mechanics.FuelEfficiency
import org.kisu.units.mechanics.HeatFluxDensity
import org.kisu.units.mechanics.KinematicViscosity
import org.kisu.units.mechanics.LinearMassDensity
import org.kisu.units.mechanics.MassFlowRate
import org.kisu.units.mechanics.MomentOfIntertia
import org.kisu.units.mechanics.Momentum
import org.kisu.units.mechanics.Radiance
import org.kisu.units.mechanics.RadiantExposure
import org.kisu.units.mechanics.RadiantIntensity
import org.kisu.units.mechanics.SpecificAngularMomentum
import org.kisu.units.mechanics.SpecificEnergy
import org.kisu.units.mechanics.SpecificVolume
import org.kisu.units.mechanics.SpectralIrradiance
import org.kisu.units.mechanics.SpectralPower
import org.kisu.units.mechanics.SpectralRadiance
import org.kisu.units.mechanics.Spectralntensity
import org.kisu.units.mechanics.SurfaceTension
import org.kisu.units.photometric.Efficacy
import org.kisu.units.photometric.Luminance
import org.kisu.units.photometric.LuminousEnergy
import org.kisu.units.special.AbsorbedDose
import org.kisu.units.special.Area
import org.kisu.units.special.Capacitance
import org.kisu.units.special.CatalyticActivity
import org.kisu.units.special.CelsiusTemperature
import org.kisu.units.special.Conductance
import org.kisu.units.special.DoseEquivalent
import org.kisu.units.special.ElectricCharge
import org.kisu.units.special.ElectricPotential
import org.kisu.units.special.Energy
import org.kisu.units.special.Force
import org.kisu.units.special.Frequency
import org.kisu.units.special.Illuminance
import org.kisu.units.special.Inductance
import org.kisu.units.special.LuminousFlux
import org.kisu.units.special.MagneticFlux
import org.kisu.units.special.MagneticFluxDensity
import org.kisu.units.special.PlaneAngle
import org.kisu.units.special.Power
import org.kisu.units.special.Pressure
import org.kisu.units.special.Radioactivity
import org.kisu.units.special.Resistance
import org.kisu.units.special.SolidAngle
import org.kisu.units.special.Volume
import org.kisu.units.thermodynamics.HeatCapacity
import org.kisu.units.thermodynamics.SpecificHeatCapacity
import org.kisu.units.thermodynamics.TemperatureGradient
import org.kisu.units.thermodynamics.ThermalConductivity
import org.kisu.units.thermodynamics.ThermalExpansionCoefficient
import org.kisu.units.thermodynamics.ThermalResistance
import java.math.BigDecimal
import org.kisu.units.kinematics.angular.Acceleration as AngularAcceleration
import org.kisu.units.kinematics.angular.Crackle as AngularCrackle
import org.kisu.units.kinematics.angular.Jerk as AngularJerk
import org.kisu.units.kinematics.angular.Pop as AngularPop
import org.kisu.units.kinematics.angular.Snap as AngularSnap

/**
 * Represents a metric unit builder carrying a magnitude value.
 * Implementations correspond to metric prefixes like kilo, milli, etc.
 */
sealed interface MetricUnitBuilder {
    /**
     * The numeric magnitude associated with this metric prefix.
     * For example, for `25.kilo`, magnitude would be 25.
     */
    val magnitude: BigDecimal

    /** The metric prefix associated with this builder (e.g., [Metric.KILO], [Metric.MILLI]). */
    val metric: Metric
}

/**
 * Creates a [Magnetization] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val magnetization = 1.amperePerMetre // 1 A/m
 * ```
 */
val MetricUnitBuilder.amperePerMetre: Magnetization
    get() = Magnetization(magnitude, metric)

/**
 * Creates an [ElectricCurrentDensity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val currentDensity = 1.micro.amperePerSquareMetre // 1 * 10^-6 A/m²
 * ```
 */
val MetricUnitBuilder.amperePerSquareMetre: ElectricCurrentDensity
    get() = ElectricCurrentDensity(magnitude, metric)

/**
 * Creates a [MagnetomotiveForce] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val mmf = 1.kilo.ampereRadian // 1000 A·rad
 * ```
 */
val MetricUnitBuilder.ampereRadian: MagnetomotiveForce
    get() = MagnetomotiveForce(magnitude, metric)

/**
 * Creates a [Current] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val current = 1.micro.amperes // 1 * 10^-6 amperes
 * ```
 */
val MetricUnitBuilder.amperes: Current get() = Current(magnitude, metric)

/**
 * Creates a [Radioactivity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val activity = 150.kilo.becquerels // 150 * 10^3 becquerels
 * ```
 */
val MetricUnitBuilder.becquerels: Radioactivity get() = Radioactivity(magnitude, metric)

/**
 * Creates a [Byte] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val size = 10.mega.bytes // 10 * 10^6 bytes
 * ```
 */
val MetricUnitBuilder.bytes: org.kisu.units.special.Bytes get() = org.kisu.units.special.Bytes(magnitude, metric)

/**
 * Creates a [Luminance] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val luminance = 1.kilo.candelaPerSquareMetre // 1000 cd/m²
 * ```
 */
val MetricUnitBuilder.candelaPerSquareMetre: Luminance
    get() = Luminance(magnitude, metric)

/**
 * Creates a [LuminousIntensity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val intensity = 10.hecto.candelas // 10 * 10^2 candelas
 * ```
 */
val MetricUnitBuilder.candelas: LuminousIntensity get() = LuminousIntensity(magnitude, metric)

/**
 * Creates a [CelsiusTemperature] measure with the given magnitude in degrees Celsius.
 *
 * Example usage:
 * ```
 * val temp = 37.degreesCelsius // 37 °C
 * ```
 */
val MetricUnitBuilder.celsius: CelsiusTemperature get() = CelsiusTemperature(magnitude, metric)

/**
 * Creates an [ElectricChargeDensity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val chargeDensity = 1.micro.coulombPerCubicMetre // 1 * 10^-6 C/m³
 * ```
 */
val MetricUnitBuilder.coulombPerCubicMetre: ElectricChargeDensity
    get() = ElectricChargeDensity(magnitude, metric)

/**
 * Creates an [Exposure] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val exposure = 1.milli.coulombPerKilogram // 0.001 C/kg
 * ```
 */
val MetricUnitBuilder.coulombPerKilogram: Exposure
    get() = Exposure(magnitude, metric)

/**
 * Creates a [LinearChargeDensity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val linearCharge = 1.micro.coulombPerMetre // 1 * 10^-6 C/m
 * ```
 */
val MetricUnitBuilder.coulombPerMetre: LinearChargeDensity
    get() = LinearChargeDensity(magnitude, metric)

/**
 * Creates an [ElectricDisplacementField] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val displacementField = 1.kilo.coulombPerSquareMetre // 1000 C/m²
 * ```
 */
val MetricUnitBuilder.coulombPerSquareMetre: ElectricDisplacementField
    get() = ElectricDisplacementField(magnitude, metric)

/**
 * Creates an [ElectricCharge] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val charge = 2.milli.coulombs // 2 * 10^-3 coulombs
 * ```
 */
val MetricUnitBuilder.coulombs: ElectricCharge get() = ElectricCharge(magnitude, metric)

/**
 * Creates a [SpecificVolume] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val specificVolume = 1.kilo.cubicMetrePerKilogram // 1000 m³/kg
 * ```
 */
val MetricUnitBuilder.cubicMetrePerKilogram: SpecificVolume
    get() = SpecificVolume(magnitude, metric)

/**
 * Creates a [MolarVolume] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val molarVolume = 1.milli.cubicMetrePerMole // 0.001 m³/mol
 * ```
 */
val MetricUnitBuilder.cubicMetrePerMole: MolarVolume
    get() = MolarVolume(magnitude, metric)

/**
 * Creates a [CatalyticEfficiency] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val catalyticEfficiency = 1.micro.cubicMetrePerMoleSecond // 1 * 10^-6 m³/(mol·s)
 * ```
 */
val MetricUnitBuilder.cubicMetrePerMoleSecond: CatalyticEfficiency
    get() = CatalyticEfficiency(magnitude, metric)

/**
 * Creates a [VolumetricFlow] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val volumetricFlow = 1.litre.cubicMetrePerSecond // 0.001 m³/s
 * ```
 */
val MetricUnitBuilder.cubicMetrePerSecond: VolumetricFlow
    get() = VolumetricFlow(magnitude, metric)

/**
 * Creates a [Volume] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val v = 2.kilo.cubicMetres // 2 * 10^3 cubic metres
 * ```
 */
val MetricUnitBuilder.cubicMetres: Volume get() = Volume(magnitude, metric)

/**
 * Creates a [Permittivity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val permittivity = 1.micro.faradPerMetre // 1 * 10^-6 F/m
 * ```
 */
val MetricUnitBuilder.faradPerMetre: Permittivity
    get() = Permittivity(magnitude, metric)

/**
 * Creates a [Capacitance] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val cap = 220.micro.farads // 220 * 10^-6 farads
 * ```
 */
val MetricUnitBuilder.farads: Capacitance get() = Capacitance(magnitude, metric)

/**
 * Creates a [Mass] measure by applying the metric prefix scale to the magnitude.
 *
 * Note: The SI base unit is kilogram, but this treats grams as base unit with prefix scaling.
 *
 * Example usage:
 * ```
 * val mass = 500.milli.grams // 500 * 10^-3 grams
 * ```
 */
val MetricUnitBuilder.grams: Mass get() = Mass(magnitude, metric)

/**
 * Creates an [AbsorbedDoseRate] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val doseRate = 1.milli.grayPerSecond // 0.001 Gy/s
 * ```
 */
val MetricUnitBuilder.grayPerSecond: AbsorbedDoseRate
    get() = AbsorbedDoseRate(magnitude, metric)

/**
 * Creates an [AbsorbedDose] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val dose = 2.milli.grays // 2 * 10^-3 grays
 * ```
 */
val MetricUnitBuilder.grays: AbsorbedDose get() = AbsorbedDose(magnitude, metric)

/**
 * Creates an [Inductance] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val induct = 5.micro.henries // 5 * 10^-6 henries
 * ```
 */
val MetricUnitBuilder.henries: Inductance get() = Inductance(magnitude, metric)

/**
 * Creates a [MagneticPermittivity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val magneticPermittivity = 1.kilo.henryPerMetre // 1000 H/m
 * ```
 */
val MetricUnitBuilder.henryPerMetre: MagneticPermittivity
    get() = MagneticPermittivity(magnitude, metric)

/**
 * Creates a [Frequency] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val freq = 440.kilo.hertz // 440 * 10^3 hertz
 * ```
 */
val MetricUnitBuilder.hertz: Frequency get() = Frequency(magnitude, metric)

/**
 * Creates a [FrequencyDrift] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val frequencyDrift = 1.milli.hertzPerSecond // 0.001 Hz/s
 * ```
 */
val MetricUnitBuilder.hertzPerSecond: FrequencyDrift
    get() = FrequencyDrift(magnitude, metric)

/**
 * Creates an [EnergyDensity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val energyDensity = 1.kilo.joulePerCubicMetre // 1000 J/m³
 * ```
 */
val MetricUnitBuilder.joulePerCubicMetre: EnergyDensity
    get() = EnergyDensity(magnitude, metric)

/**
 * Creates a [HeatCapacity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val heatCapacity = 1.kilo.joulePerKelvin // 1000 J/K
 * ```
 */
val MetricUnitBuilder.joulePerKelvin: HeatCapacity
    get() = HeatCapacity(magnitude, metric)

/**
 * Creates a [MolarHeatCapacity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val molarHeatCapacity = 1.joulePerKelvinMole // 1 J/(K·mol)
 * ```
 */
val MetricUnitBuilder.joulePerKelvinMole: MolarHeatCapacity
    get() = MolarHeatCapacity(magnitude, metric)

/**
 * Creates a [SpecificEnergy] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val specificEnergy = 1.kilo.joulePerKilogram // 1000 J/kg
 * ```
 */
val MetricUnitBuilder.joulePerKilogram: SpecificEnergy
    get() = SpecificEnergy(magnitude, metric)

/**
 * Creates a [SpecificHeatCapacity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val specificHeat = 1.kilo.joulePerKilogramKelvin // 1000 J/(kg·K)
 * ```
 */
val MetricUnitBuilder.joulePerKilogramKelvin: SpecificHeatCapacity
    get() = SpecificHeatCapacity(magnitude, metric)

/**
 * Creates a [MolarEnergy] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val molarEnergy = 1.kilo.joulePerMole // 1000 J/mol
 * ```
 */
val MetricUnitBuilder.joulePerMole: MolarEnergy
    get() = MolarEnergy(magnitude, metric)

/**
 * Creates a [RadiantExposure] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val radiantExposure = 1.milli.joulePerSquareMetre // 0.001 J/m²
 * ```
 */
val MetricUnitBuilder.joulePerSquareMetre: RadiantExposure
    get() = RadiantExposure(magnitude, metric)

/**
 * Creates an [EnergyFluxDensity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val energyFlux = 1.kilo.joulePerSquareMetreSecond // 1000 J/(m²·s)
 * ```
 */
val MetricUnitBuilder.joulePerSquareMetreSecond: EnergyFluxDensity
    get() = EnergyFluxDensity(magnitude, metric)

/**
 * Creates a [MagneticDipoleMoment] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val dipoleMoment = 1.kilo.joulePerTesla // 1000 J/T
 * ```
 */
val MetricUnitBuilder.joulePerTesla: MagneticDipoleMoment
    get() = MagneticDipoleMoment(magnitude, metric)

/**
 * Creates an [Energy] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val energy = 5.mega.joules // 5 * 10^6 joules
 * ```
 */
val MetricUnitBuilder.joules: Energy get() = Energy(magnitude, metric)

/**
 * Creates an [Action] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val action = 1.kilo.jouleSecond // 1000 J·s
 * ```
 */
val MetricUnitBuilder.jouleSecond: Action
    get() = Action(magnitude, metric)

/**
 * Creates a [CatalyticActivity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val activity = 10.milli.katals // 10 * 10^-3 katals
 * ```
 */
val MetricUnitBuilder.katals: CatalyticActivity get() = CatalyticActivity(magnitude, metric)

/**
 * Creates a [TemperatureGradient] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val tempGradient = 1.kelvinPerMetre // 1 K/m
 * ```
 */
val MetricUnitBuilder.kelvinPerMetre: TemperatureGradient
    get() = TemperatureGradient(magnitude, metric)

/**
 * Creates a [ThermalResistance] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val thermalResistance = 1.kelvinPerWatt // 1 K/W
 * ```
 */
val MetricUnitBuilder.kelvinPerWatt: ThermalResistance
    get() = ThermalResistance(magnitude, metric)

/**
 * Creates a [Temperature] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val temp = 2.kilo.kelvins // 2 * 10^3 kelvins
 * ```
 */
val MetricUnitBuilder.kelvins: Temperature get() = Temperature(magnitude, metric)

/**
 * Creates a [Yank] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val yank = 1.kilogramMetre // 1 kg·m/s³
 * ```
 */
val MetricUnitBuilder.kilogramMetre: Yank
    get() = Yank(magnitude, metric)

/**
 * Creates a [Yank] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val yank = 1.kilogramMetreSecondThird // 1 kg·m/s³
 * ```
 */
val MetricUnitBuilder.kilogramMetreSecondThird: Yank
    get() = Yank(magnitude, metric)

/**
 * Creates a [Density] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val density = 1.kilogramPerCubicMetre // 1 kg/m³
 * ```
 */
val MetricUnitBuilder.kilogramPerCubicMetre: Density
    get() = Density(magnitude, metric)

/**
 * Creates a [LinearMassDensity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val linearMassDensity = 1.kilogramPerMetre // 1 kg/m
 * ```
 */
val MetricUnitBuilder.kilogramPerMetre: LinearMassDensity
    get() = LinearMassDensity(magnitude, metric)

/**
 * Creates a [MolarMass] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val molarMass = 1.kilogramPerMole // 1 kg/mol
 * ```
 */
val MetricUnitBuilder.kilogramPerMole: MolarMass
    get() = MolarMass(magnitude, metric)

/**
 * Creates a [MassFlowRate] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val massFlow = 1.kilogramPerSecond // 1 kg/s
 * ```
 */
val MetricUnitBuilder.kilogramPerSecond: MassFlowRate
    get() = MassFlowRate(magnitude, metric)

/**
 * Creates an [AreaDensity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val areaDensity = 1.kilogramPerSquareMetre // 1 kg/m²
 * ```
 */
val MetricUnitBuilder.kilogramPerSquareMetre: AreaDensity
    get() = AreaDensity(magnitude, metric)

/**
 * Creates a [MomentOfIntertia] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val inertia = 1.kilogramSquareMetre // 1 kg·m²
 * ```
 */
val MetricUnitBuilder.kilogramSquareMetre: MomentOfIntertia
    get() = MomentOfIntertia(magnitude, metric)

/**
 * Creates an [Efficacy] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val efficacy = 1.lumenPerWatt // 1 lm/W
 * ```
 */
val MetricUnitBuilder.lumenPerWatt: Efficacy
    get() = Efficacy(magnitude, metric)

/**
 * Creates a [LuminousFlux] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val luminousFlux = 800.lumens // 800 lumens (no prefix applied)
 * ```
 */
val MetricUnitBuilder.lumens: LuminousFlux get() = LuminousFlux(magnitude, metric)

/**
 * Creates a [LuminousEnergy] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val luminousEnergy = 1.lumenSecond // 1 lm·s
 * ```
 */
val MetricUnitBuilder.lumenSecond: LuminousEnergy
    get() = LuminousEnergy(magnitude, metric)

/**
 * Creates an [Illuminance] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val illuminance = 500.lux // 500 lux (no prefix applied)
 * ```
 */
val MetricUnitBuilder.lux: Illuminance get() = Illuminance(magnitude, metric)

/**
 * Creates an [Exposure] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val exposure = 1.luxSecond // 1 lx·s
 * ```
 */
val MetricUnitBuilder.luxSecond: Exposure
    get() = Exposure(magnitude, metric)

/**
 * Creates a [FuelEfficiency] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val fuelEfficiency = 1.metrePerCubicMetre // 1 m/m³
 * ```
 */
val MetricUnitBuilder.metrePerCubicMetre: FuelEfficiency
    get() = FuelEfficiency(magnitude, metric)

/**
 * Creates a [MagneticSusceptibility] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val magneticSusceptibility = 1.metrePerHenry // 1 m/H
 * ```
 */
val MetricUnitBuilder.metrePerHenry: MagneticSusceptibility
    get() = MagneticSusceptibility(magnitude, metric)

/**
 * Creates a [Speed] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val speed = 1.metrePerSecond // 1 m/s
 * ```
 */
val MetricUnitBuilder.metrePerSecond: Speed
    get() = Speed(magnitude, metric)

/**
 * Creates a [Jerk] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val jerk = 1.metrePerSecondCubed // 1 m/s³
 * ```
 */
val MetricUnitBuilder.metrePerSecondCubed: Jerk
    get() = Jerk(magnitude, metric)

/**
 * Creates a [Crackle] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val crackle = 1.metrePerSecondFifth // 1 m/s⁵
 * ```
 */
val MetricUnitBuilder.metrePerSecondFifth: Crackle
    get() = Crackle(magnitude, metric)

/**
 * Creates a [Snap] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val snap = 1.metrePerSecondFourth // 1 m/s⁴
 * ```
 */
val MetricUnitBuilder.metrePerSecondFourth: Snap
    get() = Snap(magnitude, metric)

/**
 * Creates a [Pop] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val pop = 1.metrePerSecondSixth // 1 m/s⁶
 * ```
 */
val MetricUnitBuilder.metrePerSecondSixth: Pop
    get() = Pop(magnitude, metric)

/**
 * Creates an [Acceleration] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val acceleration = 1.metrePerSecondSquared // 1 m/s²
 * ```
 */
val MetricUnitBuilder.metrePerSecondSquared: Acceleration
    get() = Acceleration(magnitude, metric)

/**
 * Creates a [Length] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val length = 25.kilo.metres // 25 * 10^3 metres
 * ```
 */
val MetricUnitBuilder.metres: Length get() = Length(magnitude, metric)

/**
 * Creates a [Molarity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val molarity = 1.molePerCubicMetre // 1 mol/m³
 * ```
 */
val MetricUnitBuilder.molePerCubicMetre: Molarity
    get() = Molarity(magnitude, metric)

/**
 * Creates a [Molality] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val molality = 1.molPerKilogram // 1 mol/kg
 * ```
 */
val MetricUnitBuilder.molPerKilogram: Molality
    get() = Molality(magnitude, metric)

/**
 * Creates an [Amount] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val amount = 3.milli.moles // 3 * 10^-3 moles
 * ```
 */
val MetricUnitBuilder.moles: Amount get() = Amount(magnitude, metric)

/**
 * Creates an [AngularMomentum] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val angularMomentum = 1.newtonMeterSecond // 1 N·m·s
 * ```
 */
val MetricUnitBuilder.newtonMeterSecond: AngularMomentum
    get() = AngularMomentum(magnitude, metric)

/**
 * Creates a [SpecificAngularMomentum] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val specificAngularMomentum = 1.newtonMetreSecondPerKilogram // 1 N·m·s/kg
 * ```
 */
val MetricUnitBuilder.newtonMetreSecondPerKilogram: SpecificAngularMomentum
    get() = SpecificAngularMomentum(magnitude, metric)

/**
 * Creates a [SurfaceTension] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val surfaceTension = 1.newtonPerMetre // 1 N/m
 * ```
 */
val MetricUnitBuilder.newtonPerMetre: SurfaceTension
    get() = SurfaceTension(magnitude, metric)

/**
 * Creates a [Force] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val force = 10.kilo.newtons // 10 * 10^3 newtons
 * ```
 */
val MetricUnitBuilder.newtons: Force get() = Force(magnitude, metric)

/**
 * Creates a [Momentum] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val momentum = 1.newtonSecond // 1 N·s
 * ```
 */
val MetricUnitBuilder.newtonSecond: Momentum
    get() = Momentum(magnitude, metric)

/**
 * Creates a [Resistivity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val resistivity = 1.ohmMetre // 1 Ω·m
 * ```
 */
val MetricUnitBuilder.ohmMetre: Resistivity
    get() = Resistivity(magnitude, metric)

/**
 * Creates a [Resistance] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val res = 47.kilo.ohms // 47 * 10^3 ohms
 * ```
 */
val MetricUnitBuilder.ohms: Resistance get() = Resistance(magnitude, metric)

/**
 * Creates a [Pressure] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val pressure = 101.3.kilo.pascals // 101.3 * 10^3 pascals
 * ```
 */
val MetricUnitBuilder.pascals: Pressure get() = Pressure(magnitude, metric)

/**
 * Creates a [DynamicViscosity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val viscosity = 1.pascalSecond // 1 Pa·s
 * ```
 */
val MetricUnitBuilder.pascalSecond: DynamicViscosity
    get() = DynamicViscosity(magnitude, metric)

/**
 * Creates a [Velocity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val angularVelocity = 1.radianPerSecond // 1 rad/s
 * ```
 */
val MetricUnitBuilder.radianPerSecond: Velocity
    get() = Velocity(magnitude, metric)

/**
 * Creates an [AngularJerk] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val angularJerk = 1.radianPerSecondCubed // 1 rad/s³
 * ```
 */
val MetricUnitBuilder.radianPerSecondCubed: AngularJerk
    get() = AngularJerk(magnitude, metric)

/**
 * Creates an [AngularCrackle] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val angularCrackle = 1.radianPerSecondFifth // 1 rad/s⁵
 * ```
 */
val MetricUnitBuilder.radianPerSecondFifth: AngularCrackle
    get() = AngularCrackle(magnitude, metric)

/**
 * Creates an [AngularSnap] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val angularSnap = 1.radianPerSecondFourth // 1 rad/s⁴
 * ```
 */
val MetricUnitBuilder.radianPerSecondFourth: AngularSnap
    get() = AngularSnap(magnitude, metric)

/**
 * Creates an [AngularPop] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val angularPop = 1.radianPerSecondSixth // 1 rad/s⁶
 * ```
 */
val MetricUnitBuilder.radianPerSecondSixth: AngularPop
    get() = AngularPop(magnitude, metric)

/**
 * Creates an [AngularAcceleration] measure in radians per second squared.
 *
 * Example usage:
 * ```
 * val angularAcceleration = 1.radianPerSecondSquared
 * ```
 */
val MetricUnitBuilder.radianPerSecondSquared: AngularAcceleration
    get() = AngularAcceleration(magnitude, metric)

/**
 * Creates a [PlaneAngle] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val angle = 90.deci.radians // 90 * 10^-1 radians
 * ```
 */
val MetricUnitBuilder.radians: PlaneAngle get() = PlaneAngle(magnitude, metric)

/**
 * Creates a [MagneticReluctance] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val reluctance = 5.kilo.reciprocalHenries // 5 * 10^3 H⁻¹
 * ```
 */
val MetricUnitBuilder.reciprocalHenries: MagneticReluctance
    get() = MagneticReluctance(magnitude, metric)

/**
 * Creates a [ThermalExpansionCoefficient] measure in reciprocal kelvins.
 *
 * Example usage:
 * ```
 * val coefficient = 1.reciprocalKelvins
 * ```
 */
val MetricUnitBuilder.reciprocalKelvins: ThermalExpansionCoefficient
    get() = ThermalExpansionCoefficient(magnitude, metric)

/**
 * Creates a [Compressibility] measure in reciprocal pascals.
 *
 * Example usage:
 * ```
 * val compressibility = 1.reciprocalPascal
 * ```
 */
val MetricUnitBuilder.reciprocalPascal: Compressibility
    get() = Compressibility(magnitude, metric)

/**
 * Creates a [Time] measure in seconds.
 *
 * Example usage:
 * ```
 * val time = 5.seconds
 * ```
 */
val MetricUnitBuilder.seconds: Time
    get() = Time(magnitude, metric)

/**
 * Creates a [Conductance] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val cond = 1.milli.siemens // 1 * 10^-3 siemens
 * ```
 */
val MetricUnitBuilder.siemens: Conductance get() = Conductance(magnitude, metric)

/**
 * Creates an [ElectricConductivity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val conductivity = 1.siemensPerMetre // 1 S/m
 * ```
 */
val MetricUnitBuilder.siemensPerMetre: ElectricConductivity
    get() = ElectricConductivity(magnitude, metric)

/**
 * Creates a [MolarConductivity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val molarConductivity = 1.siemesSquareMetrePerMole // 1 S·m²/mol
 * ```
 */
val MetricUnitBuilder.siemensSquareMetrePerMole: MolarConductivity
    get() = MolarConductivity(magnitude, metric)

/**
 * Creates a [DoseEquivalent] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val doseEq = 500.micro.sieverts // 500 * 10^-6 sieverts
 * ```
 */
val MetricUnitBuilder.sieverts: DoseEquivalent get() = DoseEquivalent(magnitude, metric)

/**
 * Creates a [KinematicViscosity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val viscosity = 1.squareMetrePerSecond // 1 m²/s
 * ```
 */
val MetricUnitBuilder.squareMetrePerSecond: KinematicViscosity
    get() = KinematicViscosity(magnitude, metric)

/**
 * Creates an [ElectronMobility] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val mobility = 1.squareMetrePerVoltSecond // 1 m²/(V·s)
 * ```
 */
val MetricUnitBuilder.squareMetrePerVoltSecond: ElectronMobility
    get() = ElectronMobility(magnitude, metric)

/**
 * Creates an [Area] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val a = 5.centi.squareMetres // 5 * 10^-2 square metres
 * ```
 */
val MetricUnitBuilder.squareMetres: Area get() = Area(magnitude, metric)

/**
 * Creates a [SolidAngle] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val solidAngle = 5.milli.steradians // 5 * 10^-3 steradians
 * ```
 */
val MetricUnitBuilder.steradians: SolidAngle get() = SolidAngle(magnitude, metric)

/**
 * Creates a [MagneticRigidity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val rigidity = 1.teslaMetre // 1 T·m
 * ```
 */
val MetricUnitBuilder.teslaMetre: MagneticRigidity
    get() = MagneticRigidity(magnitude, metric)

/**
 * Creates a [MagneticFluxDensity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val field = 2.milli.teslas // 2 * 10^-3 teslas
 * ```
 */
val MetricUnitBuilder.teslas: MagneticFluxDensity get() = MagneticFluxDensity(magnitude, metric)

/**
 * Creates an [ElectricFieldStrength] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val field = 1.voltPerMetre // 1 V/m
 * ```
 */
val MetricUnitBuilder.voltPerMetre: ElectricFieldStrength
    get() = ElectricFieldStrength(magnitude, metric)

/**
 * Creates an [ElectricPotential] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val voltage = 12.kilo.volts // 12 * 10^3 volts
 * ```
 */
val MetricUnitBuilder.volts: ElectricPotential get() = ElectricPotential(magnitude, metric)

/**
 * Creates a [SpectralIrradiance] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val irradiance = 1.wattPerCubicMetre // 1 W/m³
 * ```
 */
val MetricUnitBuilder.wattPerCubicMetre: SpectralIrradiance
    get() = SpectralIrradiance(magnitude, metric)

/**
 * Creates a [SpectralPower] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val spectralPower = 1.wattPerMetre // 1 W/m
 * ```
 */
val MetricUnitBuilder.wattPerMetre: SpectralPower
    get() = SpectralPower(magnitude, metric)

/**
 * Creates a [ThermalConductivity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val thermalConductivity = 1.wattPerMetreKelvin // 1 W/(m·K)
 * ```
 */
val MetricUnitBuilder.wattPerMetreKelvin: ThermalConductivity
    get() = ThermalConductivity(magnitude, metric)

/**
 * Creates a [HeatFluxDensity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val heatFlux = 1.wattPerSquareMetre // 1 W/m²
 * ```
 */
val MetricUnitBuilder.wattPerSquareMetre: HeatFluxDensity
    get() = HeatFluxDensity(magnitude, metric)

/**
 * Creates a [RadiantIntensity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val radiantIntensity = 1.wattPerSteradian // 1 W/sr
 * ```
 */
val MetricUnitBuilder.wattPerSteradian: RadiantIntensity
    get() = RadiantIntensity(magnitude, metric)

/**
 * Creates a [SpectralRadiance] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val spectralRadiance = 1.wattPerSteradianCubicMetre // 1 W/(sr·m³)
 * ```
 */
val MetricUnitBuilder.wattPerSteradianCubicMetre: SpectralRadiance
    get() = SpectralRadiance(magnitude, metric)

/**
 * Creates a [Spectralntensity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val spectralIntensity = 1.wattPerSteradianMetre // 1 W/(sr·m)
 * ```
 */
val MetricUnitBuilder.wattPerSteradianMetre: Spectralntensity
    get() = Spectralntensity(magnitude, metric)

/**
 * Creates a [Radiance] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val radiance = 1.wattPerSteradianSquareMetre // 1 W/(sr·m²)
 * ```
 */
val MetricUnitBuilder.wattPerSteradianSquareMetre: Radiance
    get() = Radiance(magnitude, metric)

/**
 * Creates a [Power] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val power = 60.kilo.watts // 60 * 10^3 watts
 * ```
 */
val MetricUnitBuilder.watts: Power get() = Power(magnitude, metric)

/**
 * Creates a [MagneticMoment] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val magneticMoment = 1.weberMetre // 1 Wb·m
 * ```
 */
val MetricUnitBuilder.weberMetre: MagneticMoment
    get() = MagneticMoment(magnitude, metric)

/**
 * Creates a [MagneticVectorPotential] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val magneticVectorPotential = 1.weberPerMetre // 1 Wb/m
 * ```
 */
val MetricUnitBuilder.weberPerMetre: MagneticVectorPotential
    get() = MagneticVectorPotential(magnitude, metric)

/**
 * Creates a [MagneticFlux] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val flux = 3.micro.webers // 3 * 10^-6 webers
 * ```
 */
val MetricUnitBuilder.webers: MagneticFlux get() = MagneticFlux(magnitude, metric)
