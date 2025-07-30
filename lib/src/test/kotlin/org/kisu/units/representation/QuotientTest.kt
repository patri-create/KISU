package org.kisu.units.representation

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import io.kotest.property.checkAll
import org.kisu.KisuConfig
import org.kisu.prefixes.Metric
import org.kisu.productSymbol
import org.kisu.test.fakes.TestUnit
import org.kisu.test.generators.Binaries
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.Scalars
import org.kisu.test.generators.Units

class QuotientTest : StringSpec({
    "factor is the quotient of both prefixes" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            val expression = Quotient(
                Scalar(metric, unit = TestUnit.UNIT),
                Scalar(binary, unit = TestUnit.UNIT)
            )

            expression.factor shouldBe metric.factor.divide(binary.factor, KisuConfig.precision)
        }
    }

    "factors should simplify equal units" {
        checkAll(Scalars.generator) { scalar ->
            val quotient = scalar / scalar

            quotient.factors.should { factors ->
                factors.shouldBeEmpty()
            }
            quotient.symbol.shouldBeEmpty()
        }
    }

    "represents its symbol" {
        checkAll(
            Metrics.generator,
            Binaries.generator,
            Units.distinct(2)
        ) { metric, binary, (leftUnit, rightUnit) ->
            val numerator = Scalar(metric, unit = leftUnit)
            val denominator = Scalar(binary, unit = rightUnit)
            Quotient(numerator, denominator).symbol shouldBe "$numerator/$denominator"
        }
    }

    "the symbol is empty when all factors are 0-exponent" {
        checkAll(Scalars.generator) { scalar ->
            val quotient = scalar / scalar

            quotient.symbol.shouldBeEmpty()
        }
    }

    "the symbol is 1/factor when all numerator factors are 0-exponent" {
        checkAll(Scalars.distinct(2)) { (a, b) ->
            val quotient = (a / a) / b

            quotient.symbol shouldBe "1/$b"
        }
    }

    "the symbol is a product factor when all denominator factors are 0-exponent" {
        checkAll(Scalars.distinct(2)) { (a, b) ->
            val quotient = a / (b / b)

            quotient.symbol shouldBe a.symbol
        }
    }

    "the denominator does not have parenthesis when it is a single factor" {
        checkAll(Scalars.distinct(2)) { (a, b) ->
            val quotient = a / b

            quotient.symbol shouldBe "$a/$b"
        }
    }

    "the denominator  have parenthesis when it is multiple factor" {
        checkAll(Scalars.distinct(3)) { (a, b, c) ->
            val quotient = a / (b * c)

            quotient.symbol shouldBe "$a/(${listOf(b, c).productSymbol})"
        }
    }

    "the string representation is the symbol" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            val expression = Quotient(
                Scalar(metric, unit = TestUnit.UNIT),
                Scalar(binary, unit = TestUnit.UNIT)
            )
            expression.symbol shouldBe expression.toString()
        }
    }

    "multiplying a Quotient and a Scalar makes a Quotient with Product numerator" {
        checkAll(Scalars.distinct(3)) { (a, b, c) ->
            ((a / b) * c).symbol shouldBe "${listOf(a, c).productSymbol}/$b"
        }
    }

    "multiplying a Quotient and a Product makes a Quotient with Product numerator" {
        checkAll(Scalars.distinct(4)) { (a, b, c, d) ->
            ((a / b) * (c * d)).symbol shouldBe "${listOf(a, c, d).productSymbol}/$b"
        }
    }

    "multiplying two Quotients makes a Quotient with Product numerator and denominator" {
        checkAll(Scalars.distinct(4)) { (a, b, c, d) ->
            ((a / b) * (c / d)).symbol shouldBe "${listOf(a, c).productSymbol}/(${listOf(b, d).productSymbol})"
        }
    }

    "dividing a Quotient by a Scalar makes a Quotient with Product denominator" {
        checkAll(Scalars.distinct(3)) { (a, b, c) ->
            ((a / b) / c).symbol shouldBe "$a/(${listOf(b, c).productSymbol})"
        }
    }

    "dividing a Quotient by a Product makes a Quotient with Product denominator" {
        checkAll(Scalars.distinct(4)) { (a, b, c, d) ->
            ((a / b) / (c * d)).symbol shouldBe "$a/(${listOf(b, c, d).productSymbol})"
        }
    }

    "dividing a Quotient by a Quotient flips and multiplies the inner Quotient" {
        checkAll(Scalars.distinct(4)) { (a, b, c, d) ->
            ((a / b) / (c / d)).symbol shouldBe "${listOf(a, d).productSymbol}/(${listOf(b, c).productSymbol})"
        }
    }
})
