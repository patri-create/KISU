package org.kisu.test.generators

import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import org.kisu.prefixes.Binary
import org.kisu.prefixes.Decimal
import org.kisu.prefixes.Metric
import org.kisu.prefixes.primitives.StandardSystem

object System {
    val systems: Arbitrary<StandardSystem<*>> = Arbitraries.of(
        StandardSystem(Metric::class),
        StandardSystem(Binary::class),
        StandardSystem(Decimal::class),
    )

    fun sample() = systems.sample()
}
