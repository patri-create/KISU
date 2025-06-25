package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.of
import org.kisu.prefixes.Binary
import org.kisu.prefixes.Decimal
import org.kisu.prefixes.Metric
import org.kisu.prefixes.primitives.StandardSystem

object Systems : Generator<StandardSystem<*>> {
    override val generator: Arb<StandardSystem<*>> = Arb.of(
        StandardSystem(Metric::class),
        StandardSystem(Binary::class),
        StandardSystem(Decimal::class)
    )
}
