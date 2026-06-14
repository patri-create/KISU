package org.kisu.units.representation

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import io.kotest.property.arbitrary.filter
import io.kotest.property.checkAll
import org.kisu.KisuConfig
import org.kisu.prefixes.Metric
import org.kisu.productSymbol
import org.kisu.test.fakes.TestUnit
import org.kisu.test.generators.Exponents
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.Units
import org.kisu.units.scales.ExponentialScale
import java.math.BigDecimal

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

    "delegates factor to the scale" {
        checkAll(Metrics.generator) { metric ->
            TestUnit(metric).factor shouldBe ExponentialScale<Metric>().factor(metric)
        }
    }

    "converts to another scalar scale" {
        checkAll(Metrics.generator, Metrics.generator) { source, target ->
            val sourceUnit = TestUnit(source)
            val targetUnit = TestUnit(target)
            val conversion = sourceUnit.to(targetUnit)

            (targetUnit.factor * conversion).compareTo(sourceUnit.factor) shouldBe 0
        }
    }

    "converts using each scalar scale" {
        val source = TestUnit(ExponentialScale(), Metric.KILO, Unit("ts", 1))
        val target = TestUnit(ExponentialScale(SQUARE_SCALE_BASE), Metric.KILO, Unit("ts", 1))

        source.to(target).compareTo(source.factor.divide(target.factor, KisuConfig.precision)) shouldBe 0
    }

    "preserves overflow when multiplying clamped prefixes" {
        val left = TestUnit(Metric.QUETTA)
        val right = TestUnit(Metric.KILO)

        (left + right).factor.compareTo(left.factor * right.factor) shouldBe 0
    }

    "preserves overflow when dividing clamped prefixes" {
        val left = TestUnit(Metric.QUECTO)
        val right = TestUnit(Metric.KILO)

        (left - right).factor.compareTo(left.factor.divide(right.factor, KisuConfig.precision)) shouldBe 0
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

private val SQUARE_SCALE_BASE = BigDecimal("100")
