package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.base.Mass
import org.kisu.units.base.Temperature
import org.kisu.units.builders.joulesPerKilogram
import org.kisu.units.mechanics.SpecificEnergy.Companion.JoulePerKilogram
import org.kisu.units.special.Energy
import org.kisu.units.thermodynamics.SpecificHeatCapacity

class SpecificEnergyTest : StringSpec({
    "creates a SpecificEnergy" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKilogram(magnitude.builder().metric)
                symbol shouldBe JoulePerKilogram().toString()
            }
        }
    }

    "creates a base SpecificEnergy" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.joulesPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKilogram()
                symbol shouldBe JoulePerKilogram().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a SpecificEnergy by a Temperature returns a SpecificHeatCapacity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SpecificEnergy(leftMagnitude, leftPrefix)
            val right = Temperature(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = SpecificHeatCapacity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a SpecificEnergy by a SpecificHeatCapacity returns a Temperature" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SpecificEnergy(leftMagnitude, leftPrefix)
            val right = SpecificHeatCapacity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Temperature(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a SpecificEnergy by a Mass returns an Energy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SpecificEnergy(leftMagnitude, leftPrefix)
            val right = Mass(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Energy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
