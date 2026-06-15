package org.kisu.prefixes.primitives

import org.kisu.orElse
import org.kisu.prefixes.ExponentialPrefix
import java.math.BigDecimal
import kotlin.reflect.KClass

/**
 * Enum-backed [System] for prefixes whose [ExponentialPrefix.power] stores an exponent.
 *
 * Lookup compares requested exponent coordinates against declared prefix powers. The canonical prefix is the enum value
 * with power `0`.
 */
class ExponentialEnumSystem<T : ExponentialPrefix<T>> : EnumSystem<T> {
    constructor(klass: KClass<T>) : this(klass, BigDecimal.TEN)

    constructor(klass: KClass<T>, base: Int) : this(klass, BigDecimal.valueOf(base.toLong()))

    @Suppress("UNUSED_PARAMETER")
    constructor(klass: KClass<T>, base: BigDecimal) : super(klass)

    /**
     * The zero-power prefix for this exponent-based system.
     */
    override val canonical: T by lazy {
        all.find { prefix -> prefix.power == 0 }
            ?: noCanonicalPrefixError("zero-power")
    }

    override fun find(factor: BigDecimal): T = all.lastOrNull { it.power.toBigDecimal() <= factor }.orElse { smallest }
}
