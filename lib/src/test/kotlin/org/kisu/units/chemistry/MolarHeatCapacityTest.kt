package org.kisu.units.chemistry

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Amount
import org.kisu.units.base.Temperature
import org.kisu.units.builders.joulesPerKelvinMole
import org.kisu.units.chemistry.MolarHeatCapacity.Companion.JoulePerKelvinMole
import org.kisu.units.thermodynamics.HeatCapacity

class MolarHeatCapacityTest : StringSpec({
    "creates a MolarHeatCapacity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerKelvinMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKelvinMole(magnitude.builder().metric)
                symbol shouldBe JoulePerKelvinMole().toString()
            }
        }
    }

    "creates a base MolarHeatCapacity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.joulesPerKelvinMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKelvinMole()
                symbol shouldBe JoulePerKelvinMole().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a MolarHeatCapacity by an Amount returns a HeatCapacity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MolarHeatCapacity(leftMagnitude, leftPrefix)
            val right = Amount(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = HeatCapacity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a MolarHeatCapacity by a Temperature returns a MolarEnergy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MolarHeatCapacity(leftMagnitude, leftPrefix)
            val right = Temperature(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MolarEnergy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
