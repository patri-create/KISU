package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.builders.kelvins

class TemperatureTest : StringSpec({
    "creates Temperature" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().kelvins.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Kelvin(magnitude.builder().metric)
                symbol shouldBe Kelvin.UNIT.toString()
            }
        }
    }

    "creates a base Temperature" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.kelvins.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Kelvin()
                symbol shouldBe Kelvin.UNIT.toString()
            }
        }
    }

    "converts to ThermalExpansionCoefficient" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.kelvins.thermalExpansionCoefficient.temperature.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Kelvin()
                symbol shouldBe Kelvin.UNIT.toString()
            }
        }
    }
})
