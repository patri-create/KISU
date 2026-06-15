package org.kisu.prefixes

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.test.generators.ExponentialPrefixes
import org.kisu.test.generators.Metrics

class ExponentialPrefixTest : StringSpec({
    "orders prefixes by power" {
        checkAll(ExponentialPrefixes.generator, ExponentialPrefixes.generator) { left, right ->
            left.compareTo(right) shouldBe left.power.compareTo(right.power)
            left.sortWith(right).toList().shouldBeSorted()
        }
    }

    "adds powers and returns exponent overflow" {
        checkAll(Metrics.generator, Metrics.generator) { left, right ->
            val (prefix, overflow) = left + right
            val expectedPower = left.power + right.power

            prefix shouldBe left.find(expectedPower.toBigDecimal())
            overflow shouldBe expectedPower - prefix.power
        }
    }

    "subtracts powers and returns exponent overflow" {
        checkAll(Metrics.generator, Metrics.generator) { left, right ->
            val (prefix, overflow) = left - right
            val expectedPower = left.power - right.power

            prefix shouldBe left.find(expectedPower.toBigDecimal())
            overflow shouldBe expectedPower - prefix.power
        }
    }
})
