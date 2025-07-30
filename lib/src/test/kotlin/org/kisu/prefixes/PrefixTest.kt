package org.kisu.prefixes

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.Prefixes
import org.kisu.test.matchers.plusOrMinus
import java.math.BigDecimal
import java.math.MathContext

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

    "prefix multiplication preserves factor identity" {
        checkAll(Metrics.generator, Metrics.generator) { a, b ->
            val (prefix, remainder) = a * b

            val expected = a.factor * b.factor
            val actual = prefix.factor * remainder

            actual.compareTo(expected) shouldBe 0
        }
    }

    "prefix division preserves factor identity" {
        checkAll(Metrics.generator, Metrics.generator) { a, b ->
            val (prefix, remainder) = a / b

            val expected = a.factor.divide(b.factor, MathContext.UNLIMITED)
            val actual = prefix.factor * remainder

            actual.compareTo(expected) shouldBe 0
        }
    }
})

private const val ALLOWED_TOLERANCE = 1e-14
