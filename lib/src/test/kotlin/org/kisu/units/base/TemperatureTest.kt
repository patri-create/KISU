package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.Scalar
import org.kisu.units.builders.kelvins

class TemperatureTest : StringSpec({
    "creates Temperature" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().kelvins.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, Temperature.SYMBOL)
                symbol shouldBe Temperature.SYMBOL
            }
        }
    }

    "creates a base Temperature" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.kelvins.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, Temperature.SYMBOL)
                symbol shouldBe Temperature.SYMBOL
            }
        }
    }
})
