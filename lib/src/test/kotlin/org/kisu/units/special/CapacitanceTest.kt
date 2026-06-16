package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.farads

class CapacitanceTest : StringSpec({
    "creates a Capacitance" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().farads.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Farad(magnitude.builder().metric)
                symbol shouldBe Farad.UNIT.toString()
            }
        }
    }

    "creates a base Capacitance" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.farads.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Farad()
                symbol shouldBe Farad.UNIT.toString()
            }
        }
    }
})
