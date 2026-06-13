package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.element
import org.kisu.prefixes.Binary
import org.kisu.prefixes.Decimal
import org.kisu.prefixes.Metric
import org.kisu.prefixes.Time
import org.kisu.prefixes.primitives.EnumSystem
import org.kisu.prefixes.primitives.ExponentialEnumSystem
import org.kisu.prefixes.primitives.LinealEnumSystem

object Systems : Generator<EnumSystem<*>> {
    private val systems: List<EnumSystem<*>> = listOf(
        ExponentialEnumSystem(Metric::class),
        ExponentialEnumSystem(Binary::class, 2),
        LinealEnumSystem(Decimal::class),
        LinealEnumSystem(Time::class),
    )

    override val generator: Arb<EnumSystem<*>> =
        Arb.element(systems)
}
