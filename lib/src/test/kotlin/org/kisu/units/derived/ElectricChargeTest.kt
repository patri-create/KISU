package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.coulombs

class ElectricChargeTest : StringSpec({
    "creates an ElectricCharge" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().coulombs.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe "${magnitude.builder().metric.symbol}${ElectricCharge.SYMBOL}"
                symbol shouldBe ElectricCharge.SYMBOL
            }
        }
    }

    "creates a base ElectricCharge" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.coulombs.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ElectricCharge.SYMBOL
                symbol shouldBe ElectricCharge.SYMBOL
            }
        }
    }
})
