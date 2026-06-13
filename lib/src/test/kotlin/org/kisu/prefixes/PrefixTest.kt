package org.kisu.prefixes

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.KisuConfig
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.Prefixes
import org.kisu.test.generators.Times
import org.kisu.test.matchers.plusOrMinus
import java.math.BigDecimal

class PrefixTest : StringSpec({
    "rescales to a different power" {
        checkAll(Prefixes.generator, Prefixes.generator) { left, right ->
            val decimal = left.to(right) * right.to(left)
            decimal shouldBe (BigDecimal.ONE plusOrMinus (BigDecimal.valueOf(ALLOWED_TOLERANCE)))
        }
    }

    "order is maintained" {
        checkAll(Prefixes.generator, Prefixes.generator) { left, right ->
            left.sortWith(right).toList().shouldBeSorted()
            right.sortWith(left).toList().shouldBeSorted()
        }
    }

    "exponential prefix multiplication adds exponents and returns overflow" {
        checkAll(Metrics.generator, Metrics.generator) { a, b ->
            val (prefix, remainder) = a * b

            val expectedFactor = a.factor + b.factor
            val expectedPrefix = a.find(expectedFactor)
            val expectedRemainder = expectedFactor - expectedPrefix.factor

            prefix shouldBe expectedPrefix
            remainder.compareTo(expectedRemainder) shouldBe 0
        }
    }

    "exponential prefix division subtracts exponents and returns overflow" {
        checkAll(Metrics.generator, Metrics.generator) { a, b ->
            val (prefix, remainder) = a / b

            val expectedFactor = a.factor - b.factor
            val expectedPrefix = a.find(expectedFactor)
            val expectedRemainder = expectedFactor - expectedPrefix.factor

            prefix shouldBe expectedPrefix
            remainder.compareTo(expectedRemainder) shouldBe 0
        }
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

private const val ALLOWED_TOLERANCE = 1e-14
