package org.kisu.prefixes

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.KisuConfig
import org.kisu.prefixes.Binary.BASE
import org.kisu.prefixes.Binary.QUEBI
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.Prefixes
import org.kisu.test.generators.Times
import org.kisu.test.matchers.plusOrMinus
import org.kisu.units.scales.ExponentialScale
import java.math.BigDecimal

class PrefixTest : StringSpec({
    "rescales to a different power" {
        checkAll(Prefixes.generator, Prefixes.generator) { left, right ->
            val decimal = left.to(right) * right.to(left)
            decimal shouldBe (BigDecimal.ONE plusOrMinus (BigDecimal.valueOf(ALLOWED_TOLERANCE)))
        }
    }

    "order is maintained" {
        checkAll(Prefixes.generator, Prefixes.generator) { left, right ->
            left.sortWith(right).toList().shouldBeSorted()
            right.sortWith(left).toList().shouldBeSorted()
        }
    }

    "exponential prefix multiplication adds exponents and returns multiplicative overflow" {
        val scale = ExponentialScale<Metric>()

        checkAll(Metrics.generator, Metrics.generator) { a, b ->
            val (prefix, remainder) = a * b

            val expectedFactor = a.factor + b.factor
            val expectedPrefix = a.find(expectedFactor)
            val expectedConcreteFactor = scale.factor(a) * scale.factor(b)
            val actualConcreteFactor = scale.factor(prefix) * remainder

            prefix shouldBe expectedPrefix
            actualConcreteFactor.compareTo(expectedConcreteFactor) shouldBe 0
        }
    }

    "exponential prefix division subtracts exponents and returns multiplicative overflow" {
        val scale = ExponentialScale<Metric>()

        checkAll(Metrics.generator, Metrics.generator) { a, b ->
            val (prefix, remainder) = a / b

            val expectedFactor = a.factor - b.factor
            val expectedPrefix = a.find(expectedFactor)
            val expectedConcreteFactor = scale.factor(a).divide(scale.factor(b), KisuConfig.precision)
            val actualConcreteFactor = scale.factor(prefix) * remainder

            prefix shouldBe expectedPrefix
            actualConcreteFactor.compareTo(expectedConcreteFactor) shouldBe 0
        }
    }

    "binary prefix multiplication returns base-two multiplicative overflow" {
        val (prefix, remainder) = QUEBI * QUEBI

        prefix shouldBe QUEBI
        remainder.compareTo(BINARY_EXPONENT_BASE.pow(QUEBI.factor.intValueExact())) shouldBe 0
    }

    "binary prefix division returns base-two multiplicative overflow" {
        val (prefix, remainder) = BASE / QUEBI

        prefix shouldBe BASE
        remainder.compareTo(
            BigDecimal.ONE.divide(BINARY_EXPONENT_BASE.pow(QUEBI.factor.intValueExact()), KisuConfig.precision)
        ) shouldBe 0
    }

    "linear prefix multiplication multiplies factors and returns overflow" {
        checkAll(Times.generator, Times.generator) { a, b ->
            val (prefix, remainder) = a * b

            val expectedFactor = a.factor * b.factor
            val expectedPrefix = a.find(expectedFactor)
            val expectedRemainder = expectedFactor.divide(expectedPrefix.factor, KisuConfig.precision)

            prefix shouldBe expectedPrefix
            remainder.compareTo(expectedRemainder) shouldBe 0
        }
    }

    "linear prefix division divides factors and returns overflow" {
        checkAll(Times.generator, Times.generator) { a, b ->
            val (prefix, remainder) = a / b

            val expectedFactor = a.factor.divide(b.factor, KisuConfig.precision)
            val expectedPrefix = a.find(expectedFactor)
            val expectedRemainder = expectedFactor.divide(expectedPrefix.factor, KisuConfig.precision)

            prefix shouldBe expectedPrefix
            remainder.compareTo(expectedRemainder) shouldBe 0
        }
    }
})

private val BINARY_EXPONENT_BASE = BigDecimal.valueOf(2)

private const val ALLOWED_TOLERANCE = 1e-14
