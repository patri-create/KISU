package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.of
import org.kisu.prefixes.Metric
import org.kisu.prefixes.primitives.EnumSystem

object Metrics : Generator<Metric> {
    override val generator: Arb<Metric> = Arb.of(EnumSystem(Metric::class).all)
}
