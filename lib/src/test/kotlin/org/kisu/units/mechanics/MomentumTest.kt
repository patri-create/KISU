package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.newtonsSecond
import org.kisu.units.mechanics.Momentum.Companion.NewtonSecond

class MomentumTest : StringSpec({
    "creates a Momentum" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().newtonsSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe NewtonSecond(magnitude.builder().metric)
                symbol shouldBe NewtonSecond().toString()
            }
        }
    }

    "creates a base Momentum" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.newtonsSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe NewtonSecond()
                symbol shouldBe NewtonSecond().toString()
            }
        }
    }
})
