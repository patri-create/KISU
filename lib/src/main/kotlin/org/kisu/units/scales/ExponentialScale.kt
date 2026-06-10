package org.kisu.units.scales

import org.kisu.KisuConfig
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal
import java.math.BigInteger

/**
 * Scale strategy for prefix systems whose factors act as exponents over an expression-specific base.
 *
 * For example, an area expression can use a larger base than a length expression while preserving the same prefix
 * ordering.
 */
class ExponentialScale<A> : Scale<A> where A : Prefix<A>, A : System<A> {
    override fun factor(base: BigDecimal, prefix: A): BigDecimal {
        val power = prefix.factor.decimalExponent
        val factor = base.pow(power.absolute)

        return if (power < 0) {
            BigDecimal.ONE.divide(factor, KisuConfig.precision)
        } else {
            factor
        }
    }

    private val BigDecimal.decimalExponent: Int
        get() {
            val normalized = stripTrailingZeros()
            require(normalized.unscaledValue() == BigInteger.ONE) {
                "Exponential scale requires power-of-ten prefix factors"
            }
            return -normalized.scale()
        }

    private val Int.absolute: Int
        get() = if (this < 0) -this else this
}
