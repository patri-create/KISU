package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.builders.siemens

class ConductanceTest : StringSpec({
    "creates a Conductance" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().siemens.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Siemens(magnitude.builder().metric)
                symbol shouldBe Siemens.UNIT.toString()
            }
        }
    }

    "creates a base Conductance" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.siemens.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Siemens()
                symbol shouldBe Siemens.UNIT.toString()
            }
        }
    }

    "converts to Resistance" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.siemens.resistance.conductance.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Siemens()
                symbol shouldBe Siemens.UNIT.toString()
            }
        }
    }
})
