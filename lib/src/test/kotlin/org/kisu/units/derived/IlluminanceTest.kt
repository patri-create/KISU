package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.Scalar
import org.kisu.units.builders.lux

class IlluminanceTest : StringSpec({
    "creates an Illuminance" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().lux.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, Illuminance.SYMBOL)
                symbol shouldBe Illuminance.SYMBOL
            }
        }
    }

    "creates a base Illuminance" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.lux.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, Illuminance.SYMBOL)
                symbol shouldBe Illuminance.SYMBOL
            }
        }
    }
})
