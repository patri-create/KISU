package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.joulesSecond
import org.kisu.units.mechanics.Action.Companion.JouleSecond

class ActionTest : StringSpec({
    "creates an Action" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JouleSecond(magnitude.builder().metric)
                symbol shouldBe JouleSecond().toString()
            }
        }
    }

    "creates a base Action" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.joulesSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JouleSecond()
                symbol shouldBe JouleSecond().toString()
            }
        }
    }
})
