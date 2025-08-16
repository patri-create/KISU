package org.kisu.units.builders

import org.kisu.bigDecimal
import org.kisu.prefixes.Binary
import org.kisu.units.base.Amount
import org.kisu.units.base.Current
import org.kisu.units.base.Information
import org.kisu.units.base.Length
import org.kisu.units.base.LuminousIntensity
import org.kisu.units.base.Mass
import org.kisu.units.base.Temperature
import org.kisu.units.base.Time
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

/**
 * Creates a [Current] from this [Number] representing an electric current in amperes (A),
 * the SI unit for electric current.
 */
val Number.amperes get() = Current(bigDecimal)

/**
 * Creates a [Radioactivity] from this [Number] representing a radioactivity in becquerels (Bq),
 * the SI unit for activity referred to a radionuclide.
 */
val Number.becquerels get() = Radioactivity(bigDecimal)

/**
 * Creates an [Information] value from this [Number] representing a quantity of bits.
 * This assumes 1 bit = base unit of information.
 */
val Number.bits get() = Information(bigDecimal, Binary.BASE)

/**
 * Creates a [Bytes] from this [Number] representing digital information in bytes (B),
 * where one byte equals 8 bits.
 */
val Number.bytes get() = Bytes(bigDecimal)

/**
 * Creates a [LuminousIntensity] from this [Number] representing luminous intensity in candelas (cd),
 * the SI unit for luminous intensity.
 */
val Number.candelas get() = LuminousIntensity(bigDecimal)

/**
 * Creates a [CelsiusTemperature] from this [Number] representing a temperature in degrees Celsius (°C),
 * which corresponds to Kelvin with an offset of 273.15.
 */
val Number.celsius get() = CelsiusTemperature(bigDecimal)

/**
 * Creates an [ElectricCharge] from this [Number] representing an electric charge in coulombs (C),
 * the SI unit for electric charge.
 */
val Number.coulombs get() = ElectricCharge(bigDecimal)

/**
 * Creates a [Volume] from this [Number] representing a volume in cubic metres (m³),
 * the SI derived unit for volume.
 */
val Number.cubicMeters get() = Volume(bigDecimal)

/**
 * Creates a [Capacitance] from this [Number] representing a capacitance in farads (F),
 * the SI unit for capacitance.
 */
val Number.farads get() = Capacitance(bigDecimal)

/**
 * Creates a [Mass] from this [Number] representing a mass in grams (g).
 * Note: This assumes the base unit is the gram, not the kilogram.
 */
val Number.grams get() = Mass(bigDecimal)

/**
 * Creates an [AbsorbedDose] from this [Number] representing an absorbed dose in grays (Gy),
 * the SI unit for absorbed dose and kerma.
 */
val Number.grays get() = AbsorbedDose(bigDecimal)

/**
 * Creates an [Energy] from this [Number] representing an amount of energy in joules (J),
 * the SI unit for energy, work, or heat.
 */
val Number.joules get() = Energy(bigDecimal)

/**
 * Creates an [Inductance] from this [Number] representing an inductance in henries (H),
 * the SI unit for inductance.
 */
val Number.henries get() = Inductance(bigDecimal)

/**
 * Creates a [Frequency] from this [Number] representing a frequency in hertz (Hz),
 * the SI unit for frequency.
 */
val Number.hertz get() = Frequency(bigDecimal)

/**
 * Creates a [CatalyticActivity] from this [Number] representing a catalytic activity in katals (kat),
 * the SI unit for catalytic activity.
 */
val Number.katals get() = CatalyticActivity(bigDecimal)

/**
 * Creates a [Temperature] from this [Number] representing a temperature in kelvins (K),
 * the SI unit for thermodynamic temperature.
 */
val Number.kelvins get() = Temperature(bigDecimal)

/**
 * Creates a [LuminousFlux] from this [Number] representing a luminous flux in lumens (lm),
 * the SI unit for luminous flux.
 */
val Number.lumens get() = LuminousFlux(bigDecimal)

