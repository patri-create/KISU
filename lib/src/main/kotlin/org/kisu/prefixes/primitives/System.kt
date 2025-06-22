package org.kisu.prefixes.primitives

import org.kisu.prefixes.Binary
import org.kisu.prefixes.Metric
import org.kisu.prefixes.Metric.QUECTO
import org.kisu.prefixes.Metric.QUETTA
import org.kisu.prefixes.Prefix

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
 * @param T the enum type representing the prefixes in this system, which must implement [Prefix]
 */
interface System<T : Prefix> {
    /**
     * The canonical unit for the system.
     *
     * It is a prefix whose power is 0, and to which all the prefixes in the system refer to.
     *
     * ```kotlin
     * val metricSystem: System<Metric>
     *
     * metricSystem.canonical // METER
     * ```
     */
    val canonical: T

    /**
     * All prefixes defined in this system.
     *
     * To maintain an order, this list is sorted by power, smallest to largest.
     */
    val all: List<T>

    /**
     * The smallest prefix in this system (e.g., [QUECTO] in Metric).
     *
     * ```kotlin
     * val metricSystem: System<Metric>
     *
     * metricSystem.smallest // QUECTO
     * ```
     */
    val smallest: T

    /**
     * The largest prefix in this system (e.g., [QUETTA] in Metric).
     *
     * ```kotlin
     * val metricSystem: System<Metric>
     *
     * metricSystem.smallest // QUETTA
     * ```
     */
    val largest: T
}
