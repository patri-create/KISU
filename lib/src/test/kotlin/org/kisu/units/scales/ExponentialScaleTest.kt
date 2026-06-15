package org.kisu.units.scales

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.KisuConfig
import org.kisu.prefixes.Binary
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.test.generators.Binaries
import org.kisu.test.generators.Metrics
import java.math.BigDecimal

class ExponentialScaleTest : StringSpec({
    val metricBase = BigDecimal.TEN
    val binaryBase = BigDecimal.TWO

    "calculates exponential factors" {
        checkAll(Metrics.generator) { prefix ->
            val power = metricBase.pow(prefix.factor.abs().toInt())
            val expected = if (prefix.factor < BigDecimal.ZERO) {
                BigDecimal.ONE.divide(power, KisuConfig.precision)
            } else {
                power
            }

            ExponentialAlgebra<Metric>(metricBase).factor(prefix).compareTo(expected) shouldBe 0
        }
    }

    "works for other bases" {
        checkAll(Binaries.generator) { prefix ->
            val expected = binaryBase.pow(prefix.factor.toInt())

            ExponentialAlgebra<Binary>(binaryBase).factor(prefix).compareTo(expected) shouldBe 0
        }
    }
})
