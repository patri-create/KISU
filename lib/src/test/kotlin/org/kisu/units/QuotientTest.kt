package org.kisu.units

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.KisuConfig
import org.kisu.test.fakes.TestUnit
import org.kisu.test.generators.Binaries
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.Scalars

class QuotientTest : StringSpec({
    "factor is the quotient of both prefixes" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            val expression = Quotient(
                Scalar(metric, TestUnit.SYMBOL),
                Scalar(binary, TestUnit.SYMBOL)
            )

            expression.factor shouldBe metric.factor.divide(binary.factor, KisuConfig.precision)
        }
    }

    "represents its symbol" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            Quotient(
                Scalar(metric, TestUnit.SYMBOL),
                Scalar(binary, TestUnit.SYMBOL)
            ).symbol shouldBe "${metric.symbol}${TestUnit.SYMBOL}/${binary.symbol}${TestUnit.SYMBOL}"
        }
    }

    "the string representation is the symbol" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            val expression = Quotient(
                Scalar(metric, TestUnit.SYMBOL),
                Scalar(binary, TestUnit.SYMBOL)
            )
            expression.symbol shouldBe expression.toString()
        }
    }

    "multiplying a Quotient and a Scalar makes a Quotient with Product numerator" {
        checkAll(
            Scalars.generator,
            Scalars.generator,
            Scalars.generator
        ) { a, b, c -> ((a / b) * c).symbol shouldBe "$a·$c/$b" }
    }

    "multiplying a Quotient and a Product makes a Quotient with Product numerator" {
        checkAll(
            Scalars.generator,
            Scalars.generator,
            Scalars.generator,
            Scalars.generator
        ) { a, b, c, d -> ((a / b) * (c * d)).symbol shouldBe "$a·$c·$d/$b" }
    }

    "multiplying two Quotients makes a Quotient with Product numerator and denominator" {
        checkAll(
            Scalars.generator,
            Scalars.generator,
            Scalars.generator,
            Scalars.generator
        ) { a, b, c, d -> ((a / b) * (c / d)).symbol shouldBe "$a·$c/($b·$d)" }
    }

    "dividing a Quotient by a Scalar makes a Quotient with Product denominator" {
        checkAll(
            Scalars.generator,
            Scalars.generator,
            Scalars.generator
        ) { a, b, c -> ((a / b) / c).symbol shouldBe "$a/($b·$c)" }
    }

    "dividing a Quotient by a Product makes a Quotient with Product denominator" {
        checkAll(
            Scalars.generator,
            Scalars.generator,
            Scalars.generator,
            Scalars.generator
        ) { a, b, c, d ->
            ((a / b) / (c * d)).symbol shouldBe "$a/($b·$c·$d)"
        }
    }

    "dividing a Quotient by a Quotient flips and multiplies the inner Quotient" {
        checkAll(
            Scalars.generator,
            Scalars.generator,
            Scalars.generator,
            Scalars.generator
        ) { a, b, c, d -> ((a / b) / (c / d)).symbol shouldBe "$a·$d/($b·$c)" }
    }
})
