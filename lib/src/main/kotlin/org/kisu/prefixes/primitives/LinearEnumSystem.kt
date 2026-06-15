package org.kisu.prefixes.primitives

import org.kisu.one
import org.kisu.orElse
import org.kisu.prefixes.LinearPrefix
import java.math.BigDecimal
import kotlin.reflect.KClass

/**
 * Enum-backed [System] for prefixes whose [LinearPrefix.factor] is the concrete multiplicative factor.
 *
 * Prefix multiplication and division multiply or divide factors directly, select the closest declared prefix, and
 * return any residual value as a multiplicative remainder. The canonical prefix is the enum value with factor `1`.
 */
class LinearEnumSystem<T : LinearPrefix<T>>(klass: KClass<T>) : EnumSystem<T>(klass) {
    /**
     * The unit-factor prefix for this linear system.
     */
    override val canonical: T by lazy {
        all.find { prefix -> prefix.factor.one }
            ?: noCanonicalPrefixError("unit-factor")
    }

    override fun find(factor: BigDecimal): T =
        all.lastOrNull { it.factor <= factor }.orElse { smallest }
}
