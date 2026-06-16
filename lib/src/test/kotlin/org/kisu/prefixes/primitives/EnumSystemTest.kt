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
import org.kisu.prefixes.ExponentialPrefix
import org.kisu.prefixes.LinearPrefix
import org.kisu.prefixes.Prefix
import org.kisu.test.generators.Systems
import java.math.BigDecimal

class EnumSystemTest : StringSpec({
    val withinCoordinates = Systems.generator.flatMap { system ->
        Arb.bigDecimal(
            min = system.smallest.coordinate,
            max = system.largest.coordinate
        ).map { coordinate -> system to coordinate }
    }

    val belowCoordinates = Systems.generator.flatMap { system ->
        val smallest = system.smallest
        val below = Arb.bigDecimal(
            min = smallest.coordinate - FACTOR_RANGE_MARGIN,
            max = smallest.coordinate
        )
        below.map { coordinate -> system to coordinate }
    }

    val aboveCoordinates = Systems.generator.flatMap { system ->
        val largest = system.largest
        val above = Arb.bigDecimal(
            min = largest.coordinate,
            max = largest.coordinate + FACTOR_RANGE_MARGIN
        )
        above.map { coordinate -> system to coordinate }
    }

    "retrieves all prefixes for a system" {
        checkAll(Systems.generator) { system ->
            system.all.shouldNotBeEmpty()
        }
    }

    "all prefixes from a system are sorted by natural ordering" {
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
                system.find(prefix.coordinate) shouldBe prefix
            }
        }
    }

    "finds the closest prefix at or below a factor" {
        checkAll(withinCoordinates) { (system, coordinate) ->
            val prefix = system.find(coordinate)

            (prefix in system.all).shouldBeTrue()
            (prefix.coordinate <= coordinate).shouldBeTrue()
            system.all.none { candidate ->
                candidate.coordinate <= coordinate && candidate.coordinate > prefix.coordinate
            }.shouldBeTrue()
        }
    }

    "returns the largest prefix when the factor exceeds the known range" {
        checkAll(aboveCoordinates) { (system, coordinate) ->
            system.find(coordinate) shouldBe system.largest
        }
    }

    "returns the smallest prefix when the factor is below the known range" {
        checkAll(belowCoordinates) { (system, coordinate) ->
            system.find(coordinate) shouldBe system.smallest
        }
    }
})

private val FACTOR_RANGE_MARGIN = BigDecimal("1000")

private val Prefix<*>.coordinate: BigDecimal
    get() =
        when (this) {
            is ExponentialPrefix<*> -> power.toBigDecimal()
            is LinearPrefix<*> -> factor
            else -> error("Unsupported prefix type: ${this::class.qualifiedName}")
        }
