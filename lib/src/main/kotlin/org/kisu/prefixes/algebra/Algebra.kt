package org.kisu.prefixes.algebra

import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

/**
 * Resolves the effective factor for a prefix when applied to a measurement expression.
 *
 * Some systems use the prefix factor directly, while others derive the factor from an expression-specific base.
 */
interface Algebra<P> where P : Prefix<P>, P : System<P> {
    /**
     * Calculates the factor represented by [prefix].
     */
    fun factor(prefix: P): BigDecimal

    /**
     * Combines two prefixes as a multiplication.
     *
     * @return a pair whose first element is the resolved prefix and whose second element is the residual
     * remainder factor.
     */
    fun multiply(left: P, right: P): Pair<P, BigDecimal>

    /**
     * Combines two prefixes as a division.
     *
     * @return a pair whose first element is the resolved prefix and whose second element is the residual
     * remainder factor.
     */
    fun divide(left: P, right: P): Pair<P, BigDecimal>
}
