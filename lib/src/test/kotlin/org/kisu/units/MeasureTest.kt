package org.kisu.units

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.bigDecimal
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.choice
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.map
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.prefixes.Metric
import org.kisu.test.fakes.TestUnit
import org.kisu.test.generators.Magnitudes.composition
import org.kisu.test.generators.Measures
import org.kisu.test.generators.distinct
import org.kisu.test.generators.nonZero
import org.kisu.test.matchers.plusOrMinus
import org.kisu.test.utils.magnitude
import org.kisu.test.utils.optimalPrefixFrom
import org.kisu.zero
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import org.kisu.test.generators.Metrics as MetricGenerator

class MeasureTest : StringSpec({

    val magnitudes = Arb.bigDecimal()
    val nonZeroMagnitudes = magnitudes.filter { it.compareTo(BigDecimal.ZERO) != 0 }
    val prefixes = MetricGenerator.generator
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
        ).filter { !it.zero }
    val scalars = Arb.double().filter { it != 0.0 && !it.isNaN() && !it.isInfinite() }
    val divisionScalars =
        Arb.bigDecimal()
            .filter { it != BigDecimal.ZERO }
            .filter { it in BigDecimal("1E-6")..BigDecimal("1E10") }
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
            TestUnit(
                magnitude,
                prefix
            ).representation shouldBe "${magnitude.stripTrailingZeros()} $prefix${TestUnit.SYMBOL}"
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
                TestUnit(magnitude, prefix).to(Scalar(prefix.canonical, TestUnit.SYMBOL)).representation
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
            val correctedMagnitude = magnitude * Metric.BASE.to(optimalPrefix)
            val compact = TestUnit(correctedMagnitude, optimalPrefix)

            measure.optimal.representation shouldBe compact.representation
        }
    }

    "representation is the optimal representation" {
        checkAll(measures) { measure ->
            measure.optimal.representation shouldBe measure.toString()
        }
    }

    "adds two of the same unit" {
        checkAll(Arb.bigDecimal(), Arb.bigDecimal()) { a, b ->
            val left = TestUnit(a)
            val right = TestUnit(b)
            (left + right) shouldBe TestUnit(a + b)
        }
    }

    "addition is commutative" {
        checkAll(Measures.generator, Measures.generator) { a, b ->
            (a + b) shouldBe (b + a)
        }
    }

    "addition is associative" {
        checkAll(Measures.generator, Measures.generator, Measures.generator) { a, b, c ->
            ((a + b) + c) shouldBe (a + (b + c))
        }
    }

    "addition has zero identity" {
        checkAll(Measures.generator) { a ->
            val zero = TestUnit(BigDecimal.ZERO)

            (a + zero) shouldBe a
        }
    }

    "subtraction is right-inverse of addition" {
        checkAll(Measures.generator, Measures.generator) { a, b ->
            ((a + b) - b) shouldBe a
        }
    }

    "subtraction of self is zero" {
        checkAll(Measures.generator) { a ->
            (a - a) shouldBe TestUnit(BigDecimal.ZERO)
        }
    }

    "subtraction is not commutative" {
        checkAll(
            Measures.generator.flatMap { a ->
                Measures.generator.filter { b -> a != b }.map { b -> a to b }
            },
        ) { (a, b) ->
            (a - b) shouldNotBe (b - a)
        }
    }

    "subtraction is not associative" {
        checkAll(
            Measures.generator.nonZero,
            Measures.generator.nonZero,
            Measures.generator.nonZero,
        ) { a, b, c ->
            ((a - b) - c) shouldNotBe (a - (b - c))
        }
    }

    "scalar distributivity over subtraction" {
        checkAll(Measures.generator, Measures.generator, scalars) { a, b, scalar ->
            val scale = BigDecimal.valueOf(scalar)
            val left = (a - b) * scale
            val right = (a * scale) - (b * scale)
            left shouldBe right
        }
    }

    "multiplying by one keeps the same value" {
        checkAll(Measures.generator) { a ->
            (a * 1) shouldBe a
        }
    }

    "multiplying by zero gives zero" {
        checkAll(Measures.generator) { a ->
            (a * 0) shouldBe TestUnit(BigDecimal.ZERO)
        }
    }

    "scalar distributivity over addition" {
        checkAll(Measures.generator, Measures.generator, scalars) { a, b, scalar ->
            val scale = BigDecimal.valueOf(scalar)
            val left = (a * scale) + (b * scale)
            val right = (a + b) * scale
            left shouldBe right
        }
    }

    "scalar multiplication is associative" {
        checkAll(Measures.generator, nonZeroMagnitudes, nonZeroMagnitudes) { a, s1, s2 ->
            val b1 = a * s1
            val b2 = b1 * s2
            val combined = a * (s1 * s2)
            b2 shouldBe combined
        }
    }

    "division is right-inverse of multiplication" {
        checkAll(Measures.generator, divisionScalars) { a, scalar ->
            val result = (a * scalar) / scalar
            result.magnitude shouldBe (a.magnitude plusOrMinus (BigDecimal.valueOf(ALLOWED_TOLERANCE)))
        }
    }

    "division is left-inverse of multiplication" {
        checkAll(Measures.generator, divisionScalars) { a, scalar ->
            val result = (a / scalar) * scalar
            result.magnitude shouldBe (a.magnitude plusOrMinus (BigDecimal.valueOf(ALLOWED_TOLERANCE)))
        }
    }

    "division by one keeps the same value" {
        checkAll(Measures.generator) { a ->
            val result = a / 1
            result.magnitude shouldBe (a.magnitude plusOrMinus (BigDecimal.valueOf(ALLOWED_TOLERANCE)))
        }
    }

    "scalar division distributes over subtraction" {
        checkAll(Measures.generator, Measures.generator, divisionScalars) { a, b, scalar ->
            val left = (a - b) / scalar
            val right = (a / scalar) - (b / scalar)
            left.magnitude shouldBe (right.magnitude plusOrMinus (BigDecimal.valueOf(ALLOWED_TOLERANCE)))
        }
    }

    "division by scalar product equals sequential division" {
        checkAll(Measures.generator, divisionScalars, divisionScalars) { a, s1, s2 ->
            val left = a / (s1 * s2)
            val right = (a / s1) / s2
            left.magnitude shouldBe (right.magnitude plusOrMinus (BigDecimal.valueOf(ALLOWED_TOLERANCE)))
        }
    }

    "rangeTo includes start and end" {
        checkAll(Measures.generator.distinct) { (a, b) ->
            val (left, right) = a sortWith b
            val range = left..right
            (left in range).shouldBeTrue()
            (right in range).shouldBeTrue()
        }
    }

    "rangeUntil includes start but excludes end" {
        checkAll(Measures.generator.distinct) { (a, b) ->
            val (left, right) = listOf(a, b).sorted()
            val range = left..<right
            (left in range).shouldBeTrue()
            (right in range).shouldBeFalse()
        }
    }

    "a decompositon of zero is a zero-base prefix" {
        checkAll(
            MetricGenerator.generator
        ) { prefix ->
            TestUnit(BigDecimal.ZERO, prefix).decomposition shouldContainExactly
                listOf(TestUnit(BigDecimal.ZERO, prefix))
        }
    }

    "decomposes a metric into its prefix components" {
        checkAll(
            Metric.BASE.composition,
            MetricGenerator.generator
        ) { composition: List<Pair<BigInteger, Metric>>, prefix ->
            val testUnit = TestUnit(composition.magnitude.divide(prefix.factor, MathContext.UNLIMITED), prefix)
            val decomposition: List<TestUnit> = testUnit.decomposition
            decomposition shouldContainAll composition.map { (magnitude, prefix) ->
                TestUnit(
                    magnitude.bigDecimal,
                    prefix
                )
            }
        }
    }

    "decomposes unit into magnitude, prefix and symbol" {
        checkAll(Arb.bigDecimal(), MetricGenerator.generator) { magnitude, prefix ->
            TestUnit(magnitude, prefix).should { (number, expression, unit) ->
                number shouldBe magnitude
                expression shouldBe "$prefix${TestUnit.SYMBOL}"
                unit shouldBe TestUnit.SYMBOL
            }
        }
    }

    "equality is reflexive" {
        checkAll(measures) { x ->
            (x == x).shouldBeTrue()
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
            val y = x.to(Scalar(first, TestUnit.SYMBOL))
            val z = x.to(Scalar(second, TestUnit.SYMBOL))

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

private const val ALLOWED_TOLERANCE = 1e-14
