package org.kisu.units.thermodynamics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.joulesPerKilogramKelvin

class SpecificHeatCapacityTest : StringSpec({
    "creates a SpecificHeatCapacity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerKilogramKelvin.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKilogramKelvin(magnitude.builder().metric)
                symbol shouldBe JoulePerKilogramKelvin().toString()
            }
        }
    }

    "creates a base SpecificHeatCapacity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.joulesPerKilogramKelvin.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKilogramKelvin()
                symbol shouldBe JoulePerKilogramKelvin().toString()
            }
        }
    }
})
