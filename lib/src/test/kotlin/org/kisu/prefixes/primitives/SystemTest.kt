package org.kisu.prefixes.primitives

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.ints.shouldBeZero
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.one
import org.kisu.test.fakes.InvalidPrefix
import org.kisu.test.generators.Systems

class SystemTest : StringSpec({

    "retrieves base unit" {
        checkAll(Systems.generator) { system ->
            system.canonical.factor.one.shouldBeTrue()
        }
    }

    "crashes if there is an invalid system with no base" {
        shouldThrow<IllegalStateException> {
            StandardSystem(InvalidPrefix::class).canonical
        }
    }

    "retrieves all prefixes for a system" {
        checkAll(Systems.generator) { system ->
            system.all.shouldNotBeEmpty()
        }
    }

    "all prefixes from a system are sorted by power" {
        checkAll(Systems.generator) { system ->
            system.all.shouldBeSorted()
        }
    }

    "retrieves the smallest prefix" {
        checkAll(Systems.generator) { system ->
            system.smallest shouldBe system.all.first()
        }
    }

    "retrieves the largest prefix" {
        checkAll(Systems.generator) { system ->
            system.largest shouldBe system.all.last()
        }
    }
})
