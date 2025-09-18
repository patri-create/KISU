package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.graysPerSecond
import org.kisu.units.mechanics.AbsorbedDoseRate.Companion.GrayPerSecond

class AbsorbedDoseRateTest : StringSpec({
    "creates an AbsorbedDoseRate" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().graysPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe GrayPerSecond(magnitude.builder().metric)
                symbol shouldBe GrayPerSecond().toString()
            }
        }
    }

    "creates a base AbsorbedDoseRate" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.graysPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe GrayPerSecond()
                symbol shouldBe GrayPerSecond().toString()
            }
        }
    }
})
