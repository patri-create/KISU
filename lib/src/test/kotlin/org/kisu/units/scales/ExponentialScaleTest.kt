package org.kisu.units.scales

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.prefixes.Binary
import org.kisu.prefixes.Metric
import org.kisu.test.generators.Binaries
import org.kisu.test.generators.Metrics
import java.math.BigDecimal
import kotlin.math.pow

class ExponentialScaleTest : StringSpec({
    val metricBase = BigDecimal.TEN
    val binaryBase = BigDecimal.TWO

    "calculates exponential factors" {
        checkAll(Metrics.generator) { prefix ->
            val expected = metricBase.toDouble()
                .pow(prefix.factor.toDouble())
                .toBigDecimal()
                .stripTrailingZeros()

            ExponentialScale<Metric>(metricBase).factor(prefix).compareTo(expected) shouldBe 0
        }
    }

    "works for other bases" {
        checkAll(Binaries.generator) { prefix ->
            val expected = binaryBase.pow(prefix.factor.toInt())

            ExponentialScale<Binary>(binaryBase).factor(prefix).compareTo(expected) shouldBe 0
        }
    }
})