/**
 * Creates an [Illuminance] from this [Number] representing an illuminance in lux (lx),
 * the SI unit for illuminance.
 */
val Number.lux get() = Illuminance(bigDecimal)

/**
 * Creates a [Length] from this [Number] representing a distance in meters (m),
 * the SI unit for length.
 */
val Number.meters get() = Length(bigDecimal)

/**
 * Creates an [Amount] from this [Number] representing a number of moles (mol),
 * the SI unit for amount of substance.
 */
val Number.moles get() = Amount(bigDecimal)

/**
 * Creates a [Force] from this [Number] representing a force in newtons (N),
 * the SI unit for force.
 */
val Number.newtons get() = Force(bigDecimal)

/**
 * Creates a [Resistance] from this [Number] representing an electrical resistance in ohms (Ω),
 * the SI unit for electrical resistance.
 */
val Number.ohms get() = Resistance(bigDecimal)

/**
 * Creates a [Pressure] from this [Number] representing a pressure in pascals (Pa),
 * the SI unit for pressure and stress.
 */
val Number.pascals get() = Pressure(bigDecimal)

/**
 * Creates a [PlaneAngle] from this [Number] representing an angle in radians (rad),
 * the SI unit for plane angle.
 */
val Number.radians get() = PlaneAngle(bigDecimal)

/**
 * Creates a [Time] from this [Number] representing a duration in seconds (s),
 * the SI unit for time.
 */
val Number.seconds get() = Time(bigDecimal)

/**
 * Creates a [Conductance] from this [Number] representing an electrical conductance in siemens (S),
 * the SI unit for conductance.
 */
val Number.siemens get() = Conductance(bigDecimal)

/**
 * Creates a [DoseEquivalent] from this [Number] representing a dose equivalent in sieverts (Sv),
 * the SI unit for dose equivalent.
 */
val Number.sieverts get() = DoseEquivalent(bigDecimal)

/**
 * Creates an [Area] from this [Number] representing an area in square metres (m²),
 * the SI derived unit for area.
 */
val Number.squareMeters get() = Area(bigDecimal)

/**
 * Creates a [SolidAngle] from this [Number] representing a solid angle in steradians (sr),
 * the SI unit for solid angle.
 */
val Number.steradians get() = SolidAngle(bigDecimal)

/**
 * Creates a [MagneticFluxDensity] from this [Number] representing a magnetic flux density in teslas (T),
 * the SI unit for magnetic flux density.
 */
val Number.teslas get() = MagneticFluxDensity(bigDecimal)

/**
 * Creates an [ElectricPotential] from this [Number] representing an electric potential in volts (V),
 * the SI unit for electric potential difference.
 */
val Number.volts get() = ElectricPotential(bigDecimal)

/**
 * Creates a [Power] from this [Number] representing a power in watts (W),
 * the SI unit for power and radiant flux.
 */
val Number.watts get() = Power(bigDecimal)

/**
 * Creates a [MagneticFlux] from this [Number] representing a magnetic flux in webers (Wb),
 * the SI unit for magnetic flux.
 */
val Number.webers get() = MagneticFlux(bigDecimal)

/** Creates a builder with the 'quecto' (10⁻³⁰) metric prefix. */
val Number.quecto get() = QuectoBuilder(bigDecimal)

/** Creates a builder with the 'ronto' (10⁻²⁷) metric prefix. */
val Number.ronto get() = RontoBuilder(bigDecimal)

/** Creates a builder with the 'yocto' (10⁻²⁴) metric prefix. */
val Number.yocto get() = YoctoBuilder(bigDecimal)

/** Creates a builder with the 'zepto' (10⁻²¹) metric prefix. */
val Number.zepto get() = ZeptoBuilder(bigDecimal)

/** Creates a builder with the 'atto' (10⁻¹⁸) metric prefix. */
val Number.atto get() = AttoBuilder(bigDecimal)

/** Creates a builder with the 'femto' (10⁻¹⁵) metric prefix. */
val Number.femto get() = FemtoBuilder(bigDecimal)

