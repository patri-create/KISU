package org.kisu.units.scales

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.prefixes.Time
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.Times
import java.math.BigDecimal

class LinearScaleTest : StringSpec({
    "calculates a linear factor" {
        checkAll(Times.generator) { prefix ->
            LinearScale<Time>().factor(BigDecimal.ONE, prefix) shouldBe prefix.factor
        }
    }
})
