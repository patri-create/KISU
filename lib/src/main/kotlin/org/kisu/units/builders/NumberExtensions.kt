package org.kisu.units.builders

import org.kisu.bigDecimal
import org.kisu.prefixes.Binary
import org.kisu.prefixes.Metric
import org.kisu.units.base.Amount
import org.kisu.units.base.Current
import org.kisu.units.base.Information
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
import org.kisu.units.mechanics.SpectralIntensity
import org.kisu.units.mechanics.SpectralIrradiance
import org.kisu.units.mechanics.SpectralPower
import org.kisu.units.mechanics.SpectralRadiance
import org.kisu.units.mechanics.SurfaceTension
import org.kisu.units.mechanics.WaveNumber
import org.kisu.units.photometric.Efficacy
import org.kisu.units.photometric.Luminance
import org.kisu.units.photometric.LuminousEnergy
import org.kisu.units.special.AbsorbedDose
import org.kisu.units.special.Area
import org.kisu.units.special.Bytes
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
import org.kisu.units.kinematics.angular.Acceleration as AngularAcceleration
import org.kisu.units.kinematics.angular.Crackle as AngularCrackle
import org.kisu.units.kinematics.angular.Jerk as AngularJerk
import org.kisu.units.kinematics.angular.Pop as AngularPop
import org.kisu.units.kinematics.angular.Snap as AngularSnap

/**
 * Creates a [Magnetization] from this [Number] representing a magnetization in ampere per metre,
 * the SI unit for magnetization.
 */
val Number.amperesPerMetre: Magnetization get() = Magnetization(bigDecimal)

/**
 * Creates a [ElectricCurrentDensity] from this [Number] representing an electric current density in ampere per square
 * metre, the SI unit for electric current density.
 */
val Number.amperesPerSquareMetre: ElectricCurrentDensity get() = ElectricCurrentDensity(bigDecimal)

/**
 * Creates a [MagnetomotiveForce] from this [Number] representing a magnetomotive force in ampere radian,
 * the SI unit for magnetomotive force.
 */
val Number.amperesRadian: MagnetomotiveForce get() = MagnetomotiveForce(bigDecimal)

/**
 * Creates a [Current] from this [Number] representing a current in amperes,
 * the SI unit for current.
 */
val Number.amperes: Current get() = Current(bigDecimal)

/**
 * Creates a [Radioactivity] from this [Number] representing a radioactivity in becquerels,
 * the SI unit for radioactivity.
 */
val Number.becquerels: Radioactivity get() = Radioactivity(bigDecimal)

/**
 * Creates an [Information] from this [Number] representing a bytes in bytes,
 * the SI unit for bytes.
 */
/**
 * Creates an [Information] value from this [Number] representing a quantity of bits.
 * This assumes 1 bit = base unit of information.
 */
val Number.bits get() = Information(bigDecimal, Binary.BASE)

/**
 * Creates a [Bytes] from this [Number] representing a bytes in bytes,
 * the SI unit for bytes.
 */
val Number.bytes: Bytes get() = Bytes(bigDecimal)

/**
 * Creates a [Luminance] from this [Number] representing a luminance in candela per square metre,
 * the SI unit for luminance.
 */
val Number.candelasPerSquareMetre: Luminance get() = Luminance(bigDecimal)

/**
 * Creates a [LuminousIntensity] from this [Number] representing a luminous intensity in candelas,
 * the SI unit for luminous intensity.
 */
val Number.candelas: LuminousIntensity get() = LuminousIntensity(bigDecimal)

/**
 * Creates a [CelsiusTemperature] from this [Number] representing a celsius temperature in celsius,
 * the SI unit for celsius temperature.
 */
val Number.celsius: CelsiusTemperature get() = CelsiusTemperature(bigDecimal)

/**
 * Creates a [ElectricChargeDensity] from this [Number] representing an electric charge density in coulomb per cubic
 * metre, the SI unit for electric charge density.
 */
val Number.coulombsPerCubicMetre: ElectricChargeDensity get() = ElectricChargeDensity(bigDecimal)

