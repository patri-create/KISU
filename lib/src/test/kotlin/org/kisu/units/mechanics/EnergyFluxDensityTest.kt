package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.joulesPerSquareMetreSecond
import org.kisu.units.mechanics.EnergyFluxDensity.Companion.JoulePerSquareMetreSecond

class EnergyFluxDensityTest : StringSpec({
    "creates an EnergyFluxDensity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerSquareMetreSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerSquareMetreSecond(magnitude.builder().metric)
                symbol shouldBe JoulePerSquareMetreSecond().toString()
            }
        }
    }

    "creates a base EnergyFluxDensity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.joulesPerSquareMetreSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerSquareMetreSecond()
                symbol shouldBe JoulePerSquareMetreSecond().toString()
            }
        }
    }
})
