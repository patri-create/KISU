package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.bigInt
import org.kisu.prefixes.Binary
import org.kisu.prefixes.ExponentialPrefix
import org.kisu.prefixes.LinearPrefix
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import org.kisu.zero
import java.math.BigDecimal
import java.math.BigInteger

object Magnitudes {
    val <T> System<T>.composition: Arb<List<Pair<BigInteger, T>>> where T : Prefix<T>
        get() =
            arbitrary {
                val bounds = all.zipWithNext().map { (current, next) ->
                    val maxExclusive = maxExclusive(current, next)
                    Arb.bigInt(0..<maxExclusive.intValueExact()).bind() to current
                }

                (bounds + (Arb.bigInt(0..1_000_000).bind() to largest))
                    .filter { (magnitude, _) -> !magnitude.zero }
            }

    private fun <T> System<T>.maxExclusive(
        current: T,
        next: T,
    ): BigDecimal where T : Prefix<T> {
        return when {
            current is ExponentialPrefix<*> && next is ExponentialPrefix<*> ->
                exponentBase.pow(next.power - current.power)
            current is LinearPrefix<*> && next is LinearPrefix<*> ->
                next.factor.divide(current.factor)
            else -> error("Unsupported prefix type: ${current::class.qualifiedName}")
        }
    }

    private val <T> System<T>.exponentBase: BigDecimal where T : Prefix<T>
        get() =
            when (canonical) {
                is Binary -> BigDecimal.valueOf(2)
                else -> BigDecimal.TEN
            }
}
