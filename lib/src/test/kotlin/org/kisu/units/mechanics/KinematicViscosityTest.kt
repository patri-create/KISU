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
import org.kisu.units.builders.squareMetresPerSecond
import org.kisu.units.electromagnetic.ElectronMobility
import org.kisu.units.mechanics.KinematicViscosity.Companion.SquareMetrePerSecond
import org.kisu.units.special.Area
import org.kisu.units.special.ElectricPotential

class KinematicViscosityTest : StringSpec({
    "creates a LinearChargeDensity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().squareMetresPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SquareMetrePerSecond(magnitude.builder().metric)
                symbol shouldBe SquareMetrePerSecond().toString()
            }
        }
    }

    "creates a base LinearChargeDensity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.squareMetresPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SquareMetrePerSecond()
                symbol shouldBe SquareMetrePerSecond().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a KinematicViscosity by an ElectronMobility returns an ElectricPotential" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = KinematicViscosity(leftMagnitude, leftPrefix)
            val right = ElectronMobility(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = ElectricPotential(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a KinematicViscosity by an ElectricPotential returns an ElectronMobility" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = KinematicViscosity(leftMagnitude, leftPrefix)
            val right = ElectricPotential(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = ElectronMobility(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a KinematicViscosity by a Time returns an Area" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = KinematicViscosity(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Area(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
