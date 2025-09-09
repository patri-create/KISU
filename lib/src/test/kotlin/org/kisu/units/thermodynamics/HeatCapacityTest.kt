package org.kisu.units.thermodynamics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.joulesPerKelvin
import org.kisu.units.thermodynamics.HeatCapacity.Companion.JoulePerKelvin

class HeatCapacityTest : StringSpec({
    "creates a HeatCapacity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerKelvin.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKelvin(magnitude.builder().metric)
                symbol shouldBe JoulePerKelvin().toString()
            }
        }
    }

    "creates a base HeatCapacity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.joulesPerKelvin.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKelvin()
                symbol shouldBe JoulePerKelvin().toString()
            }
        }
    }
})
