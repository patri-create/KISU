package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.base.Current
import org.kisu.units.builders.amperesRadian
import org.kisu.units.electromagnetic.MagnetomotiveForce.Companion.AmpereRadian
import org.kisu.units.special.PlaneAngle

class MagnetomotiveForceTest : StringSpec({
    "creates a MagnetomotiveForce" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().amperesRadian.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe AmpereRadian(magnitude.builder().metric)
                symbol shouldBe AmpereRadian().toString()
            }
        }
    }

    "creates a base MagnetomotiveForce" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.amperesRadian.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe AmpereRadian()
                symbol shouldBe AmpereRadian().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a MagnetomotiveForce by a Current returns a PlaneAngle" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MagnetomotiveForce(leftMagnitude, leftPrefix)
            val right = Current(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = PlaneAngle(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a MagnetomotiveForce by a PlaneAngle returns a Current" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MagnetomotiveForce(leftMagnitude, leftPrefix)
            val right = PlaneAngle(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Current(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
