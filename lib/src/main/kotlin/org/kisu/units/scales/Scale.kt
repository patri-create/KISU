package org.kisu.units.scales

import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

fun interface Scale<A> where A : Prefix<A>, A : System<A> {
    fun factor(base: BigDecimal, prefix: A): BigDecimal
}