package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.LuminousIntensity
import org.kisu.units.builders.steradians
import org.kisu.units.mechanics.RadiantIntensity

class SolidAngleTest : StringSpec({
    "creates a SolidAngle" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().steradians.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Steradian(magnitude.builder().metric)
                symbol shouldBe Steradian.UNIT.toString()
            }
        }
    }

    "creates a base SolidAngle" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.steradians.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Steradian()
                symbol shouldBe Steradian.UNIT.toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a SolidAngle by a LuminousIntensity returns a LuminousFlux" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SolidAngle(leftMagnitude, leftPrefix)
            val right = LuminousIntensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = LuminousFlux(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a SolidAngle by a RadiantIntensity returns a Power" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SolidAngle(leftMagnitude, leftPrefix)
            val right = RadiantIntensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Power(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
