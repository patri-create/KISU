package org.kisu.prefixes.primitives

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.element
import io.kotest.property.checkAll
import org.kisu.prefixes.Binary
import org.kisu.prefixes.Metric
import org.kisu.test.fakes.InvalidPrefix
import org.kisu.zero

class ExponentialEnumSystemTest : StringSpec({
    val systems = Arb.element<ExponentialEnumSystem<*>>(
        ExponentialEnumSystem(Metric::class),
        ExponentialEnumSystem(Binary::class),
    )

    "uses the zero-factor prefix as canonical" {
        checkAll(systems) { system ->
            system.canonical shouldBe system.all.first { prefix -> prefix.factor.zero }
        }
    }

    "crashes if there is no zero-factor prefix" {
        shouldThrow<IllegalStateException> {
            ExponentialEnumSystem(InvalidPrefix::class).canonical
        }
    }
})
