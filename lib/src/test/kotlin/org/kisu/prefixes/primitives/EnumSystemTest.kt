package org.kisu.prefixes.primitives

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
import org.kisu.test.generators.Systems
import java.math.BigDecimal

class EnumSystemTest : StringSpec({
    val withinFactors = Systems.generator.flatMap { system ->
        Arb.bigDecimal(
            min = system.smallest.factor,
            max = system.largest.factor
        ).map { factor -> system to factor }
    }

    val belowFactors = Systems.generator.flatMap { system ->
        val smallest = system.smallest
        val below = Arb.bigDecimal(
            min = smallest.factor - FACTOR_RANGE_MARGIN,
            max = smallest.factor
        )
        below.map { factor -> system to factor }
    }

    val aboveFactors = Systems.generator.flatMap { system ->
        val largest = system.largest
        val above = Arb.bigDecimal(
            min = largest.factor,
            max = largest.factor + FACTOR_RANGE_MARGIN
        )
        above.map { factor -> system to factor }
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

    "finds every exact prefix factor" {
        checkAll(Systems.generator) { system ->
            system.all.forEach { prefix ->
                system.find(prefix.factor) shouldBe prefix
            }
        }
    }

    "finds the closest prefix at or below a factor" {
        checkAll(withinFactors) { (system, factor) ->
            val prefix = system.find(factor)

            (prefix in system.all).shouldBeTrue()
            (prefix.factor <= factor).shouldBeTrue()
            system.all.none { candidate ->
                candidate.factor <= factor && candidate.factor > prefix.factor
            }.shouldBeTrue()
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

private val FACTOR_RANGE_MARGIN = BigDecimal("1000")