/**
 * Creates a [Exposure] from this [Number] representing an exposure in coulomb per kilogram,
 * the SI unit for exposure.
 */
val Number.coulombsPerKilogram: Exposure get() = Exposure(bigDecimal)

/**
 * Creates a [LinearChargeDensity] from this [Number] representing a linear charge density in coulomb per metre,
 * the SI unit for linear charge density.
 */
val Number.coulombsPerMetre: LinearChargeDensity get() = LinearChargeDensity(bigDecimal)

/**
 * Creates a [ElectricDisplacementField] from this [Number] representing an electric displacement field in coulomb per
 * square metre,the SI unit for electric displacement field.
 */
val Number.coulombsPerSquareMetre: ElectricDisplacementField get() = ElectricDisplacementField(bigDecimal)

/**
 * Creates a [ElectricCharge] from this [Number] representing an electric charge in coulombs,
 * the SI unit for electric charge.
 */
val Number.coulombs: ElectricCharge get() = ElectricCharge(bigDecimal)

/**
 * Creates a [SpecificVolume] from this [Number] representing a specific volume in cubic metre per kilogram,
 * the SI unit for specific volume.
 */
val Number.cubicMetresPerKilogram: SpecificVolume get() = SpecificVolume(bigDecimal)

/**
 * Creates a [MolarVolume] from this [Number] representing a molar volume in cubic metre per mole,
 * the SI unit for molar volume.
 */
val Number.cubicMetrePerMole: MolarVolume get() = MolarVolume(bigDecimal)

/**
 * Creates a [CatalyticEfficiency] from this [Number] representing a catalytic efficiency in cubic metre per mole
 * second, the SI unit for catalytic efficiency.
 */
val Number.cubicMetrePerMoleSecond: CatalyticEfficiency get() = CatalyticEfficiency(bigDecimal)

/**
 * Creates a [VolumetricFlow] from this [Number] representing a volumetric flow in cubic metre per second,
 * the SI unit for volumetric flow.
 */
val Number.cubicMetresPerSecond: VolumetricFlow get() = VolumetricFlow(bigDecimal)

/**
 * Creates a [Volume] from this [Number] representing a volume in cubic metres,
 * the SI unit for volume.
 */
val Number.cubicMetres: Volume get() = Volume(bigDecimal)

/**
 * Creates a [Permittivity] from this [Number] representing a permittivity in farad per metre,
 * the SI unit for permittivity.
 */
val Number.faradsPerMetre: Permittivity get() = Permittivity(bigDecimal)

/**
 * Creates a [Capacitance] from this [Number] representing a capacitance in farads,
 * the SI unit for capacitance.
 */
val Number.farads: Capacitance get() = Capacitance(bigDecimal)

/**
 * Creates a [Yank] from this [Number] representing a yank in gram metre,
 * the SI unit for yank.
 */
val Number.gramMetre: Yank get() = Yank(bigDecimal)

/**
 * Creates a [Yank] from this [Number] representing a yank in gram metre second third,
 * the SI unit for yank.
 */
val Number.gramsMetreSecondCubed: Yank get() = Yank(bigDecimal)

/**
 * Creates a [Density] from this [Number] representing a density in gram per cubic metre,
 * the SI unit for density.
 */
val Number.gramsPerCubicMetre: Density get() = Density(bigDecimal)

/**
 * Creates a [LinearMassDensity] from this [Number] representing a linear mass density in gram per metre,
 * the SI unit for linear mass density.
 */
val Number.gramsPerMetre: LinearMassDensity get() = LinearMassDensity(bigDecimal)

/**
 * Creates a [MolarMass] from this [Number] representing a molar mass in gram per mole,
 * the SI unit for molar mass.
 */
val Number.gramsPerMole: MolarMass get() = MolarMass(bigDecimal)

/**
 * Creates a [MassFlowRate] from this [Number] representing a mass flow rate in gram per second,
 * the SI unit for mass flow rate.
 */
val Number.gramsPerSecond: MassFlowRate get() = MassFlowRate(bigDecimal)

/**
 * Creates a [AreaDensity] from this [Number] representing an area density in gram per square metre,
 * the SI unit for area density.
 */
