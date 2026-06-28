package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.builders.pascals

class PressureTest : StringSpec({
    "creates a Pressure" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().pascals.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Pascal(magnitude.builder().metric)
                symbol shouldBe Pascal.UNIT.toString()
            }
        }
    }

    "creates a base Pressure" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.pascals.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Pascal()
                symbol shouldBe Pascal.UNIT.toString()
            }
        }
    }

    "converts to Compressibility" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.pascals.compressibility.pressure.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Pascal()
                symbol shouldBe Pascal.UNIT.toString()
            }
        }
    }
})
