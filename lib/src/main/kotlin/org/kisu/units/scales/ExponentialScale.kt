package org.kisu.units.scales

import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

class ExponentialScale<A> : Scale<A> where A : Prefix<A>, A : System<A> {
    override fun factor(base: BigDecimal, prefix: A): BigDecimal =
        base.pow(prefix.factor.toInt())
}