val Number.gramsPerSquareMetre: AreaDensity get() = AreaDensity(bigDecimal)

/**
 * Creates a [Mass] from this [Number] representing a mass in grams,
 * the SI unit for mass.
 */
val Number.grams: Mass get() = Mass(bigDecimal, Metric.BASE)

/**
 * Creates a [MomentOfIntertia] from this [Number] representing a moment of intertia in gram square metre,
 * the SI unit for moment of intertia.
 */
val Number.gramsSquareMetre: MomentOfIntertia get() = MomentOfIntertia(bigDecimal)

/**
 * Creates a [AbsorbedDoseRate] from this [Number] representing an absorbed dose rate in gray per second,
 * the SI unit for absorbed dose rate.
 */
val Number.graysPerSecond: AbsorbedDoseRate get() = AbsorbedDoseRate(bigDecimal)

/**
 * Creates a [AbsorbedDose] from this [Number] representing an absorbed dose in grays,
 * the SI unit for absorbed dose.
 */
val Number.grays: AbsorbedDose get() = AbsorbedDose(bigDecimal)

/**
 * Creates a [Inductance] from this [Number] representing an inductance in henries,
 * the SI unit for inductance.
 */
val Number.henries: Inductance get() = Inductance(bigDecimal)

/**
 * Creates a [MagneticPermittivity] from this [Number] representing a magnetic permittivity in henry per metre,
 * the SI unit for magnetic permittivity.
 */
val Number.henriesPerMetre: MagneticPermittivity get() = MagneticPermittivity(bigDecimal)

/**
 * Creates a [Frequency] from this [Number] representing a frequency in hertz,
 * the SI unit for frequency.
 */
val Number.hertz: Frequency get() = Frequency(bigDecimal)

/**
 * Creates a [FrequencyDrift] from this [Number] representing a frequency drift in hertz per second,
 * the SI unit for frequency drift.
 */
val Number.hertzPerSecond: FrequencyDrift get() = FrequencyDrift(bigDecimal)

/**
 * Creates a [EnergyDensity] from this [Number] representing an energy density in joule per cubic metre,
 * the SI unit for energy density.
 */
val Number.joulesPerCubicMetre: EnergyDensity get() = EnergyDensity(bigDecimal)

/**
 * Creates a [HeatCapacity] from this [Number] representing a heat capacity in joule per kelvin,
 * the SI unit for heat capacity.
 */
val Number.joulesPerKelvin: HeatCapacity get() = HeatCapacity(bigDecimal)

/**
 * Creates a [MolarHeatCapacity] from this [Number] representing a molar heat capacity in joule per kelvin mole,
 * the SI unit for molar heat capacity.
 */
val Number.joulePerKelvinMole: MolarHeatCapacity get() = MolarHeatCapacity(bigDecimal)

/**
 * Creates a [SpecificEnergy] from this [Number] representing a specific energy in joule per kilogram,
 * the SI unit for specific energy.
 */
val Number.joulesPerKilogram: SpecificEnergy get() = SpecificEnergy(bigDecimal)

/**
 * Creates a [SpecificHeatCapacity] from this [Number] representing a specific heat capacity in joule per kilogram
 * kelvin, the SI unit for specific heat capacity.
 */
val Number.joulesPerKilogramKelvin: SpecificHeatCapacity get() = SpecificHeatCapacity(bigDecimal)

/**
 * Creates a [MolarEnergy] from this [Number] representing a molar energy in joule per mole,
 * the SI unit for molar energy.
 */
val Number.joulePerMole: MolarEnergy get() = MolarEnergy(bigDecimal)

/**
 * Creates a [RadiantExposure] from this [Number] representing a radiant exposure in joule per square metre,
 * the SI unit for radiant exposure.
 */
val Number.joulesPerSquareMetre: RadiantExposure get() = RadiantExposure(bigDecimal)

/**
 * Creates a [EnergyFluxDensity] from this [Number] representing an energy flux density in joule per square metre
 * second,the SI unit for energy flux density.
 */
