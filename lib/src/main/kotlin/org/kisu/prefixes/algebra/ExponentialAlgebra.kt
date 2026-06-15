package org.kisu.prefixes.algebra

import org.kisu.KisuConfig
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

/**
 * Algebra for prefix systems whose factors act as exponents over an expression-specific base.
 *
 * For example, an area expression can use a larger base than a length expression while preserving the same prefix
 * ordering.
 */
class ExponentialAlgebra<P>(
    private val base: BigDecimal = BigDecimal.TEN
) : Algebra<P> where P : Prefix<P>, P : System<P> {

    init {
        require(base > BigDecimal.ZERO) {
            "Exponential algebra base must be greater than zero. Base is $base"
        }
    }

    constructor(base: Int) : this(BigDecimal(base))

    override fun factor(prefix: P): BigDecimal = remainder(prefix.factor)

    override fun multiply(left: P, right: P): Pair<P, BigDecimal> =
        resolve(left, left.factor + right.factor)

    override fun divide(left: P, right: P): Pair<P, BigDecimal> =
        resolve(left, left.factor - right.factor)

    private fun resolve(system: P, factor: BigDecimal): Pair<P, BigDecimal> {
        val prefix = system.all.lastOrNull { prefix -> prefix.factor <= factor } ?: system.smallest
        return prefix to remainder(factor - prefix.factor)
    }

    private fun remainder(exponent: BigDecimal): BigDecimal {
        val factor = base.pow(exponent.abs().intValueExact())
        return if (exponent < BigDecimal.ZERO) {
            BigDecimal.ONE.divide(factor, KisuConfig.precision)
        } else {
            factor
        }
    }
}
