package org.kisu.units.thermodynamics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.base.Amount
import org.kisu.units.base.Mass
import org.kisu.units.base.Temperature
import org.kisu.units.builders.joulesPerKelvin
import org.kisu.units.chemistry.MolarHeatCapacity
import org.kisu.units.special.Energy
import org.kisu.units.thermodynamics.HeatCapacity.Companion.JoulePerKelvin

class HeatCapacityTest : StringSpec({
    "creates a HeatCapacity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerKelvin.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKelvin(magnitude.builder().metric)
                symbol shouldBe JoulePerKelvin().toString()
            }
        }
    }

    "creates a base HeatCapacity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.joulesPerKelvin.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKelvin()
                symbol shouldBe JoulePerKelvin().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a HeatCapacity by an Amount returns a MolarHeatCapacity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = HeatCapacity(leftMagnitude, leftPrefix)
            val right = Amount(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = MolarHeatCapacity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a HeatCapacity by a Mass returns a SpecificHeatCapacity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = HeatCapacity(leftMagnitude, leftPrefix)
            val right = Mass(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = SpecificHeatCapacity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a HeatCapacity by a MolarHeatCapacity returns an Amount" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = HeatCapacity(leftMagnitude, leftPrefix)
            val right = MolarHeatCapacity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Amount(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a HeatCapacity by a SpecificHeatCapacity returns a Mass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = HeatCapacity(leftMagnitude, leftPrefix)
            val right = SpecificHeatCapacity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Mass(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a HeatCapacity by a Temperature returns an Energy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = HeatCapacity(leftMagnitude, leftPrefix)
            val right = Temperature(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Energy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
