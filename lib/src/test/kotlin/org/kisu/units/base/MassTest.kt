package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.grams

class MassTest : StringSpec({
    "creates mass" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().grams.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Kilogram(magnitude.builder().metric to Magnitude.ONE)
                symbol shouldBe Kilogram().toString()
            }
        }
    }

    "creates a base Mass" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.grams.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Kilogram(Metric.BASE to Magnitude.ONE)
                symbol shouldBe Kilogram().toString()
            }
        }
    }
})
