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
    /**
     * The set of scalar components that make up this expression.
     *
     * This property represents the irreducible parts of the expression, where each [Scalar]
     * is a base quantity (e.g., length, mass, time) raised to a power. These factors are
     * used to describe the dimensional structure of the expression and are useful for
     * dimensional analysis, simplification, and operations such as multiplication and division.
     *
     * The set is guaranteed to be non-redundant (i.e., no duplicate or equivalent scalars),
     * and its contents define the meaning of the expression in terms of base units.
     */
    abstract val factors: Set<Scalar<*>>

    /**
     * Checks structural equality based on the [symbol] property.
     *
     * Two [Expression] instances are considered equal if they are of the same concrete class
     * and share the same [symbol]. This assumes that [symbol] uniquely identifies the meaning
     * and structure of the expression.
     *
     * @param other The object to compare with this instance.
     * @return `true` if the other object is an [Expression] of the same class with an equal [symbol], `false`
     * otherwise.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Expression<*>

        return symbol == other.symbol
    }

    /**
     * Returns a hash code derived from the [symbol].
     *
     * Ensures consistency with [equals], so that equal expressions produce the same hash code.
     */
    override fun hashCode(): Int {
        return symbol.hashCode()
    }

    /**
     * Returns a string representation of this expression.
     *
     * The returned value is the [symbol], which is assumed to concisely describe
     * the expression in symbolic form (e.g., "kg·m/s²").
     */
    override fun toString(): String = symbol
}