/** Creates a builder with the 'pico' (10⁻¹²) metric prefix. */
val Number.pico get() = PicoBuilder(bigDecimal)

/** Creates a builder with the 'nano' (10⁻⁹) metric prefix. */
val Number.nano get() = NanoBuilder(bigDecimal)

/** Creates a builder with the 'micro' (10⁻⁶) metric prefix. */
val Number.micro get() = MicroBuilder(bigDecimal)

/** Creates a builder with the 'milli' (10⁻³) metric prefix. */
val Number.milli get() = MilliBuilder(bigDecimal)

/** Creates a builder with the 'centi' (10⁻²) metric prefix. */
val Number.centi get() = CentiBuilder(bigDecimal)

/** Creates a builder with the 'deci' (10⁻¹) metric prefix. */
val Number.deci get() = DeciBuilder(bigDecimal)

/** Creates a builder with the 'deca' (10¹) metric prefix. */
val Number.deca get() = DecaBuilder(bigDecimal)

/** Creates a builder with the 'hecto' (10²) metric prefix. */
val Number.hecto get() = HectoBuilder(bigDecimal)

/** Creates a builder with the 'kilo' (10³) metric prefix. */
val Number.kilo get() = KiloBuilder(bigDecimal)

/** Creates a builder with the 'mega' (10⁶) metric prefix. */
val Number.mega get() = MegaBuilder(bigDecimal)

/** Creates a builder with the 'giga' (10⁹) metric prefix. */
val Number.giga get() = GigaBuilder(bigDecimal)

/** Creates a builder with the 'tera' (10¹²) metric prefix. */
val Number.tera get() = TeraBuilder(bigDecimal)

/** Creates a builder with the 'peta' (10¹⁵) metric prefix. */
val Number.peta get() = PetaBuilder(bigDecimal)

/** Creates a builder with the 'exa' (10¹⁸) metric prefix. */
val Number.exa get() = ExaBuilder(bigDecimal)

/** Creates a builder with the 'zetta' (10²¹) metric prefix. */
val Number.zetta get() = ZettaBuilder(bigDecimal)

/** Creates a builder with the 'yotta' (10²⁴) metric prefix. */
val Number.yotta get() = YottaBuilder(bigDecimal)

/** Creates a builder with the 'ronna' (10²⁷) metric prefix. */
val Number.ronna get() = RonnaBuilder(bigDecimal)

/** Creates a builder with the 'quetta' (10³⁰) metric prefix. */
val Number.quetta get() = QuettaBuilder(bigDecimal)

/** Creates a builder with the 'kibi' (2¹⁰) binary prefix. */
val Number.kibi get() = KibiBuilder(bigDecimal)

/** Creates a builder with the 'mebi' (2²⁰) binary prefix. */
val Number.mebi get() = MebiBuilder(bigDecimal)

/** Creates a builder with the 'gibi' (2³⁰) binary prefix. */
val Number.gibi get() = GibiBuilder(bigDecimal)

/** Creates a builder with the 'tebi' (2⁴⁰) binary prefix. */
val Number.tebi get() = TebiBuilder(bigDecimal)

/** Creates a builder with the 'pebi' (2⁵⁰) binary prefix. */
val Number.pebi get() = PebiBuilder(bigDecimal)

/** Creates a builder with the 'exbi' (2⁶⁰) binary prefix. */
val Number.exbi get() = ExbiBuilder(bigDecimal)

/** Creates a builder with the 'zebi' (2⁷⁰) binary prefix. */
val Number.zebi get() = ZebiBuilder(bigDecimal)

/** Creates a builder with the 'yobi' (2⁸⁰) binary prefix. */
val Number.yobi get() = YobiBuilder(bigDecimal)

/** Creates a builder with the 'robi' (2⁹⁰) binary prefix. */
val Number.robi get() = RobiBuilder(bigDecimal)

/** Creates a builder with the 'quebi' (2¹⁰⁰) binary prefix. */
val Number.quebi get() = QuebiBuilder(bigDecimal)
