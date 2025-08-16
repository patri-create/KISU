package org.kisu.units.representation

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.productSymbol
import org.kisu.test.generators.Units

class ProductTest : StringSpec({
    "factor is the product of both prefixes" {
        checkAll(Units.generator, Units.binaries()) { a, b ->
            val expression = Product(a.self, b.self)

            expression.factor shouldBe a.factor * b.factor
        }
    }

    "factors should be displayed in order" {
        checkAll(Units.distinct(2)) { (left, right) ->
            val factors = (left * right).factors

            factors.productSymbol shouldBe listOf(left, right).productSymbol
        }
    }

    "factors should coalesce equal units" {
        checkAll(Units.generator) { scalar ->
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
        checkAll(Units.distinct(2, Units.Mode.RANDOM)) { (left, right) ->
            Product(left.self, right.self).symbol shouldBe listOf(left, right).productSymbol
        }
    }

    "a product of n 0-exponent scalars is empty" {
        checkAll(Units.distinct(2, Units.Mode.RANDOM)) { (left, right) ->
            val product = (left / left) * (right / right)

            product.symbol.shouldBeEmpty()
        }
    }

    "filters 0-exponent scalars" {
        checkAll(Units.distinct(2, Units.Mode.RANDOM)) { (left, right) ->
            // The issue, because Scalar with exponent 0 is printed as "", I am trying to merge two different Scalars.
            val product = (left / left) * right

            product.symbol shouldBe right.symbol
        }
    }

    "the string representation is the symbol" {
        checkAll(Units.generator, Units.binaries()) { metric, binary ->
            val expression = Product(metric.self, binary.self)

            expression.symbol shouldBe expression.toString()
        }
    }

    "multiplying a Product and a Scalar makes a Product" {
        checkAll(Units.distinct(3)) { (a, b, c) ->
            ((a * b) * c).symbol shouldBe listOf(a, b, c).productSymbol
        }
    }

    "multiplying two Products makes a nested Product" {
        checkAll(Units.distinct(4)) { (a, b, c, d) ->
            ((a * b) * (c * d)).symbol shouldBe listOf(a, b, c, d).productSymbol
        }
    }

    "multiplying a Product and a Quotient makes a Quotient with a Product numerator" {
        checkAll(Units.distinct(4)) { (a, b, c, d) ->
            ((a * b) * (c / d)).symbol shouldBe "${listOf(a, b, c).productSymbol}/$d"
        }
    }

    "dividing a Product by a Scalar makes a Quotient" {
        checkAll(Units.distinct(3)) { (a, b, c) ->
            ((a * b) / c).symbol shouldBe "${listOf(a, b).productSymbol}/$c"
        }
    }

    "dividing a Product by a Product makes a Quotient" {
        checkAll(Units.distinct(4)) { (a, b, c, d) ->
            ((a * b) / (c * d)).symbol shouldBe
                    "${listOf(a, b).productSymbol}/(${listOf(c, d).productSymbol})"
        }
    }

    "dividing a Product by a Quotient makes a nested Quotient" {
        checkAll(Units.distinct(4)) { (a, b, c, d) ->
            ((a * b) / (c / d)).symbol shouldBe "${listOf(a, b, d).productSymbol}/$c"
        }
    }
})
