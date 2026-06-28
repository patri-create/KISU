package org.kisu.units.thermodynamics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.builders.reciprocalKelvins

class ThermalExpansionCoefficientTest : StringSpec({
    "creates a ThermalExpansionCoefficient" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().reciprocalKelvins.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ReciprocalKelvin(magnitude.builder().metric)
                symbol shouldBe ReciprocalKelvin().toString()
            }
        }
    }

    "creates a base ThermalExpansionCoefficient" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.reciprocalKelvins.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ReciprocalKelvin()
                symbol shouldBe ReciprocalKelvin().toString()
            }
        }
    }

    "converts to Temperature" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.reciprocalKelvins.temperature.thermalExpansionCoefficient.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ReciprocalKelvin()
                symbol shouldBe ReciprocalKelvin().toString()
            }
        }
    }
})
