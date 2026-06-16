package org.kisu.prefixes

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.KisuConfig
import org.kisu.test.generators.LinearPrefixes
import org.kisu.test.generators.Times

class LinearPrefixTest : StringSpec({
    "orders prefixes by factor" {
        checkAll(LinearPrefixes.generator, LinearPrefixes.generator) { left, right ->
            left.compareTo(right) shouldBe left.factor.compareTo(right.factor)
            left.sortWith(right).toList().shouldBeSorted()
        }
    }

    "multiplies factors and returns multiplicative remainder" {
        checkAll(Times.generator, Times.generator) { left, right ->
            val (prefix, remainder) = left * right
            val expectedFactor = left.factor.multiply(right.factor)
            val expectedRemainder = expectedFactor.divide(prefix.factor, KisuConfig.precision)

            prefix shouldBe left.find(expectedFactor)
            remainder.compareTo(expectedRemainder) shouldBe 0
        }
    }

    "divides factors and returns multiplicative remainder" {
        checkAll(Times.generator, Times.generator) { left, right ->
            val (prefix, remainder) = left / right
            val expectedFactor = left.factor.divide(right.factor, KisuConfig.precision)
            val expectedRemainder = expectedFactor.divide(prefix.factor, KisuConfig.precision)

            prefix shouldBe left.find(expectedFactor)
            remainder.compareTo(expectedRemainder) shouldBe 0
        }
    }
})
