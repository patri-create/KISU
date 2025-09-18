package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.gramsPerSecond
import org.kisu.units.mechanics.MassFlowRate.Companion.KilogramPerSecond

class MassFlowRateTest : StringSpec({
    "creates a MassFlowRate" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerSecond(magnitude.builder().metric)
                symbol shouldBe KilogramPerSecond().toString()
            }
        }
    }

    "creates a base MassFlowRate" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.gramsPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerSecond()
                symbol shouldBe KilogramPerSecond().toString()
            }
        }
    }
})
