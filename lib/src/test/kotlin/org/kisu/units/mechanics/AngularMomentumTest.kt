package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.newtonsMetreSecond
import org.kisu.units.mechanics.AngularMomentum.Companion.NewtonMeterSecond

class AngularMomentumTest : StringSpec({
    "creates an AngularMomentum" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().newtonsMetreSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe NewtonMeterSecond(magnitude.builder().metric)
                symbol shouldBe NewtonMeterSecond().toString()
            }
        }
    }

    "creates a base AngularMomentum" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.newtonsMetreSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe NewtonMeterSecond()
                symbol shouldBe NewtonMeterSecond().toString()
            }
        }
    }
})
