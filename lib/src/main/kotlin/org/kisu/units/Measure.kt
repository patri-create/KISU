package org.kisu.units

import org.kisu.orElse
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import org.kisu.zero
import java.math.BigDecimal

/**
 * Represents a physical quantity composed of a [magnitude], a [prefix], and a [unit].
 *
 * This generic class is designed to work with unit prefixes (such as SI prefixes like kilo, milli),
 * supporting automatic rescaling to optimal representations based on magnitude.
 *
 * @param T The type of prefix, which must be one of the ones speecified in [org.kisu.prefixes].
 * @property magnitude The numerical value of the measurement.
 * @property prefix The prefix applied to the unit (e.g., `kilo`, `milli`).
 * @property unit The name of the unit (e.g., `"m"`, `"g"`).
 */
class Measure<T>(
    private val magnitude: BigDecimal,
    private val prefix: T,
    private val unit: String,
) where T : System<T>, T : Prefix {
    constructor(magnitude: Double, prefix: T, unit: String) : this(BigDecimal.valueOf(magnitude), prefix, unit)

    /**
     * Returns the most human-readable form of the measurement by automatically choosing the
     * most appropriate [prefix] such that the magnitude is near or above 1.
     *
     * If the magnitude is zero, this falls back to the [canonical] representation.
     * If the current [prefix] is already the smallest and the magnitude is less than 1,
     * or if it is the largest and the magnitude is greater than 1, the [representation] form is returned.
     *
     * Otherwise, it selects the largest rescaled prefix such that the resulting magnitude is ≥ 1.
     *
     * Does not trim the floating point in any form, to avoid precision loss.
     *
     * Example: `"1500m" -> "1.5 km"`
     *
     * ```
     * val distance = Measure(1500.0, BASE, "m")
     * distance.optimal // "1.5km"
     * ```
     */
    val optimal: Measure<T> by lazy {
        when {
            magnitude.zero -> canonical
            prefix == prefix.smallest && magnitude.abs() < BigDecimal.ONE -> this
            prefix == prefix.largest && magnitude.abs() > BigDecimal.ONE -> this
            else ->
                prefix.all
                    .asSequence()
                    .map(this::to)
                    .lastOrNull { measure -> measure.magnitude.abs() >= BigDecimal.ONE }
                    .orElse { to(prefix.largest) }
        }
    }

    /**
     * Returns the canonical form of the measurement by converting it to the base prefix.
     *
     * Example `"1.5 km" -> "1500 m"`
     *
     * ```
     * val distance = Measure(1.5, KILO, "m")
     * distance.canonical // "1500m"
     * ```
     */
    val canonical: Measure<T> by lazy {
        if (!magnitude.zero) {
            to(prefix.canonical)
        } else {
            Measure(magnitude, prefix.canonical, unit)
        }
    }

    /**
     * Returns the raw string representation of the measurement without any rescaling.
     *
     * If magnitude is 0, it returns the value in the canonical unit, even if the prefix stated is different.
     *
     * ```
     * val distance = Measure(1.5, KILO, "m")
     * distance.representation // "1.5 km"
     * ```
     *
     * ```
     * val distance = Measure(0, KILO, "m")
     * distance.representation // "0 m"
     * ```
     */
    val representation: String by lazy {
        if (magnitude.zero) {
            "0 ${prefix.canonical}$unit"
        } else {
            "$magnitude $prefix$unit"
        }
    }

    /**
     * Rescales the current measurement to a different [prefix], adjusting the [magnitude] accordingly.
     *
     * ```
     * val distance = Measure(1500.0, METER, "m")
     * val inKilometers = distance.to(KILO)
     * println(inKilometers.literal) // "1.5 km"
     * ```
     *
     * @param other The target prefix to convert to.
     * @return A new [Measure] instance using the new [prefix] and the converted [magnitude].
     */
    fun to(other: T): Measure<T> {
        val conversion = prefix.scale(other)
        return Measure(magnitude * conversion, other, unit)
    }

    /**
     * Returns the [optimal] string representation of this measurement for inspection purposes.
     *
     * ```
     * val distance = Measure(1500.0, BASE, "m")
     * distance.toString() // "1.5km"
     * ```
     */
    override fun toString(): String = optimal.representation

    /**
     * Compares this [Measure] with another object for equality.
     *
     * Two [Measure] instances are considered equal if:
     * - They are the same instance, or
     * - They have the same runtime class,
     * - Their [unit] is equal,
     * - Their canonical [magnitude] values are equal.
     *
     * The [prefix] is intentionally ignored in the comparison,
     * as equality is determined by the canonical (normalized) value.
     *
     * ```
     * val distanceInKm = Measure(1.0, KILO, "m")
     * val distanceInM = Measure(1000.0, BASE, "m")
     *
     * distanceInKm == distanceInM // true
     * ```
     *
     * ```
     * val temperature = Measure(200.0, BASE, "º")
     * val weight = Measure(10.0, KILO, "g")
     *
     * temperature == weight // false
     * ```
     *
     * @param other The object to compare with.
     * @return `true` if the objects are considered equal, `false` otherwise.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Measure<*>

        if (unit != other.unit) return false

        val left = canonical
        val right = other.canonical
        if (left.magnitude.compareTo(right.magnitude) != 0) return false

        return true
    }

    /**
     * Returns a hash code value for the [Measure] object.
     *
     * The hash code is based on the canonical [magnitude] and [unit] only,
     * since [prefix] is ignored in the [equals] comparison as all units are compared to their canonical unit.
     *
     * @return The hash code value.
     */
    override fun hashCode(): Int {
        val canonical = canonical
        var result = canonical.magnitude.hashCode()
        result = 31 * result + canonical.unit.hashCode()
        return result
    }
}
