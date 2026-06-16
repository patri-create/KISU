package org.kisu.prefixes

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.prefixes.primitives.ExponentialEnumSystem
import org.kisu.prefixes.primitives.System
import org.kisu.test.generators.ExponentialPrefixes
import org.kisu.test.generators.Metrics

class ExponentialPrefixTest : StringSpec({
    "orders prefixes by power" {
        checkAll(ExponentialPrefixes.generator, ExponentialPrefixes.generator) { left, right ->
            left.compareTo(right) shouldBe left.power.compareTo(right.power)
            left.sortWith(right).toList().shouldBeSorted()
        }
    }

    "adds powers and returns exponent overflow" {
        checkAll(Metrics.generator, Metrics.generator) { left, right ->
            val (prefix, overflow) = left + right
            val expectedPower = Math.addExact(left.power, right.power)

            prefix shouldBe left.find(expectedPower.toBigDecimal())
            overflow shouldBe Math.subtractExact(expectedPower, prefix.power)
        }
    }

    "subtracts powers and returns exponent overflow" {
        checkAll(Metrics.generator, Metrics.generator) { left, right ->
            val (prefix, overflow) = left - right
            val expectedPower = Math.subtractExact(left.power, right.power)

            prefix shouldBe left.find(expectedPower.toBigDecimal())
            overflow shouldBe Math.subtractExact(expectedPower, prefix.power)
        }
    }

    "fails when addition overflows the power coordinate" {
        shouldThrow<ArithmeticException> {
            ExtremePowerPrefix.MAX + ExtremePowerPrefix.MAX
        }
    }

    "fails when subtraction overflows the power coordinate" {
        shouldThrow<ArithmeticException> {
            ExtremePowerPrefix.MIN - ExtremePowerPrefix.MAX
        }
    }
})

@Suppress("DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
private enum class ExtremePowerPrefix(
    override val power: Int,
    override val symbol: String,
) : ExponentialPrefix<ExtremePowerPrefix>,
    System<ExtremePowerPrefix> by ExponentialEnumSystem(ExtremePowerPrefix::class) {
    MIN(Int.MIN_VALUE, "min"),
    BASE(0, ""),
    MAX(Int.MAX_VALUE, "max"),
}
