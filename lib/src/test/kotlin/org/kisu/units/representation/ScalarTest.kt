package org.kisu.units.representation

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import io.kotest.property.arbitrary.filter
import io.kotest.property.checkAll
import org.kisu.productSymbol
import org.kisu.test.fakes.TestMeasure
import org.kisu.test.fakes.TestUnit
import org.kisu.test.generators.Exponents
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.Units

class ScalarTest : StringSpec({
    "Scalar is recognized as positive" {
        checkAll(Metrics.generator, Units.symbols, Exponents.range(1, Int.MAX_VALUE)) { prefix, symbol, exponent ->
            TestUnit(prefix, unit = Unit(symbol, exponent)).positive.shouldBeTrue()
        }
    }

    "Scalar is not recognized as negative" {
        checkAll(Metrics.generator, Units.symbols, Exponents.range(Int.MIN_VALUE, 0)) { prefix, symbol, exponent ->
            TestUnit(prefix, unit = Unit(symbol, exponent)).positive.shouldBeFalse()
        }
    }

    "Scalar is recognized as zero" {
        checkAll(Metrics.generator, Units.symbols) { prefix, symbol ->
            TestUnit(prefix, unit = Unit(symbol, 0)).zero.shouldBeTrue()
        }
    }

    "Scalar is not recognized as zero" {
        checkAll(Metrics.generator, Units.symbols, Exponents.range().filter { !it.zero }) { prefix, symbol, exponent ->
            TestUnit(prefix, unit = Unit(symbol, exponent)).zero.shouldBeFalse()
        }
    }

    "delegates factor to the prefix" {
        checkAll(Metrics.generator) { metric ->
            TestUnit(metric).factor shouldBe metric.factor
        }
    }

    "symbol is the combination of the prefix and the unit" {
        checkAll(Metrics.generator, Units.symbols) { prefix, unit ->
            TestUnit(prefix, unit = Unit(unit, 1)).symbol shouldBe "${prefix.symbol}$unit"
        }
    }

    "symbol is empty if exponent is zero" {
        checkAll(Metrics.generator, Units.symbols) { prefix, symbol ->
            TestUnit(prefix, unit = Unit(symbol, 0)).symbol.shouldBeEmpty()
        }
    }

    "the string representation is the symbol" {
        checkAll(Units.distinct(1, Units.Mode.RANDOM)) { (expression) ->
            expression.symbol shouldBe expression.toString()
        }
    }

    "multiplying two scalars make a Product" {
        checkAll(Units.distinct(2)) { (a, b) ->
            (a * b).symbol shouldBe listOf(a, b).productSymbol
        }
    }

    "multiplying a scalar and a Product make a Product" {
        checkAll(Units.distinct(3)) { (a, b, c) ->
            (a * (b * c)).symbol shouldBe listOf(a, b, c).productSymbol
        }
    }

    "multiplying a scalar and a Quotient make a Quotient" {
        checkAll(Units.distinct(3)) { (a, b, c) ->
            (a * (b / c)).symbol shouldBe "${listOf(a, b).productSymbol}/$c"
        }
    }

    "dividing two scalars make a Quotient" {
        checkAll(Units.distinct(2)) { (a, b) ->
            (a / b).symbol shouldBe "$a/$b"
        }
    }

    "dividing a scalar and a Product make a Quotient" {
        checkAll(Units.distinct(3)) { (a, b, c) ->
            (a / (b * c)).symbol shouldBe "$a/(${listOf(b, c).productSymbol})"
        }
    }

    "dividing a scalar and a Quotient make a Quotient" {
        checkAll(Units.distinct(3)) { (a, b, c) ->
            (a / (b / c)).symbol shouldBe "${listOf(a, c).productSymbol}/$b"
        }
    }
})
