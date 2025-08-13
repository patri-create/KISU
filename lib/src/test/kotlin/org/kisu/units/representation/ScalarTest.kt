package org.kisu.units.representation

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import io.kotest.property.arbitrary.filter
import io.kotest.property.checkAll
import org.kisu.productSymbol
import org.kisu.test.fakes.TestUnit
import org.kisu.test.generators.Exponents
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.Scalars
import org.kisu.test.generators.Units

class ScalarTest : StringSpec({
    "Scalar is recognized as positive" {
        checkAll(Metrics.generator, Units.symbols, Exponents.range(1, Int.MAX_VALUE)) { prefix, symbol, exponent ->
            Scalar(prefix, unit = Unit(symbol, exponent)).positive.shouldBeTrue()
        }
    }

    "Scalar is not recognized as negative" {
        checkAll(Metrics.generator, Units.symbols, Exponents.range(Int.MIN_VALUE, 0)) { prefix, symbol, exponent ->
            Scalar(prefix, unit = Unit(symbol, exponent)).positive.shouldBeFalse()
        }
    }

    "Scalar is recognized as zero" {
        checkAll(Metrics.generator, Units.symbols) { prefix, symbol ->
            Scalar(prefix, unit = Unit(symbol, 0)).zero.shouldBeTrue()
        }
    }

    "Scalar is not recognized as zero" {
        checkAll(Metrics.generator, Units.symbols, Exponents.range().filter { !it.zero }) { prefix, symbol, exponent ->
            Scalar(prefix, unit = Unit(symbol, exponent)).zero.shouldBeFalse()
        }
    }

    "delegates factor to the prefix" {
        checkAll(Metrics.generator) { metric ->
            Scalar(metric, unit = TestUnit.UNIT).factor shouldBe metric.factor
        }
    }

    "symbol is the combination of the prefix and the unit" {
        checkAll(Metrics.generator, Units.generator) { prefix, unit ->
            Scalar(prefix, unit = unit).symbol shouldBe "${prefix.symbol}$unit"
        }
    }

    "symbol is empty if exponent is zero" {
        checkAll(Metrics.generator, Units.symbols) { prefix, symbol ->
            Scalar(prefix, unit = Unit(symbol, 0)).symbol.shouldBeEmpty()
        }
    }

    "the string representation is the symbol" {
        checkAll(Metrics.generator) { metric ->
            val expression = Scalar(metric, unit = TestUnit.UNIT)
            expression.symbol shouldBe expression.toString()
        }
    }

    "multiplying two scalars make a Product" {
        checkAll(Scalars.distinct(2)) { (a, b) ->
            (a * b).symbol shouldBe listOf(a, b).productSymbol
        }
    }

    "multiplying a scalar and a Product make a Product" {
        checkAll(Scalars.distinct(3)) { (a, b, c) ->
            (a * (b * c)).symbol shouldBe listOf(a, b, c).productSymbol
        }
    }

    "multiplying a scalar and a Quotient make a Quotient" {
        checkAll(Scalars.distinct(3)) { (a, b, c) ->
            (a * (b / c)).symbol shouldBe "${listOf(a, b).productSymbol}/$c"
        }
    }

    "dividing two scalars make a Quotient" {
        checkAll(Scalars.distinct(2)) { (a, b) ->
            (a / b).symbol shouldBe "$a/$b"
        }
    }

    "dividing a scalar and a Product make a Quotient" {
        checkAll(Scalars.distinct(3)) { (a, b, c) ->
            (a / (b * c)).symbol shouldBe "$a/(${listOf(b, c).productSymbol})"
        }
    }

    "dividing a scalar and a Quotient make a Quotient" {
        checkAll(Scalars.distinct(3)) { (a, b, c) ->
            (a / (b / c)).symbol shouldBe "${listOf(a, c).productSymbol}/$b"
        }
    }
})
