package org.kisu.prefixes.algebra

import org.kisu.Magnitude
import org.kisu.prefixes.LinearPrefix
import org.kisu.prefixes.primitives.System

/**
 * Algebra for prefix systems whose factors are already expressed as absolute multipliers.
 *
 * This is useful for irregular systems such as time, where values like minutes and hours map directly to seconds.
 */
class LinearAlgebra<P> : Algebra<P> where P : LinearPrefix<P>, P : System<P> {

    override fun factor(prefix: P): Magnitude = prefix.factor

    override fun multiply(left: P, right: P): Pair<P, Magnitude> {
        val factor = left.factor * right.factor
        val prefix = left.find(factor)
        return prefix to factor / prefix.factor
    }

    override fun divide(left: P, right: P): Pair<P, Magnitude> {
        val factor = left.factor / right.factor
        val prefix = left.find(factor)
        return prefix to factor / prefix.factor
    }
}
