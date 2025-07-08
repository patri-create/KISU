package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.pascals

class PressureTest : StringSpec({
    "creates a Pressure" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().pascals.should { (amount, prefix, symbol) ->
                amount shouldBe magnitude
                prefix shouldBe magnitude.builder().metric
                symbol shouldBe Pressure.SYMBOL
            }
        }
    }

    "creates a base Pressure" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.pascals.should { (amount, prefix, symbol) ->
                amount shouldBe magnitude
                prefix shouldBe Metric.BASE
                symbol shouldBe Pressure.SYMBOL
            }
        }
    }
})
