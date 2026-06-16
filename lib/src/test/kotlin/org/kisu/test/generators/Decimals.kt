package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.of
import org.kisu.prefixes.Decimal
import org.kisu.prefixes.primitives.ExponentialEnumSystem

object Decimals : Generator<Decimal> {
    override val generator: Arb<Decimal> = Arb.of(ExponentialEnumSystem(Decimal::class).all)
}
