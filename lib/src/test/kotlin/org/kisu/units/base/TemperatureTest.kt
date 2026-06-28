package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.builders.kelvins
import org.kisu.units.chemistry.MolarEnergy
import org.kisu.units.chemistry.MolarHeatCapacity
import org.kisu.units.mechanics.SpecificEnergy
import org.kisu.units.special.Energy
import org.kisu.units.special.Power
import org.kisu.units.thermodynamics.HeatCapacity
import org.kisu.units.thermodynamics.SpecificHeatCapacity
import org.kisu.units.thermodynamics.TemperatureGradient
import org.kisu.units.thermodynamics.ThermalResistance

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
    // Dimension-aware arithmetic properties
    "dividing a Temperature by a Length returns a TemperatureGradient" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Temperature(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = TemperatureGradient(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Temperature by a Power returns a ThermalResistance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Temperature(leftMagnitude, leftPrefix)
            val right = Power(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = ThermalResistance(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Temperature by a TemperatureGradient returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Temperature(leftMagnitude, leftPrefix)
            val right = TemperatureGradient(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Temperature by a ThermalResistance returns a Power" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Temperature(leftMagnitude, leftPrefix)
            val right = ThermalResistance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Power(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a Temperature by a MolarHeatCapacity returns a MolarEnergy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Temperature(leftMagnitude, leftPrefix)
            val right = MolarHeatCapacity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MolarEnergy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Temperature by a HeatCapacity returns an Energy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Temperature(leftMagnitude, leftPrefix)
            val right = HeatCapacity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Energy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Temperature by a SpecificHeatCapacity returns a SpecificEnergy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Temperature(leftMagnitude, leftPrefix)
            val right = SpecificHeatCapacity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = SpecificEnergy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