val Number.joulesPerSquareMetreSecond: EnergyFluxDensity get() = EnergyFluxDensity(bigDecimal)

/**
 * Creates a [MagneticDipoleMoment] from this [Number] representing a magnetic dipole moment in joule per tesla,
 * the SI unit for magnetic dipole moment.
 */
val Number.joulePerTesla: MagneticDipoleMoment get() = MagneticDipoleMoment(bigDecimal)

/**
 * Creates a [Energy] from this [Number] representing an energy in joules,
 * the SI unit for energy.
 */
val Number.joules: Energy get() = Energy(bigDecimal)

/**
 * Creates a [Action] from this [Number] representing an action in joule second,
 * the SI unit for action.
 */
val Number.joulesSecond: Action get() = Action(bigDecimal)

/**
 * Creates a [CatalyticActivity] from this [Number] representing a catalytic activity in katals,
 * the SI unit for catalytic activity.
 */
val Number.katals: CatalyticActivity get() = CatalyticActivity(bigDecimal)

/**
 * Creates a [TemperatureGradient] from this [Number] representing a temperature gradient in kelvin per metre,
 * the SI unit for temperature gradient.
 */
val Number.kelvinsPerMetre: TemperatureGradient get() = TemperatureGradient(bigDecimal)

/**
 * Creates a [ThermalResistance] from this [Number] representing a thermal resistance in kelvin per watt,
 * the SI unit for thermal resistance.
 */
val Number.kelvinsPerWatt: ThermalResistance get() = ThermalResistance(bigDecimal)

/**
 * Creates a [Temperature] from this [Number] representing a temperature in kelvins,
 * the SI unit for temperature.
 */
val Number.kelvins: Temperature get() = Temperature(bigDecimal)

/**
 * Creates a [Efficacy] from this [Number] representing an efficacy in lumen per watt,
 * the SI unit for efficacy.
 */
val Number.lumensPerWatt: Efficacy get() = Efficacy(bigDecimal)

/**
 * Creates a [LuminousFlux] from this [Number] representing a luminous flux in lumens,
 * the SI unit for luminous flux.
 */
val Number.lumens: LuminousFlux get() = LuminousFlux(bigDecimal)

/**
 * Creates a [LuminousEnergy] from this [Number] representing a luminous energy in lumen second,
 * the SI unit for luminous energy.
 */
val Number.lumensSecond: LuminousEnergy get() = LuminousEnergy(bigDecimal)

/**
 * Creates a [Illuminance] from this [Number] representing an illuminance in lux,
 * the SI unit for illuminance.
 */
val Number.lux: Illuminance get() = Illuminance(bigDecimal)

/**
 * Creates a [Exposure] from this [Number] representing an exposure in lux second,
 * the SI unit for exposure.
 */
val Number.luxSecond: org.kisu.units.photometric.Exposure get() = org.kisu.units.photometric.Exposure(bigDecimal)

/**
 * Creates a [FuelEfficiency] from this [Number] representing a fuel efficiency in metre per cubic metre,
 * the SI unit for fuel efficiency.
 */
val Number.metresPerCubicMetre: FuelEfficiency get() = FuelEfficiency(bigDecimal)

/**
 * Creates a [MagneticSusceptibility] from this [Number] representing a magnetic susceptibility in metre per henry,
 * the SI unit for magnetic susceptibility.
 */
val Number.metresPerHenry: MagneticSusceptibility get() = MagneticSusceptibility(bigDecimal)

/**
 * Creates a [Speed] from this [Number] representing a speed in metre per second,
 * the SI unit for speed.
 */
val Number.metresPerSecond: Speed get() = Speed(bigDecimal)

/**
 * Creates a [Jerk] from this [Number] representing a jerk in metre per second cubed,
 * the SI unit for jerk.
 */
val Number.metresPerSecondCubed: Jerk get() = Jerk(bigDecimal)

/**
 * Creates a [Crackle] from this [Number] representing a crackle in metre per second fifth,
 * the SI unit for crackle.
 */
val Number.metresPerSecondFifth: Crackle get() = Crackle(bigDecimal)

