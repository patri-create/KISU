package org.kisu.units.scales

import org.kisu.KisuConfig
import org.kisu.integer
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import java.lang.Math.pow
import java.math.BigDecimal

/**
 * Scale strategy for prefix systems whose factors act as exponents over an expression-specific base.
 *
 * For example, an area expression can use a larger base than a length expression while preserving the same prefix
 * ordering.
 */
class ExponentialScale<A>(
    private val base: BigDecimal = BigDecimal.TEN
) : Scale<A> where A : Prefix<A>, A : System<A> {

    constructor(base: Int) : this(BigDecimal(base))

    override fun factor(prefix: A): BigDecimal {
        require(prefix.factor.integer) {
            "Exponential scale requires integer prefix factors. Factor is ${prefix.factor}"
        }

        val power = base.pow(prefix.factor.abs().toInt())

        return if (prefix.factor < BigDecimal.ZERO) {
            BigDecimal.ONE.divide(power, KisuConfig.precision)
        } else {
            power
        }
    }
}
