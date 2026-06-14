package org.kisu.prefixes.primitives

import org.kisu.prefixes.Metric.QUECTO
import org.kisu.prefixes.Metric.QUETTA
import org.kisu.prefixes.Prefix
import java.math.BigDecimal
import kotlin.reflect.KClass

/**
 * Base [System] implementation for enum-backed prefix families.
 *
 * This class uses reflection on the provided enum class to retrieve all prefix values, sort them by their factor,
 * and expose common lookup operations. Concrete systems define which enum value is [canonical] because different
 * prefix algebra strategies use different base-prefix rules.
 *
 * @param klass The Kotlin class reference of the enum implementing [Prefix].
 */
abstract class EnumSystem<T : Prefix<T>>(klass: KClass<T>) : System<T> {
    /**
     * The base prefix in the system.
     *
     * Concrete systems must define this explicitly so missing canonical strategies fail at compile time rather than
     * at first access.
     */
    abstract override val canonical: T

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

    override fun find(factor: BigDecimal): T {
        return all.lastOrNull { it.factor <= factor }
            ?: all.first() // factor is smaller than the smallest known prefix
    }

    /**
     * Fails when a concrete system cannot find a canonical prefix matching [expected].
     */
    protected fun noCanonicalPrefixError(expected: String): Nothing =
        error("${this::class.simpleName} is a system with no $expected canonical prefix")
}