/**
 * Creates a [Snap] from this [Number] representing a snap in metre per second fourth,
 * the SI unit for snap.
 */
val Number.metresPerSecondFourth: Snap get() = Snap(bigDecimal)

/**
 * Creates a [Pop] from this [Number] representing a pop in metre per second sixth,
 * the SI unit for pop.
 */
val Number.metresPerSecondSixth: Pop get() = Pop(bigDecimal)

/**
 * Creates a [Acceleration] from this [Number] representing an acceleration in metre per second squared,
 * the SI unit for acceleration.
 */
val Number.metresPerSecondSquared: Acceleration get() = Acceleration(bigDecimal)

/**
 * Creates a [Length] from this [Number] representing a length in metres,
 * the SI unit for length.
 */
val Number.metres: Length get() = Length(bigDecimal)

/**
 * Creates a [Molarity] from this [Number] representing a molarity in mole per cubic metre,
 * the SI unit for molarity.
 */
val Number.molePerCubicMetre: Molarity get() = Molarity(bigDecimal)

/**
 * Creates a [Amount] from this [Number] representing an amount in moles,
 * the SI unit for amount.
 */
val Number.moles: Amount get() = Amount(bigDecimal)

/**
 * Creates a [Molality] from this [Number] representing a molality in mol per kilogram,
 * the SI unit for molality.
 */
val Number.molPerKilogram: Molality get() = Molality(bigDecimal)

/**
 * Creates a [AngularMomentum] from this [Number] representing an angular momentum in newton meter second,
 * the SI unit for angular momentum.
 */
val Number.newtonsMetreSecond: AngularMomentum get() = AngularMomentum(bigDecimal)

/**
 * Creates a [SpecificAngularMomentum] from this [Number] representing a specific angular momentum in newton metre
 * second per kilogram, the SI unit for specific angular momentum.
 */
val Number.newtonsMetreSecondPerKilogram: SpecificAngularMomentum get() = SpecificAngularMomentum(bigDecimal)

/**
 * Creates a [SurfaceTension] from this [Number] representing a surface tension in newton per metre,
 * the SI unit for surface tension.
 */
val Number.newtonsPerMetre: SurfaceTension get() = SurfaceTension(bigDecimal)

/**
 * Creates a [Force] from this [Number] representing a force in newtons,
 * the SI unit for force.
 */
val Number.newtons: Force get() = Force(bigDecimal)

/**
 * Creates a [Momentum] from this [Number] representing a momentum in newton second,
 * the SI unit for momentum.
 */
val Number.newtonsSecond: Momentum get() = Momentum(bigDecimal)

/**
 * Creates a [Resistivity] from this [Number] representing a resistivity in ohm metre,
 * the SI unit for resistivity.
 */
val Number.ohmsMetre: Resistivity get() = Resistivity(bigDecimal)

/**
 * Creates a [Resistance] from this [Number] representing a resistance in ohms,
 * the SI unit for resistance.
 */
val Number.ohms: Resistance get() = Resistance(bigDecimal)

/**
 * Creates a [Pressure] from this [Number] representing a pressure in pascals,
 * the SI unit for pressure.
 */
val Number.pascals: Pressure get() = Pressure(bigDecimal)

/**
 * Creates a [DynamicViscosity] from this [Number] representing a dynamic viscosity in pascal second,
 * the SI unit for dynamic viscosity.
 */
val Number.pascalsSecond: DynamicViscosity get() = DynamicViscosity(bigDecimal)

/**
 * Creates a [Velocity] from this [Number] representing a velocity in radian per second,
 * the SI unit for velocity.
 */
val Number.radiansPerSecond: Velocity get() = Velocity(bigDecimal)

/**
 * Creates a [AngularJerk] from this [Number] representing an angular jerk in radian per second cubed,
 * the SI unit for angular jerk.
 */
val Number.radiansPerSecondCubed: AngularJerk get() = AngularJerk(bigDecimal)

/**
 * Creates a [AngularCrackle] from this [Number] representing an angular crackle in radian per second fifth,
 * the SI unit for angular crackle.
 */
val Number.radiansPerSecondFifth: AngularCrackle get() = AngularCrackle(bigDecimal)

