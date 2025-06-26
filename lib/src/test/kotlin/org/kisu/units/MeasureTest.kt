package org.kisu.units

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.bigDecimal
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.choice
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.map
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.fakes.TestUnit
import org.kisu.test.utils.optimalPrefixFrom
import java.math.BigDecimal
import java.math.MathContext
import org.kisu.test.generators.Metric as MetricGenerator

class MeasureTest : StringSpec({

    val magnitudes = Arb.bigDecimal()
    val nonZeroMagnitudes = magnitudes.filter { it.compareTo(BigDecimal.ZERO) != 0 }
    val prefixes = MetricGenerator.system
    val measures =
        Arb.bind(magnitudes, prefixes) { magnitude, prefix ->
            TestUnit(magnitude, prefix)
        }
    val greaterThanZero: Arb<BigDecimal> =
        Arb.choice(
            Arb.bigDecimal(min = BigDecimal.ONE, max = BigDecimal.valueOf(Double.MAX_VALUE)),
            Arb.bigDecimal(min = BigDecimal.ONE, max = BigDecimal.valueOf(Double.MAX_VALUE)).map { it.negate() },
        )
    val lesserThanOne =
        Arb.bigDecimal(
            min = BigDecimal.ONE.negate().plus(BigDecimal.valueOf(1e-10)),
            max = BigDecimal.ONE.minus(BigDecimal.valueOf(1e-10)),
        ).filter {
            it.compareTo(BigDecimal.ZERO) != 0
        }

    val inRange =
        Arb.int(-30..30)
            .filter { it != 0 }
            .map { power ->
                if (power > 0) {
                    BigDecimal.TEN.pow(power)
                } else {
                    BigDecimal.ONE.divide(BigDecimal.TEN.pow(-power), MathContext.DECIMAL128)
                }
            }

    "rescales correctly" {
        checkAll(measures, prefixes) { measure, newPrefix ->
            measure.to(newPrefix)
        }
    }

    "renders literal when the magnitude is not zero" {
        checkAll(nonZeroMagnitudes, prefixes) { magnitude, prefix ->
            TestUnit(magnitude, prefix).representation shouldBe "$magnitude $prefix${TestUnit.SYMBOL}"
        }
    }

    "renders literal when the magnitude is zero" {
        checkAll(prefixes) { prefix ->
            TestUnit(BigDecimal.ZERO, prefix).representation shouldBe "0 ${TestUnit.SYMBOL}"
        }
    }

    "renders canonical" {
        checkAll(magnitudes, prefixes) { magnitude, prefix ->
            TestUnit(magnitude, prefix).canonical.representation shouldBe
                TestUnit(magnitude, prefix).to(prefix.canonical).representation
        }
    }

    "renders optimal correctly when magnitude is 0" {
        checkAll(prefixes) { prefix ->
            val measure = TestUnit(BigDecimal.ZERO, prefix)
            measure.optimal shouldBe measure.canonical
        }
    }

    "renders optimal correctly when magnitude is beyond the largest prefix" {
        checkAll(greaterThanZero) { magnitude ->
            val measure = TestUnit(magnitude, Metric.QUETTA)
            measure.optimal.representation shouldBe measure.representation
        }
    }

    "renders optimal correctly when magnitude is beyond the smallest prefix" {
        checkAll(lesserThanOne) { magnitude ->
            val measure = TestUnit(magnitude, Metric.QUECTO)
            measure.optimal.representation shouldBe measure.representation
        }
    }

    "renders optimal correctly when magnitude is in range" {
        checkAll(inRange) { magnitude ->
            val measure = TestUnit(magnitude, Metric.BASE)
            val optimalPrefix = magnitude.optimalPrefixFrom(Metric.BASE)
            val correctedMagnitude = magnitude * Metric.BASE.scale(optimalPrefix)
            val compact = TestUnit(correctedMagnitude, optimalPrefix)

            measure.optimal.representation shouldBe compact.representation
        }
    }

    "representation is the optimal representation" {
        checkAll(measures) { measure ->
            measure.optimal.representation shouldBe measure.toString()
        }
    }

    "equality is reflexive" {
        checkAll(measures) { x ->
            x.equals(x).shouldBeTrue()
        }
    }

    "equality is symmetric" {
        checkAll(measures, prefixes) { x, prefix ->
            val y = x.to(prefix)
            (x == y) shouldBe (y == x)
        }
    }

    "equality is transitive" {
        checkAll(measures, prefixes, prefixes) { x, first, second ->
            val y = x.to(first)
            val z = x.to(second)

            (x == y).shouldBeTrue()
            (y == z).shouldBeTrue()
            (x == z).shouldBeTrue()
        }
    }

    @Suppress("EqualsNullCall")
    "equality is non-null" {
        checkAll(measures) { x ->
            (x.equals(null)).shouldBeFalse()
        }
    }
})
