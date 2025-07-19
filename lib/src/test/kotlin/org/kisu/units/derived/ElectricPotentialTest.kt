package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.volts

class ElectricPotentialTest : StringSpec({
    "creates an ElectricPotential" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().volts.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe "${magnitude.builder().metric.symbol}${ElectricPotential.SYMBOL}"
                symbol shouldBe ElectricPotential.SYMBOL
            }
        }
    }

    "creates a base ElectricPotential" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.volts.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ElectricPotential.SYMBOL
                symbol shouldBe ElectricPotential.SYMBOL
            }
        }
    }
})
