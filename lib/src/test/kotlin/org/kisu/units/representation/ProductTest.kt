package org.kisu.units.representation

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.productSymbol
import org.kisu.test.fakes.TestUnit
import org.kisu.test.generators.Binaries
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.Scalars
import org.kisu.test.generators.Units

class ProductTest : StringSpec({
    "factor is the product of both prefixes" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            val expression = Product(
                Scalar(metric, unit = TestUnit.UNIT),
                Scalar(binary, unit = TestUnit.UNIT)
            )

            expression.factor shouldBe metric.factor * binary.factor
        }
    }

    "factors should be displayed in order" {
        checkAll(Scalars.distinct(2)) { (left, right) ->
            val factors = (left * right).factors

            factors.productSymbol shouldBe listOf(left, right).productSymbol
        }
    }

    "factors should coalesce equal units" {
        checkAll(Scalars.generator) { scalar ->
            val product = scalar * scalar
            val expectedPrefix = Metric.BASE.find(scalar.factor * scalar.factor)
            val expectedUnit = scalar.unit * scalar.unit

            product.factors.should { factors ->
                factors.shouldHaveSize(1)
                factors.first().symbol shouldBe "$expectedPrefix$expectedUnit"
            }
            product.symbol shouldBe product.factors.first().symbol
        }
    }

    "represents its symbol" {
        checkAll(
            Metrics.generator,
            Binaries.generator,
            Units.distinct(2)
        ) { leftPrefix, rightPrefix, (leftUnit, rightUnit) ->
            val left = Scalar(leftPrefix, unit = leftUnit)
            val right = Scalar(rightPrefix, unit = rightUnit)

            Product(left, right).symbol shouldBe listOf(left, right).productSymbol
        }
    }

    "a product of n 0-exponent scalars is empty" {
        checkAll(
            Metrics.generator,
            Metrics.generator,
            Units.distinct(2)
        ) { leftPrefix, rightPrefix, (leftUnit, rightUnit) ->
            val left = Scalar(leftPrefix, unit = leftUnit / leftUnit)
            val right = Scalar(rightPrefix, unit = rightUnit / rightUnit)
            val product = left * right

            product.symbol.shouldBeEmpty()
        }
    }

    "filters 0-exponent scalars" {
        checkAll(
            Metrics.generator,
            Metrics.generator,
            Units.distinct(2)
        ) { leftPrefix, rightPrefix, (leftUnit, rightUnit) ->
            // The issue, because Scalar with exponent 0 is printed as "", I am trying to merge two different Scalars.
            val right = Scalar(rightPrefix, unit = rightUnit)
            val product = Scalar(leftPrefix, unit = leftUnit / leftUnit) * right

            product.symbol shouldBe right.symbol
        }
    }


    "the string representation is the symbol" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            val expression = Product(
                Scalar(metric, unit = TestUnit.UNIT),
                Scalar(binary, unit = TestUnit.UNIT)
            )
            expression.symbol shouldBe expression.toString()
        }
    }

    "multiplying a Product and a Scalar makes a Product" {
        checkAll(Scalars.distinct(3)) { (a, b, c) ->
            ((a * b) * c).symbol shouldBe listOf(a, b, c).productSymbol
        }
    }

    "multiplying two Products makes a nested Product" {
        checkAll(Scalars.distinct(4)) { (a, b, c, d) ->
            ((a * b) * (c * d)).symbol shouldBe listOf(a, b, c, d).productSymbol
        }
    }

    "multiplying a Product and a Quotient makes a Quotient with a Product numerator" {
        checkAll(Scalars.distinct(4)) { (a, b, c, d) ->
            ((a * b) * (c / d)).symbol shouldBe "${listOf(a, b, c).productSymbol}/$d"
        }
    }

    "dividing a Product by a Scalar makes a Quotient" {
        checkAll(Scalars.distinct(3)) { (a, b, c) ->
            ((a * b) / c).symbol shouldBe "${listOf(a, b).productSymbol}/$c"
        }
    }

    "dividing a Product by a Product makes a Quotient" {
        checkAll(Scalars.distinct(4)) { (a, b, c, d) ->
            ((a * b) / (c * d)).symbol shouldBe
                    "${listOf(a, b).productSymbol}/(${listOf(c, d).productSymbol})"
        }
    }

    "dividing a Product by a Quotient makes a nested Quotient" {
        checkAll(Scalars.distinct(4)) { (a, b, c, d) ->
            ((a * b) / (c / d)).symbol shouldBe "${listOf(a, b, d).productSymbol}/$c"
        }
    }
})
