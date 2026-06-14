package org.kisu.prefixes.primitives

import org.kisu.KisuConfig
import org.kisu.one
import org.kisu.prefixes.Prefix
import java.math.BigDecimal
import kotlin.reflect.KClass

/**
 * Enum-backed [System] for prefixes whose [Prefix.factor] is the concrete multiplicative factor.
 *
 * Prefix multiplication and division multiply or divide factors directly, select the closest declared prefix, and
 * return any residual value as a multiplicative remainder. The canonical prefix is the enum value with factor `1`.
 */
class LinealEnumSystem<T : Prefix<T>>(klass: KClass<T>) : EnumSystem<T>(klass) {
    /**
     * The unit-factor prefix for this linear system.
     */
    override val canonical: T by lazy {
        all.find { prefix -> prefix.factor.one }
            ?: noCanonicalPrefixError("unit-factor")
    }

    /**
     * Returns the factor needed to convert a value expressed with this prefix into one expressed in [other].
     *
     * For example:
     * ```kotlin
     * KILO.to(MILLI) // → 1_000_000
     * MILLI.to(KILO) // → 0.000001
     * ```
     *
     * This satisfies:
     * ```kotlin
     * magnitudeInThis * this.to(other) == magnitudeInOther
     * ```
     *
     * @param left the source prefix
     * @param right the target prefix
     * @return the conversion factor from [left] to [right]
     */
    fun convert(left: T, right: T): BigDecimal = left.factor.divide(right.factor, KisuConfig.precision)

    override fun multiply(left: T, right: T): Pair<T, BigDecimal> {
        val factor = left.factor * right.factor
        val prefix = find(factor)
        return prefix to factor.divide(prefix.factor, KisuConfig.precision)
    }

    override fun divide(left: T, right: T): Pair<T, BigDecimal> {
        val factor = left.factor.divide(right.factor, KisuConfig.precision)
        val prefix = find(factor)
        return prefix to factor.divide(prefix.factor, KisuConfig.precision)
    }
}
