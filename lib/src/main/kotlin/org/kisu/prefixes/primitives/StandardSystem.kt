package org.kisu.prefixes.primitives

import org.kisu.prefixes.Metric.QUECTO
import org.kisu.prefixes.Metric.QUETTA
import org.kisu.prefixes.Prefix
import kotlin.reflect.KClass

/**
 * A standard implementation of [System] that works with an enum class representing prefixes.
 *
 * This class uses reflection on the provided enum class to:
 * - Retrieve all prefix values,
 * - Sort them by their power from smallest to largest,
 * - Identify the base unit (prefix with power 0),
 * - Provide easy access to the smallest and largest prefixes.
 *
 * This implementation assumes the enum constants implement [Prefix] and that exactly one prefix has a power of zero
 * (the base unit).
 *
 * If no such base prefix is found, it throws an [IllegalStateException].
 *
 * @param klass The Kotlin class reference of the enum implementing [Prefix].
 */
class StandardSystem<T : Prefix>(klass: KClass<T>) : System<T> {
    /**
     * The base prefix in the system, identified by power == 0.
     *
     * @throws [IllegalStateException] if no base prefix is found.
     */
    override val canonical: T by lazy {
        all.find { prefix -> prefix.power == 0 }
            ?: error("${this::class.simpleName} is a system with no base")
    }

    /**
     * All prefixes defined in this system.
     *
     * To maintain an order, this list is sorted by power, smallest to largest.
     */
    override val all: List<T> by lazy { klass.java.enumConstants?.toList().orEmpty().sorted() }

    /**
     * The smallest prefix in this system (e.g., [QUECTO] in Metric).
     *
     * ```kotlin
     * val metricSystem: System<Metric>
     *
     * metricSystem.smallest // QUECTO
     * ```
     */
    override val smallest: T by lazy { all.first() }

    /**
     * The largest prefix in this system (e.g., [QUETTA] in Metric).
     *
     * ```kotlin
     * val metricSystem: System<Metric>
     *
     * metricSystem.smallest // QUETTA
     * ```
     */
    override val largest: T by lazy { all.last() }
}
