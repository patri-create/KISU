package org.kisu.prefixes.primitives

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.element
import io.kotest.property.checkAll
import org.kisu.one
import org.kisu.prefixes.Time
import org.kisu.test.fakes.InvalidPrefix

class LinearEnumSystemTest : StringSpec({
    val systems = Arb.element<LinearEnumSystem<*>>(
        LinearEnumSystem(Time::class),
    )

    "uses the unit-factor prefix as canonical" {
        checkAll(systems) { system ->
            system.canonical shouldBe system.all.first { prefix -> prefix.factor.one }
        }
    }

    "crashes if there is no unit-factor prefix" {
        shouldThrow<IllegalStateException> {
            LinearEnumSystem(InvalidPrefix::class).canonical
        }
    }
})
