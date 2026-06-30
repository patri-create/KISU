package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.joulesPerCubicMetre
import org.kisu.units.mechanics.EnergyDensity.Companion.JoulePerCubicMetre
import org.kisu.units.special.Energy
import org.kisu.units.special.Volume

class EnergyDensityTest : StringSpec({
    "creates an EnergyDensity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerCubicMetre(magnitude.builder().metric)
                symbol shouldBe JoulePerCubicMetre().toString()
            }
        }
    }

    "creates a base EnergyDensity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.joulesPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerCubicMetre()
                symbol shouldBe JoulePerCubicMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying an EnergyDensity by a Volume returns an Energy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = EnergyDensity(leftMagnitude, leftPrefix)
            val right = Volume(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Energy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
