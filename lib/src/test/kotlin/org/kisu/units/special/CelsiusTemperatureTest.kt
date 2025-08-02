package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.celsius
import org.kisu.units.representation.Scalar

class CelsiusTemperatureTest : StringSpec({
    "creates a CelsiusTemperature" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().celsius.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, Celsius.SYMBOL)
                symbol shouldBe Celsius.SYMBOL
            }
        }
    }

    "creates a base CatalyticActivity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.celsius.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, Celsius.SYMBOL)
                symbol shouldBe Celsius.SYMBOL
            }
        }
    }
})
