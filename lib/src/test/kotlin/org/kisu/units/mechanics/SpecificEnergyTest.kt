package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.joulesPerKilogram
import org.kisu.units.mechanics.SpecificEnergy.Companion.JoulePerKilogram

class SpecificEnergyTest : StringSpec({
    "creates a SpecificEnergy" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKilogram(magnitude.builder().metric)
                symbol shouldBe JoulePerKilogram().toString()
            }
        }
    }

    "creates a base SpecificEnergy" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.joulesPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKilogram()
                symbol shouldBe JoulePerKilogram().toString()
            }
        }
    }
})
