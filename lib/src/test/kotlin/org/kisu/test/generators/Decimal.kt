package org.kisu.test.generators

import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import org.kisu.prefixes.Decimal
import org.kisu.prefixes.primitives.StandardSystem

object Decimal {
    val system: Arbitrary<Decimal> = Arbitraries.of(StandardSystem(Decimal::class).all)

    fun sample(): Decimal = system.sample()
}
