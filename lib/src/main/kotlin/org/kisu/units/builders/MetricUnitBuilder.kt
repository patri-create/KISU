package org.kisu.units.builders

import org.kisu.prefixes.Metric
import org.kisu.units.base.Amount
import org.kisu.units.base.Current
import org.kisu.units.base.Length
import org.kisu.units.base.LuminousIntensity
import org.kisu.units.base.Mass
import org.kisu.units.base.Temperature
import org.kisu.units.base.Time
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
 * Creates a [Current] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val current = 1.micro.amperes // 1 * 10^-6 amperes
 * ```
 */
val MetricUnitBuilder.amperes: Current get() = Current(magnitude, metric)

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
 * Creates a [Temperature] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val temp = 2.kilo.kelvins // 2 * 10^3 kelvins
 * ```
 */
val MetricUnitBuilder.kelvins: Temperature get() = Temperature(magnitude, metric)

/**
 * Creates a [Time] measure by applying the metric prefix scale to the magnitude.
 *
 * Example usage:
 * ```
 * val duration = 4.centi.seconds // 4 * 10^-2 seconds
 * ```
 */
val MetricUnitBuilder.seconds: Time get() = Time(magnitude, metric)
