package org.kisu.units.thermodynamics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Mass
import org.kisu.units.base.Temperature
import org.kisu.units.builders.joulesPerKilogramKelvin
import org.kisu.units.mechanics.SpecificEnergy
import org.kisu.units.thermodynamics.SpecificHeatCapacity.Companion.JoulePerKilogramKelvin

class SpecificHeatCapacityTest : StringSpec({
    "creates a SpecificHeatCapacity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerKilogramKelvin.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKilogramKelvin(magnitude.builder().metric)
                symbol shouldBe JoulePerKilogramKelvin().toString()
            }
        }
    }

    "creates a base SpecificHeatCapacity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.joulesPerKilogramKelvin.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKilogramKelvin()
                symbol shouldBe JoulePerKilogramKelvin().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a SpecificHeatCapacity by a Mass returns a HeatCapacity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SpecificHeatCapacity(leftMagnitude, leftPrefix)
            val right = Mass(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = HeatCapacity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a SpecificHeatCapacity by a Temperature returns a SpecificEnergy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SpecificHeatCapacity(leftMagnitude, leftPrefix)
            val right = Temperature(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = SpecificEnergy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
