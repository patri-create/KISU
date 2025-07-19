package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.celsius

class CelsiusTemperatureTest : StringSpec({
    "creates a CelsiusTemperature" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().celsius.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe "${magnitude.builder().metric.symbol}${Celsius.SYMBOL}"
                symbol shouldBe Celsius.SYMBOL
            }
        }
    }

    "creates a base CatalyticActivity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.celsius.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Celsius.SYMBOL
                symbol shouldBe Celsius.SYMBOL
            }
        }
    }
})
