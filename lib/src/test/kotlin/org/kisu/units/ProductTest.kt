package org.kisu.units

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.test.fakes.TestUnit
import org.kisu.test.generators.Binaries
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.Scalars

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

    "multiplying a Product and a Scalar makes a Product" {
        checkAll(
            Scalars.generator,
            Scalars.generator,
            Scalars.generator
        ) { a, b, c -> ((a * b) * c).symbol shouldBe "$a·$b·$c" }
    }

    "multiplying two Products makes a nested Product" {
        checkAll(
            Scalars.generator,
            Scalars.generator,
            Scalars.generator,
            Scalars.generator
        ) { a, b, c, d -> ((a * b) * (c * d)).symbol shouldBe "$a·$b·$c·$d" }
    }

    "multiplying a Product and a Quotient makes a Quotient with a Product numerator" {
        checkAll(
            Scalars.generator,
            Scalars.generator,
            Scalars.generator,
            Scalars.generator
        ) { a, b, c, d ->
            ((a * b) * (c / d)).symbol shouldBe "$a·$b·$c/$d"
        }
    }

    "dividing a Product by a Scalar makes a Quotient" {
        checkAll(
            Scalars.generator,
            Scalars.generator,
            Scalars.generator
        ) { a, b, c ->
            ((a * b) / c).symbol shouldBe "$a·$b/$c"
        }
    }

    "dividing a Product by a Product makes a Quotient" {
        checkAll(
            Scalars.generator,
            Scalars.generator,
            Scalars.generator,
            Scalars.generator
        ) { a, b, c, d -> ((a * b) / (c * d)).symbol shouldBe "$a·$b/($c·$d)" }
    }

    "dividing a Product by a Quotient makes a nested Quotient" {
        checkAll(
            Scalars.generator,
            Scalars.generator,
            Scalars.generator,
            Scalars.generator
        ) { a, b, c, d -> ((a * b) / (c / d)).symbol shouldBe "$a·$b·$d/$c" }
    }
})
