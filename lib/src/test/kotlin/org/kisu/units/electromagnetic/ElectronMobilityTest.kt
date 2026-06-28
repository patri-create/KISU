package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.squareMetresPerVoltSecond
import org.kisu.units.electromagnetic.ElectronMobility.Companion.SquareMetrePerVoltSecond
import org.kisu.units.mechanics.KinematicViscosity
import org.kisu.units.special.ElectricPotential

class ElectronMobilityTest : StringSpec({
    "creates an ElectronMobility" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().squareMetresPerVoltSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SquareMetrePerVoltSecond(magnitude.builder().metric)
                symbol shouldBe SquareMetrePerVoltSecond().toString()
            }
        }
    }

    "creates a base ElectronMobility" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.squareMetresPerVoltSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SquareMetrePerVoltSecond()
                symbol shouldBe SquareMetrePerVoltSecond().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying an ElectronMobility by an ElectricPotential returns a KinematicViscosity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectronMobility(leftMagnitude, leftPrefix)
            val right = ElectricPotential(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = KinematicViscosity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
