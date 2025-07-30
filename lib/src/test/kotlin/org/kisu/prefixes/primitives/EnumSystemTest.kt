package org.kisu.prefixes.primitives

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.bigDecimal
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import io.kotest.property.checkAll
import org.kisu.one
import org.kisu.test.fakes.InvalidPrefix
import org.kisu.test.generators.Systems
import java.math.BigDecimal

class EnumSystemTest : StringSpec({

    val belowFactors = Systems.generator.flatMap { system ->
        val smallest = system.smallest
        val below = Arb.bigDecimal(
            min = BigDecimal("1e-300"),
            max = smallest.factor
        )
        below.map { factor -> system to factor }
    }

    val aboveFactors = Systems.generator.flatMap { system ->
        val largest = system.largest
        val above = Arb.bigDecimal(
            min = largest.factor,
            max = BigDecimal("1e300")
        )
        above.map { factor -> system to factor }
    }

    "retrieves base unit" {
        checkAll(Systems.generator) { system ->
            system.canonical.factor.one.shouldBeTrue()
        }
    }

    "crashes if there is an invalid system with no base" {
        shouldThrow<IllegalStateException> {
            EnumSystem(InvalidPrefix::class).canonical
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

    "finds the appropriate prefix for an exact factor" {
        checkAll(Systems.generator) { system ->
            val prefix = system.all.random()

            system.find(prefix.factor) shouldBe prefix
        }
    }

    "returns the largest prefix when the factor exceeds the known range" {
        checkAll(aboveFactors) { (system, factor) ->
            system.find(factor) shouldBe system.largest
        }
    }

    "returns the smallest prefix when the factor is below the known range" {
        checkAll(belowFactors) { (system, factor) ->
            system.find(factor) shouldBe system.smallest
        }
    }
})
