package org.kisu.prefixes

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.KisuConfig
import org.kisu.prefixes.Binary.BASE
import org.kisu.prefixes.Binary.QUEBI
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.Prefixes
import org.kisu.test.generators.Times

class PrefixTest : StringSpec({
    "order is maintained" {
        checkAll(Prefixes.generator, Prefixes.generator) { left, right ->
            left.sortWith(right).toList().shouldBeSorted()
            right.sortWith(left).toList().shouldBeSorted()
        }
    }

    "exponential prefix multiplication adds exponents and returns exponent overflow" {
        checkAll(Metrics.generator, Metrics.generator) { a, b ->
            val (prefix, overflow) = a + b

            val expectedPower = a.power + b.power
            val expectedPrefix = a.find(expectedPower.toBigDecimal())
            val expectedOverflow = expectedPower - expectedPrefix.power

            prefix shouldBe expectedPrefix
            overflow shouldBe expectedOverflow
        }
    }

    "exponential prefix division subtracts exponents and returns exponent overflow" {
        checkAll(Metrics.generator, Metrics.generator) { a, b ->
            val (prefix, overflow) = a - b

            val expectedPower = a.power - b.power
            val expectedPrefix = a.find(expectedPower.toBigDecimal())
            val expectedOverflow = expectedPower - expectedPrefix.power

            prefix shouldBe expectedPrefix
            overflow shouldBe expectedOverflow
        }
    }

    "binary prefix multiplication returns exponent overflow" {
        val (prefix, overflow) = QUEBI + QUEBI

        prefix shouldBe QUEBI
        overflow shouldBe QUEBI.power
    }

    "binary prefix division returns exponent overflow" {
        val (prefix, overflow) = BASE - QUEBI

        prefix shouldBe BASE
        overflow shouldBe -QUEBI.power
    }

    "linear prefix multiplication multiplies factors and returns overflow" {
        checkAll(Times.generator, Times.generator) { a, b ->
            val (prefix, remainder) = a * b

            val expectedFactor = a.factor * b.factor
            val expectedPrefix = a.find(expectedFactor)
            val expectedRemainder = expectedFactor.divide(expectedPrefix.factor, KisuConfig.precision)

            prefix shouldBe expectedPrefix
            remainder.compareTo(expectedRemainder) shouldBe 0
        }
    }

    "linear prefix division divides factors and returns overflow" {
        checkAll(Times.generator, Times.generator) { a, b ->
            val (prefix, remainder) = a / b

            val expectedFactor = a.factor.divide(b.factor, KisuConfig.precision)
            val expectedPrefix = a.find(expectedFactor)
            val expectedRemainder = expectedFactor.divide(expectedPrefix.factor, KisuConfig.precision)

            prefix shouldBe expectedPrefix
            remainder.compareTo(expectedRemainder) shouldBe 0
        }
    }
})
