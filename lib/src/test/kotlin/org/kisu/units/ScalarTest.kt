package org.kisu.units

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.test.fakes.TestUnit
import org.kisu.test.generators.Metrics

class ScalarTest : StringSpec({
    "delegates factor to the prefix" {
        checkAll(Metrics.generator) { metric ->
            Scalar(metric, TestUnit.SYMBOL).factor shouldBe metric.factor
        }
    }

    "symbol is the combination of the prefix and the unit" {
        checkAll(Metrics.generator) { metric ->
            Scalar(metric, TestUnit.SYMBOL).symbol shouldBe "${metric.symbol}${TestUnit.SYMBOL}"
        }
    }

    "the string representation is the symbol" {
        checkAll(Metrics.generator) { metric ->
            val expression = Scalar(metric, TestUnit.SYMBOL)
            expression.symbol shouldBe expression.toString()
        }
    }
})
