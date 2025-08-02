package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.watts
import org.kisu.units.representation.Scalar

class PowerTest : StringSpec({
    "creates a Power" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().watts.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, Power.SYMBOL)
                symbol shouldBe Power.SYMBOL
            }
        }
    }

    "creates a base Power" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.watts.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, Power.SYMBOL)
                symbol shouldBe Power.SYMBOL
            }
        }
    }
})
