package org.kisu.units

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.test.fakes.TestUnit
import org.kisu.test.generators.Binaries
import org.kisu.test.generators.Metrics

class ProductTest : StringSpec({
    "factor is the product of both prefixes" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            val expression = Product(
                Scalar(metric, TestUnit.SYMBOL),
                Scalar(binary, TestUnit.SYMBOL)
            )

            expression.factor shouldBe metric.factor * binary.factor
        }
    }

    "represents its symbol" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            Product(
                Scalar(metric, TestUnit.SYMBOL),
                Scalar(binary, TestUnit.SYMBOL)
            ).symbol shouldBe "${metric.symbol}${TestUnit.SYMBOL}·${binary.symbol}${TestUnit.SYMBOL}"
        }
    }

    "the string representation is the symbol" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            val expression = Product(
                Scalar(metric, TestUnit.SYMBOL),
                Scalar(binary, TestUnit.SYMBOL)
            )
            expression.symbol shouldBe expression.toString()
        }
    }
})
