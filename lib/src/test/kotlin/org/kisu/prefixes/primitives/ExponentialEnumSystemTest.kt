package org.kisu.prefixes.primitives

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.element
import io.kotest.property.checkAll
import org.kisu.prefixes.Binary
import org.kisu.prefixes.ExponentialPrefix
import org.kisu.prefixes.Metric

class ExponentialEnumSystemTest : StringSpec({
    val systems = Arb.element<ExponentialEnumSystem<*>>(
        ExponentialEnumSystem(Metric::class),
        ExponentialEnumSystem(Binary::class, 2),
    )

    "uses the zero-power prefix as canonical" {
        checkAll(systems) { system ->
            system.canonical shouldBe system.all.first { prefix -> prefix.power == 0 }
        }
    }

    "crashes if there is no zero-power prefix" {
        shouldThrow<IllegalStateException> {
            ExponentialEnumSystem(InvalidExponentialPrefix::class).canonical
        }
    }
})

@Suppress("DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
private enum class InvalidExponentialPrefix(
    override val power: Int,
    symbol: String,
) : ExponentialPrefix<InvalidExponentialPrefix>,
    System<InvalidExponentialPrefix> by ExponentialEnumSystem(InvalidExponentialPrefix::class),
    Symbol by Representation(symbol) {
    ERROR(3, ""),
}
