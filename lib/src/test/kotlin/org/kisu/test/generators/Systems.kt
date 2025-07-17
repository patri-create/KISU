package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.of
import org.kisu.prefixes.Binary
import org.kisu.prefixes.Decimal
import org.kisu.prefixes.Metric
import org.kisu.prefixes.primitives.EnumSystem

object Systems : Generator<EnumSystem<*>> {
    override val generator: Arb<EnumSystem<*>> =
        Arb.of(
            EnumSystem(Metric::class),
            EnumSystem(Binary::class),
            EnumSystem(Decimal::class),
        )
}
