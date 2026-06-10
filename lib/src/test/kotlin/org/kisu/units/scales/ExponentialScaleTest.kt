package org.kisu.units.scales

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.Metrics
import java.math.BigDecimal

class ExponentialScaleTest : StringSpec({
    val scale = ExponentialScale<Metric>()

    "calculates metric factors for a base-ten expression" {
        checkAll(Metrics.generator) { prefix ->
            scale.factor(BigDecimal.TEN, prefix) shouldBe prefix.factor
        }
    }

    "applies the expression base to the metric exponent" {
        scale.factor(BigDecimal("100"), Metric.KILO) shouldBe BigDecimal("1000000")
        scale.factor(BigDecimal("100"), Metric.MILLI) shouldBe BigDecimal("0.000001")
    }
})
