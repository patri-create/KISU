package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.RandomSource
import io.kotest.property.arbitrary.of
import org.kisu.prefixes.Metric
import org.kisu.prefixes.primitives.StandardSystem

object Metric {
    val system: Arb<Metric> = Arb.of(StandardSystem(Metric::class).all)

    val sample: Metric
        get() = system.sample(RandomSource.default()).value
}
