package org.kisu.units.scales

import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

/**
 * Scale strategy for prefix systems whose factors are already expressed as absolute multipliers.
 *
 * This is useful for irregular systems such as time, where values like minutes and hours map directly to seconds.
 */
class LinearScale<A> : Scale<A> where A : Prefix<A>, A : System<A> {
    override fun factor(base: BigDecimal, prefix: A): BigDecimal = prefix.factor
}
