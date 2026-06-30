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
import org.kisu.units.base.Time
import org.kisu.units.builders.joulesPerSquareMetre
import org.kisu.units.mechanics.RadiantExposure.Companion.JoulePerSquareMetre
import org.kisu.units.special.Area
import org.kisu.units.special.Energy

class RadiantExposureTest : StringSpec({
    "creates a RadiantExposure" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerSquareMetre(magnitude.builder().metric)
                symbol shouldBe JoulePerSquareMetre().toString()
            }
        }
    }

    "creates a base RadiantExposure" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.joulesPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerSquareMetre()
                symbol shouldBe JoulePerSquareMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a RadiantExposure by a Time returns an EnergyFluxDensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = RadiantExposure(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = EnergyFluxDensity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a RadiantExposure by an EnergyFluxDensity returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = RadiantExposure(leftMagnitude, leftPrefix)
            val right = EnergyFluxDensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a RadiantExposure by an Area returns an Energy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = RadiantExposure(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Energy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
