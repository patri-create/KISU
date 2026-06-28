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
import org.kisu.units.builders.joulesPerMole
import org.kisu.units.chemistry.MolarEnergy.Companion.JoulePerMole
import org.kisu.units.special.Energy

class MolarEnergyTest : StringSpec({
    "creates a MolarEnergy" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerMole(magnitude.builder().metric)
                symbol shouldBe JoulePerMole().toString()
            }
        }
    }

    "creates a base MolarEnergy" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.joulesPerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerMole()
                symbol shouldBe JoulePerMole().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a MolarEnergy by a Temperature returns a MolarHeatCapacity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MolarEnergy(leftMagnitude, leftPrefix)
            val right = Temperature(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = MolarHeatCapacity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a MolarEnergy by a MolarHeatCapacity returns a Temperature" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MolarEnergy(leftMagnitude, leftPrefix)
            val right = MolarHeatCapacity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Temperature(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a MolarEnergy by an Amount returns an Energy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MolarEnergy(leftMagnitude, leftPrefix)
            val right = Amount(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Energy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
