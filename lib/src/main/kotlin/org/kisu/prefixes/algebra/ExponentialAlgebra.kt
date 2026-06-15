package org.kisu.prefixes.algebra

import org.kisu.KisuConfig
import org.kisu.prefixes.ExponentialPrefix
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal
import kotlin.math.absoluteValue

/**
 * Algebra for prefix systems whose powers resolve to concrete factors through an expression-specific base.
 *
 * For example, metric kilo stores power `3`, and this algebra resolves it as `10^3` for a linear expression or another
 * configured base for derived expressions.
 */
class ExponentialAlgebra<P>(
    private val base: BigDecimal = BigDecimal.TEN
) : Algebra<P> where P : ExponentialPrefix<P>, P : System<P> {

    init {
        require(base > BigDecimal.ZERO) {
            "Exponential algebra base must be greater than zero. Base is $base"
        }
    }

    constructor(base: Int) : this(BigDecimal(base))

    override fun factor(prefix: P): BigDecimal = remainder(prefix.power)

    override fun multiply(left: P, right: P): Pair<P, BigDecimal> =
        resolve(left, left.power + right.power)

    override fun divide(left: P, right: P): Pair<P, BigDecimal> =
        resolve(left, left.power - right.power)

    private fun resolve(system: P, power: Int): Pair<P, BigDecimal> {
        val prefix = system.all.lastOrNull { prefix -> prefix.power <= power } ?: system.smallest
        return prefix to remainder(power - prefix.power)
    }

    private fun remainder(power: Int): BigDecimal {
        val factor = base.pow(power.absoluteValue)
        return if (power < 0) {
            BigDecimal.ONE.divide(factor, KisuConfig.precision)
        } else {
            factor
        }
    }
}
