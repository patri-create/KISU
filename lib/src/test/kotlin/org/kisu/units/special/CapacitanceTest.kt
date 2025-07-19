package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.Scalar
import org.kisu.units.builders.farads

class CapacitanceTest : StringSpec({
    "creates a Capacitance" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().farads.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, Capacitance.SYMBOL)
                symbol shouldBe Capacitance.SYMBOL
            }
        }
    }

    "creates a base Capacitance" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.farads.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, Capacitance.SYMBOL)
                symbol shouldBe Capacitance.SYMBOL
            }
        }
    }
})
