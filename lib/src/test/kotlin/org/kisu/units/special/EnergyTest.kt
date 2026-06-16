package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.joules

class EnergyTest : StringSpec({
    "creates an Energy" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joules.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Joule(magnitude.builder().metric)
                symbol shouldBe Joule.UNIT.toString()
            }
        }
    }

    "creates a base Energy" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.joules.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Joule()
                symbol shouldBe Joule.UNIT.toString()
            }
        }
    }
})
