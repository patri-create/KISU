package org.kisu.prefixes.algebra

import org.kisu.Magnitude
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System

/**
 * Resolves the effective factor for a prefix when applied to a measurement expression.
 *
 * Some systems use a direct prefix factor, while others derive the factor from a prefix power and an
 * expression-specific base.
 */
interface Algebra<P> where P : Prefix<P>, P : System<P> {
    /**
     * Calculates the factor represented by [prefix].
     */
    fun factor(prefix: P): Magnitude

    /**
     * Combines two prefixes as a multiplication.
     *
     * @return a pair whose first element is the resolved prefix and whose second element is the residual
     * remainder factor.
     */
    fun multiply(left: P, right: P): Pair<P, Magnitude>

    /**
     * Combines two prefixes as a division.
     *
     * @return a pair whose first element is the resolved prefix and whose second element is the residual
     * remainder factor.
     */
    fun divide(left: P, right: P): Pair<P, Magnitude>
}
