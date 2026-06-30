package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Time
import org.kisu.units.builders.joulesPerSquareMetreSecond
import org.kisu.units.mechanics.EnergyFluxDensity.Companion.JoulePerSquareMetreSecond

class EnergyFluxDensityTest : StringSpec({
    "creates an EnergyFluxDensity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerSquareMetreSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerSquareMetreSecond(magnitude.builder().metric)
                symbol shouldBe JoulePerSquareMetreSecond().toString()
            }
        }
    }

    "creates a base EnergyFluxDensity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.joulesPerSquareMetreSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerSquareMetreSecond()
                symbol shouldBe JoulePerSquareMetreSecond().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying an EnergyFluxDensity by a Time returns a RadiantExposure" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = EnergyFluxDensity(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = RadiantExposure(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
