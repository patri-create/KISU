package org.kisu.prefixes.algebra

import org.kisu.KisuConfig
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

/**
 * Algebra for prefix systems whose factors are already expressed as absolute multipliers.
 *
 * This is useful for irregular systems such as time, where values like minutes and hours map directly to seconds.
 */
class LinearAlgebra<P> : Algebra<P> where P : Prefix<P>, P : System<P> {

    override fun factor(prefix: P): BigDecimal = prefix.factor

    override fun multiply(left: P, right: P): Pair<P, BigDecimal> {
        val factor = left.factor.multiply(right.factor)
        val prefix = left.find(factor)
        return prefix to factor.divide(prefix.factor, KisuConfig.precision)
    }

    override fun divide(left: P, right: P): Pair<P, BigDecimal> {
        val factor = left.factor.divide(right.factor, KisuConfig.precision)
        val prefix = left.find(factor)
        return prefix to factor.divide(prefix.factor, KisuConfig.precision)
    }
}
