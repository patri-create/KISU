package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.pascalsSecond
import org.kisu.units.mechanics.DynamicViscosity.Companion.PascalSecond

class DynamicViscosityTest : StringSpec({
    "creates a DynamicViscosity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().pascalsSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe PascalSecond(magnitude.builder().metric)
                symbol shouldBe PascalSecond().toString()
            }
        }
    }

    "creates a base DynamicViscosity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.pascalsSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe PascalSecond()
                symbol shouldBe PascalSecond().toString()
            }
        }
    }
})
