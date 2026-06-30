package org.kisu.units.thermodynamics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.wattsPerMetreKelvin
import org.kisu.units.mechanics.HeatFluxDensity
import org.kisu.units.thermodynamics.ThermalConductivity.Companion.WattPerMetreKelvin

class ThermalConductivityTest : StringSpec({
    "creates a ThermalConductivity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerMetreKelvin.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerMetreKelvin(magnitude.builder().metric)
                symbol shouldBe WattPerMetreKelvin().toString()
            }
        }
    }

    "creates a base ThermalConductivity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.wattsPerMetreKelvin.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerMetreKelvin()
                symbol shouldBe WattPerMetreKelvin().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a ThermalConductivity by a TemperatureGradient returns a HeatFluxDensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ThermalConductivity(leftMagnitude, leftPrefix)
            val right = TemperatureGradient(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = HeatFluxDensity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
