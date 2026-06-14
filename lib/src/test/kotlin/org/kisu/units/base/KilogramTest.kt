package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeZero
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.prefixes.times
import org.kisu.test.generators.Metrics
import org.kisu.units.scales.ExponentialScale
import java.math.BigDecimal

class KilogramTest : StringSpec({
    "kilogram corrects the metric prefix" {
        checkAll(Metrics.generator) { prefix ->
            val scale = ExponentialScale<Metric>(BigDecimal.TEN)

            val (newPrefix, remainder) = prefix * Metric.KILO
            val kilos = Kilogram(prefix)

            kilos.symbol shouldBe "${newPrefix}g"
            (kilos.factor).compareTo(scale.factor(newPrefix) * remainder).shouldBeZero()
        }
    }

    "kilogram outputs the correct canonical form" {
        checkAll(Metrics.generator) { prefix ->
            val kilos = Kilogram(prefix).canonical

            kilos.canonical.toString() shouldBe "kg"
        }
    }
})
