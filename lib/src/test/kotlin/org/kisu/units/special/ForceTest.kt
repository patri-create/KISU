package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.newtons

class ForceTest : StringSpec({
    "creates a Force" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().newtons.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Newton(magnitude.builder().metric)
                symbol shouldBe Newton.UNIT.toString()
            }
        }
    }

    "creates a base Force" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.newtons.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Newton()
                symbol shouldBe Newton.UNIT.toString()
            }
        }
    }
})
