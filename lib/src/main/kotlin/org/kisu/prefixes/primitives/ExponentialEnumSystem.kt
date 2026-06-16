package org.kisu.prefixes.primitives

import org.kisu.Magnitude
import org.kisu.orElse
import org.kisu.prefixes.ExponentialPrefix
import kotlin.reflect.KClass

/**
 * Enum-backed [System] for prefixes whose [ExponentialPrefix.power] stores an exponent.
 *
 * Lookup compares requested exponent coordinates against declared prefix powers. The canonical prefix is the enum value
 * with power `0`.
 */
class ExponentialEnumSystem<T : ExponentialPrefix<T>> : EnumSystem<T> {
    constructor(klass: KClass<T>) : super(klass)

    @Deprecated(
        message = "The base is algebra-specific; ExponentialEnumSystem now stores and finds prefixes by power only.",
        replaceWith = ReplaceWith("ExponentialEnumSystem(klass)")
    )
    @Suppress("UNUSED_PARAMETER")
    constructor(klass: KClass<T>, base: Int) : this(klass)

    @Deprecated(
        message = "The base is algebra-specific; ExponentialEnumSystem now stores and finds prefixes by power only.",
        replaceWith = ReplaceWith("ExponentialEnumSystem(klass)")
    )
    @Suppress("UNUSED_PARAMETER")
    constructor(klass: KClass<T>, base: Magnitude) : this(klass)

    /**
     * The zero-power prefix for this exponent-based system.
     */
    override val canonical: T by lazy {
        all.find { prefix -> prefix.power == 0 }
            ?: noCanonicalPrefixError("zero-power")
    }

    override fun find(coordinate: Magnitude): T =
        all.lastOrNull { Magnitude(it.power) <= coordinate }.orElse { smallest }
}
