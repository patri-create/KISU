package org.kisu.units.scales

import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

/**
 * Resolves the effective factor for a prefix when applied to a measurement expression.
 *
 * Some systems use the prefix factor directly, while others derive the factor from an expression-specific base.
 */
fun interface Scale<A> where A : Prefix<A>, A : System<A> {
    /**
     * Calculates the factor represented by [prefix] for the given expression [base].
     */
    fun factor(base: BigDecimal, prefix: A): BigDecimal
}
