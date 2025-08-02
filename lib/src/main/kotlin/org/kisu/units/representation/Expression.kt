package org.kisu.units.representation

import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System

/**
 * Represents a combination of a [Prefix] and a unit symbol, forming a complete unit expression
 * such as "km" (kilo + meter) or "μs" (micro + second).
 *
 * This class serves as the foundational abstraction for units with prefixes in a measurement system.
 * It inherits from both [Prefix] and [System], ensuring that the expression behaves consistently
 * within prefix and system operations.
 *
 * Equality is based on the unit symbol, meaning that two expressions are considered equal
 * if they share the same symbol, regardless of their internal structure.
 *
 * @param A the type of prefix used in this expression.
 */
sealed class Expression<A : Prefix<A>> : Prefix<A>, System<A> {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Expression<*>

        return symbol == other.symbol
    }

    override fun hashCode(): Int {
        return symbol.hashCode()
    }

    override fun toString(): String = symbol
}
