package org.kisu.prefixes.primitives

import org.kisu.Magnitude
import org.kisu.orElse
import org.kisu.prefixes.LinearPrefix
import kotlin.reflect.KClass

/**
 * Enum-backed [System] for prefixes whose [LinearPrefix.factor] is the concrete multiplicative factor.
 *
 * Lookup compares requested concrete factors against declared prefix factors. The canonical prefix is the enum value
 * with factor `1`.
 */
class LinearEnumSystem<T : LinearPrefix<T>>(klass: KClass<T>) : EnumSystem<T>(klass) {
    /**
     * The unit-factor prefix for this linear system.
     */
    override val canonical: T by lazy {
        all.find { prefix -> prefix.factor.one }
            ?: noCanonicalPrefixError("unit-factor")
    }

    override fun find(coordinate: Magnitude): T =
        all.lastOrNull { it.factor <= coordinate }.orElse { smallest }
}
