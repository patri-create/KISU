package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeZero
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.prefixes.times
import org.kisu.test.generators.Metrics

class KilogramTest : StringSpec({
    "kilogram corrects the metric prefix" {
        checkAll(Metrics.generator) { prefix ->
            val scale = ExponentialAlgebra<Metric>(Magnitude.TEN)

            val (newPrefix, remainder) = scale.multiply(prefix, Metric.KILO)
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
