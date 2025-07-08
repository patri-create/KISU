package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.bigInt
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import org.kisu.zero
import java.math.BigInteger

object Magnitudes {
    val <T> System<T>.composition: Arb<List<Pair<BigInteger, T>>> where T : Prefix<T>
        get() =
            arbitrary {
                val bounds = all.zipWithNext().map { (current, next) ->
                    val maxExclusive = next.factor.divide(current.factor)
                    Arb.bigInt(0..maxExclusive.intValueExact() - 1).bind() to current
                }

                (bounds + (Arb.bigInt(0..1_000_000).bind() to largest))
                    .filter { (magnitude, _) -> !magnitude.zero }
            }
}
