package org.kisu.units.scales

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.Metrics
import java.math.BigDecimal

class ExponentialScaleTest : StringSpec({
    val base = BigDecimal.TEN

    "calculates an exponential factor" {
        checkAll(Metrics.generator) { prefix ->
            ExponentialScale<Metric>().factor(base, prefix) shouldBe BigDecimal.ONE.movePointRight(prefix.factor.toInt())
        }
    }
})
