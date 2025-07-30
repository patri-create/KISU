package org.kisu.units.builders

import org.kisu.prefixes.Metric
import org.kisu.units.base.Amount
import org.kisu.units.base.Current
import org.kisu.units.base.Length
import org.kisu.units.base.LuminousIntensity
import org.kisu.units.base.Mass
import org.kisu.units.base.Temperature
import org.kisu.units.base.Time
import org.kisu.units.special.AbsorbedDose
import org.kisu.units.special.Area
import org.kisu.units.special.Capacitance
import org.kisu.units.special.CatalyticActivity
import org.kisu.units.special.Celsius
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
import java.math.BigDecimal

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
val MetricUnitBuilder.bytes: org.kisu.units.special.Byte get() = org.kisu.units.special.Byte(magnitude, metric)

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
 * Creates a [Celsius] measure with the given magnitude in degrees Celsius.
 *
 * Example usage:
 * ```
 * val temp = 37.degreesCelsius // 37 °C
 * ```
 */
val MetricUnitBuilder.celsius: Celsius get() = Celsius(magnitude, metric)

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
 * Creates a [Volume] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val v = 2.kilo.cubicMeters // 2 * 10^3 cubic metres
 * ```
 */
val MetricUnitBuilder.cubicMeters: Volume get() = Volume(magnitude, metric)

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
 * Creates an [AbsorbedDose] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val dose = 2.milli.grays // 2 * 10^-3 grays
 * ```
 */
val MetricUnitBuilder.grays: AbsorbedDose get() = AbsorbedDose(magnitude, metric)

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
 * Creates an [Inductance] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val induct = 5.micro.henries // 5 * 10^-6 henries
 * ```
 */
val MetricUnitBuilder.henries: Inductance get() = Inductance(magnitude, metric)

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
 * Creates a [CatalyticActivity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val activity = 10.milli.katals // 10 * 10^-3 katals
 * ```
 */
val MetricUnitBuilder.katals: CatalyticActivity get() = CatalyticActivity(magnitude, metric)

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
 * Creates a [LuminousFlux] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val luminousFlux = 800.lumens // 800 lumens (no prefix applied)
 * ```
 */
val MetricUnitBuilder.lumens: LuminousFlux get() = LuminousFlux(magnitude, metric)

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
 * Creates a [Length] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val length = 25.kilo.meters // 25 * 10^3 meters
 * ```
 */
val MetricUnitBuilder.meters: Length get() = Length(magnitude, metric)

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
 * Creates a [Force] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val force = 10.kilo.newtons // 10 * 10^3 newtons
 * ```
 */
val MetricUnitBuilder.newtons: Force get() = Force(magnitude, metric)

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
 * Creates a [PlaneAngle] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val angle = 90.deci.radians // 90 * 10^-1 radians
 * ```
 */
val MetricUnitBuilder.radians: PlaneAngle get() = PlaneAngle(magnitude, metric)

/**
 * Creates a [Time] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val duration = 4.centi.seconds // 4 * 10^-2 seconds
 * ```
 */
val MetricUnitBuilder.seconds: Time get() = Time(magnitude, metric)

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
 * Creates a [DoseEquivalent] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val doseEq = 500.micro.sieverts // 500 * 10^-6 sieverts
 * ```
 */
val MetricUnitBuilder.sieverts: DoseEquivalent get() = DoseEquivalent(magnitude, metric)

/**
 * Creates an [Area] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val a = 5.centi.squareMeters // 5 * 10^-2 square metres
 * ```
 */
val MetricUnitBuilder.squareMeters: Area get() = Area(magnitude, metric)

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
 * Creates a [MagneticFluxDensity] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val field = 2.milli.teslas // 2 * 10^-3 teslas
 * ```
 */
val MetricUnitBuilder.teslas: MagneticFluxDensity get() = MagneticFluxDensity(magnitude, metric)

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
 * Creates a [Power] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val power = 60.kilo.watts // 60 * 10^3 watts
 * ```
 */
val MetricUnitBuilder.watts: Power get() = Power(magnitude, metric)

/**
 * Creates a [MagneticFlux] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val flux = 3.micro.webers // 3 * 10^-6 webers
 * ```
 */
val MetricUnitBuilder.webers: MagneticFlux get() = MagneticFlux(magnitude, metric)
