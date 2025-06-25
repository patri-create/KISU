package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.RandomSource
import io.kotest.property.arbitrary.of
import org.kisu.prefixes.Decimal
import org.kisu.prefixes.primitives.StandardSystem

object Decimal {
    val system: Arb<Decimal> = Arb.of(StandardSystem(Decimal::class).all)

    val sample: Decimal
        get() = system.sample(RandomSource.default()).value
}