/**
 * Creates a [AngularSnap] from this [Number] representing an angular snap in radian per second fourth,
 * the SI unit for angular snap.
 */
val Number.radiansPerSecondFourth: AngularSnap get() = AngularSnap(bigDecimal)

/**
 * Creates a [AngularPop] from this [Number] representing an angular pop in radian per second sixth,
 * the SI unit for angular pop.
 */
val Number.radiansPerSecondSixth: AngularPop get() = AngularPop(bigDecimal)

/**
 * Creates a [AngularAcceleration] from this [Number] representing an angular acceleration in radian per second squared,
 * the SI unit for angular acceleration.
 */
val Number.radiansPerSecondSquared: AngularAcceleration get() = AngularAcceleration(bigDecimal)

/**
 * Creates a [PlaneAngle] from this [Number] representing a plane angle in radians,
 * the SI unit for plane angle.
 */
val Number.radians: PlaneAngle get() = PlaneAngle(bigDecimal)

/**
 * Creates a [MagneticReluctance] from this [Number] representing a magnetic reluctance in reciprocal henries,
 * the SI unit for magnetic reluctance.
 */
val Number.reciprocalHenries: MagneticReluctance get() = MagneticReluctance(bigDecimal)

/**
 * Creates a [ThermalExpansionCoefficient] from this [Number] representing a thermal expansion coefficient in
 * reciprocal kelvins, the SI unit for thermal expansion coefficient.
 */
val Number.reciprocalKelvins: ThermalExpansionCoefficient get() = ThermalExpansionCoefficient(bigDecimal)

/**
 * Creates a [WaveNumber] from this [Number] representing a compressibility in reciprocal metres,
 * the SI unit for compressibility.
 */
val Number.reciprocalMetres: WaveNumber get() = WaveNumber(bigDecimal)

/**
 * Creates a [Compressibility] from this [Number] representing a compressibility in reciprocal pascal,
 * the SI unit for compressibility.
 */
val Number.reciprocalPascals: Compressibility get() = Compressibility(bigDecimal)

/**
 * Creates a [Time] from this [Number] representing a time in seconds,
 * the SI unit for time.
 */
val Number.seconds: Time get() = Time(bigDecimal)

/**
 * Creates a [Conductance] from this [Number] representing a conductance in siemens,
 * the SI unit for conductance.
 */
val Number.siemens: Conductance get() = Conductance(bigDecimal)

/**
 * Creates a [ElectricConductivity] from this [Number] representing an electric conductivity in siemens per metre,
 * the SI unit for electric conductivity.
 */
val Number.siemensPerMetre: ElectricConductivity get() = ElectricConductivity(bigDecimal)

/**
 * Creates a [MolarConductivity] from this [Number] representing a molar conductivity in siemens square metre per mole,
 * the SI unit for molar conductivity.
 */
val Number.siemensSquareMetrePerMole: MolarConductivity get() = MolarConductivity(bigDecimal)

/**
 * Creates a [DoseEquivalent] from this [Number] representing a dose equivalent in sieverts,
 * the SI unit for dose equivalent.
 */
val Number.sieverts: DoseEquivalent get() = DoseEquivalent(bigDecimal)

/**
 * Creates a [KinematicViscosity] from this [Number] representing a kinematic viscosity in square metre per second,
 * the SI unit for kinematic viscosity.
 */
val Number.squareMetresPerSecond: KinematicViscosity get() = KinematicViscosity(bigDecimal)

/**
 * Creates a [ElectronMobility] from this [Number] representing an electron mobility in square metre per volt second,
 * the SI unit for electron mobility.
 */
val Number.squareMetresPerVoltSecond: ElectronMobility get() = ElectronMobility(bigDecimal)

/**
 * Creates a [Area] from this [Number] representing an area in square metres,
 * the SI unit for area.
 */
val Number.squareMetres: Area get() = Area(bigDecimal)

/**
 * Creates a [SolidAngle] from this [Number] representing a solid angle in steradians,
 * the SI unit for solid angle.
 */
