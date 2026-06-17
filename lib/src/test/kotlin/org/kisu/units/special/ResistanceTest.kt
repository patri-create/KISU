package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.ohms

class ResistanceTest : StringSpec({
    "creates a Resistance" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().ohms.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Ohm(magnitude.builder().metric)
                symbol shouldBe Ohm.UNIT.toString()
            }
        }
    }

    "creates a base Resistance" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.ohms.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Ohm()
                symbol shouldBe Ohm.UNIT.toString()
            }
        }
    }
})
