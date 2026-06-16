package org.kisu.prefixes.primitives

import org.kisu.prefixes.Binary
import org.kisu.prefixes.Metric
import org.kisu.prefixes.Metric.QUECTO
import org.kisu.prefixes.Metric.QUETTA
import org.kisu.prefixes.Prefix
import java.math.BigDecimal

/**
 * Represents a complete system of unit prefixes.
 *
 * A `System` groups all the prefixes belonging to a particular measurement system, such as the [Metric] system or the
 * [Binary] system.
 *
 * It provides access to the full collection of prefixes, as well as convenient references to the smallest and largest
 * prefixes within that system.
 *
 * For example:
 * - The [Metric] system includes prefixes like milli, centi, kilo, mega, etc.
 * - The [Binary] system includes prefixes like kibi, mebi, gibi, etc.
 *
 * @param P the enum type representing the prefixes in this system, which must implement [Prefix]
 */
interface System<P : Prefix<P>> {
    /**
     * The canonical unit for the system.
     *
     * It is the prefix representing the system's base coordinate, such as power `0` for exponential systems or
     * factor `1` for linear systems.
     *
     * ```kotlin
     * val metricSystem: System<Metric>
     *
     * metricSystem.canonical // METER
     * ```
     */
    val canonical: P

    /**
     * All prefixes defined in this system.
     *
     * To maintain an order, this list is sorted by each prefix type's natural ordering, smallest to largest.
     */
    val all: List<P>

    /**
     * The smallest prefix in this system (e.g., [QUECTO] in Metric).
     *
     * ```kotlin
     * val metricSystem: System<Metric>
     *
     * metricSystem.smallest // QUECTO
     * ```
     */
    val smallest: P

    /**
     * The largest prefix in this system (e.g., [QUETTA] in Metric).
     *
     * ```kotlin
     * val metricSystem: System<Metric>
     *
     * metricSystem.largest // QUETTA
     * ```
     */
    val largest: P

    /**
     * Finds the closest defined unit in the system that does not exceed the given coordinate.
     *
     * This function searches through all available units ([all]) and returns the last one
     * whose coordinate is less than or equal to the provided value. If no such unit exists, it returns the [smallest]
     * unit. If the given value is greater than all available units, it returns the [largest] unit.
     *
     * For exponential systems the coordinate is a power. For linear systems the coordinate is a concrete factor.
     *
     * @param coordinate The numerical coordinate to match against known units.
     * @return The closest unit not greater than the provided coordinate, or [smallest] if it's below the range,
     * or [largest] if it exceeds all defined units.
     */
    fun find(coordinate: BigDecimal): P
}
