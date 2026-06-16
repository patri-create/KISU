package org.kisu.prefixes.algebra

import org.kisu.Magnitude
import org.kisu.prefixes.ExponentialPrefix
import org.kisu.prefixes.primitives.System
import kotlin.math.abs

private const val MAX_BIG_DECIMAL_POWER = 999_999_999

/**
 * Algebra for prefix systems whose powers resolve to concrete factors through an expression-specific base.
 *
 * For example, metric kilo stores power `3`, and this algebra resolves it as `10^3` for a linear expression or another
 * configured base for derived expressions.
 */
class ExponentialAlgebra<P>(
    private val base: Magnitude = Magnitude.TEN
) : Algebra<P> where P : ExponentialPrefix<P>, P : System<P> {

    init {
        require(base > Magnitude.ZERO) {
            "Exponential algebra base must be greater than zero. Base is $base"
        }
    }

    constructor(base: Int) : this(Magnitude(base))

    override fun factor(prefix: P): Magnitude = remainder(prefix.power)

    override fun multiply(left: P, right: P): Pair<P, Magnitude> =
        resolve(left, Math.addExact(left.power, right.power))

    override fun divide(left: P, right: P): Pair<P, Magnitude> =
        resolve(left, Math.subtractExact(left.power, right.power))

    private fun resolve(system: P, power: Int): Pair<P, Magnitude> {
        val prefix = system.all.lastOrNull { prefix -> prefix.power <= power } ?: system.smallest
        return prefix to remainder(Math.subtractExact(power, prefix.power))
    }

    private fun remainder(power: Int): Magnitude {
        val magnitude = abs(power.toLong())
        require(magnitude <= MAX_BIG_DECIMAL_POWER) {
            "Exponential power magnitude is too large: $power"
        }
        return base.pow(power)
    }
}
