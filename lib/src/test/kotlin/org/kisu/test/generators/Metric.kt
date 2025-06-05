package org.kisu.test.generators

import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import org.kisu.prefixes.Metric
import org.kisu.prefixes.primitives.StandardSystem

object Metric {
    val system: Arbitrary<Metric> = Arbitraries.of(StandardSystem(Metric::class).all)

    fun sample(): Metric = system.sample()
}
