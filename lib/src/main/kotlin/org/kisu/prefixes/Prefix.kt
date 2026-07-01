package org.kisu.prefixes

import org.kisu.prefixes.primitives.Symbol
import org.kisu.prefixes.primitives.System

/**
 * A `Prefix` is a symbolic scale coordinate attached to a unit of measurement.
 *
 * The common contract intentionally does not expose a concrete multiplier. Different prefix families encode their
 * coordinate differently: [ExponentialPrefix] stores an exponent coordinate such as `3` for kilo, while [LinearPrefix]
 * stores a direct multiplier such as `60` for minute. Unit expressions and prefix algebras resolve those coordinates
 * into concrete scale factors when a value is converted or normalized.
 *
 * Implementations typically come from enums such as [Metric], [Binary], [Decimal], [Time], or other domain-specific
 * systems.
 */
interface Prefix<Self : Prefix<Self>> : Symbol, Comparable<Self> {

    /**
     * Returns this prefix and [other], sorted according to this prefix type's ordering.
     *
     * Concrete prefix contracts define the ordering coordinate. Linear prefixes order by their concrete multiplier,
     * while exponential prefixes order by their power.
     *
     * @param other the prefix to compare
     * @return a [Pair] of prefixes sorted from smallest to largest
     */
    @Suppress("UNCHECKED_CAST")
    infix fun sortWith(other: Self): Pair<Self, Self> =
        listOf(this as Self, other)
            .sorted()
            .let { (left, right) -> left to right }
}

/**
 * Returns `true` if this [Prefix] is the **canonical** or base unit prefix for its system.
 *
 * In most systems, this corresponds to the base coordinate for the system, such as power `0` for exponential prefixes
 * or factor `1` for linear prefixes. It compares the current prefix with the system-defined
 * [System.canonical][org.kisu.prefixes.primitives.System.canonical] prefix.
 *
 * @return `true` if this prefix is the canonical base unit, `false` otherwise.
 */
val <P> P.isCanonical: Boolean where P : Prefix<P>, P : System<P>
    get() = this == canonical
