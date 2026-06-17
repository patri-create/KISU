package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.bigInt
import org.kisu.Magnitude
import org.kisu.prefixes.Binary
import org.kisu.prefixes.ExponentialPrefix
import org.kisu.prefixes.LinearPrefix
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import org.kisu.test.utils.zero
import java.math.BigInteger

object Magnitudes {
    val <T> System<T>.composition: Arb<List<Pair<BigInteger, T>>> where T : Prefix<T>
        get() =
            arbitrary {
                val bounds = all.zipWithNext().map { (current, next) ->
                    val maxExclusive = maxExclusive(current, next)
                    Arb.bigInt(0..<maxExclusive.toBigDecimal().toBigIntegerExact().intValueExact()).bind() to current
                }

                (bounds + (Arb.bigInt(0..1_000_000).bind() to largest))
                    .filter { (magnitude, _) -> !magnitude.zero }
            }

    private fun <T> System<T>.maxExclusive(
        current: T,
        next: T,
    ): Magnitude where T : Prefix<T> {
        return when (current) {
            is ExponentialPrefix<*> if next is ExponentialPrefix<*> ->
                exponentBase.pow(next.power - current.power)

            is LinearPrefix<*> if next is LinearPrefix<*> ->
                next.factor / current.factor

            else -> error("Unsupported prefix type: ${current::class.qualifiedName}")
        }
    }

    private val <T> System<T>.exponentBase: Magnitude where T : Prefix<T>
        get() =
            when (canonical) {
                is Binary -> Magnitude.valueOf(2)
                else -> Magnitude.TEN
            }
}