val Number.steradians: SolidAngle get() = SolidAngle(bigDecimal)

/**
 * Creates a [MagneticRigidity] from this [Number] representing a magnetic rigidity in tesla metre,
 * the SI unit for magnetic rigidity.
 */
val Number.teslasMetre: MagneticRigidity get() = MagneticRigidity(bigDecimal)

/**
 * Creates a [MagneticFluxDensity] from this [Number] representing a magnetic flux density in teslas,
 * the SI unit for magnetic flux density.
 */
val Number.teslas: MagneticFluxDensity get() = MagneticFluxDensity(bigDecimal)

/**
 * Creates a [ElectricFieldStrength] from this [Number] representing an electric field strength in volt per metre,
 * the SI unit for electric field strength.
 */
val Number.voltsPerMetre: ElectricFieldStrength get() = ElectricFieldStrength(bigDecimal)

/**
 * Creates a [ElectricPotential] from this [Number] representing an electric potential in volts,
 * the SI unit for electric potential.
 */
val Number.volts: ElectricPotential get() = ElectricPotential(bigDecimal)

/**
 * Creates a [SpectralIrradiance] from this [Number] representing a spectral irradiance in watt per cubic metre,
 * the SI unit for spectral irradiance.
 */
val Number.wattsPerCubicMetre: SpectralIrradiance get() = SpectralIrradiance(bigDecimal)

/**
 * Creates a [SpectralPower] from this [Number] representing a spectral power in watt per metre,
 * the SI unit for spectral power.
 */
val Number.wattsPerMetre: SpectralPower get() = SpectralPower(bigDecimal)

/**
 * Creates a [ThermalConductivity] from this [Number] representing a thermal conductivity in watt per metre kelvin,
 * the SI unit for thermal conductivity.
 */
val Number.wattsPerMetreKelvin: ThermalConductivity get() = ThermalConductivity(bigDecimal)

/**
 * Creates a [HeatFluxDensity] from this [Number] representing a heat flux density in watt per square metre,
 * the SI unit for heat flux density.
 */
val Number.wattsPerSquareMetre: HeatFluxDensity get() = HeatFluxDensity(bigDecimal)

/**
 * Creates a [RadiantIntensity] from this [Number] representing a radiant intensity in watt per steradian,
 * the SI unit for radiant intensity.
 */
val Number.wattsPerSteradian: RadiantIntensity get() = RadiantIntensity(bigDecimal)

/**
 * Creates a [SpectralRadiance] from this [Number] representing a spectral radiance in watt per steradian cubic metre,
 * the SI unit for spectral radiance.
 */
val Number.wattsPerSteradianCubicMetre: SpectralRadiance get() = SpectralRadiance(bigDecimal)

/**
 * Creates a [SpectralIntensity] from this [Number] representing a spectralntensity in watt per steradian metre,
 * the SI unit for spectralntensity.
 */
val Number.wattPerSteradianMetre: SpectralIntensity get() = SpectralIntensity(bigDecimal)

/**
 * Creates a [Radiance] from this [Number] representing a radiance in watt per steradian square metre,
 * the SI unit for radiance.
 */
val Number.wattsPerSteradianSquareMetre: Radiance get() = Radiance(bigDecimal)

/**
 * Creates a [Power] from this [Number] representing a power in watts,
 * the SI unit for power.
 */
val Number.watts: Power get() = Power(bigDecimal)

/**
 * Creates a [MagneticMoment] from this [Number] representing a magnetic moment in weber metre,
 * the SI unit for magnetic moment.
 */
val Number.webersMetre: MagneticMoment get() = MagneticMoment(bigDecimal)

/**
 * Creates a [MagneticVectorPotential] from this [Number] representing a magnetic vector potential in weber per metre,
 * the SI unit for magnetic vector potential.
 */
val Number.webersPerMetre: MagneticVectorPotential get() = MagneticVectorPotential(bigDecimal)

/**
 * Creates a [MagneticFlux] from this [Number] representing a magnetic flux in webers,
 * the SI unit for magnetic flux.
 */
val Number.webers: MagneticFlux get() = MagneticFlux(bigDecimal)
