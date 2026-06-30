package org.kisu.units.special

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
import org.kisu.units.base.Time
import org.kisu.units.builders.radians
import org.kisu.units.electromagnetic.MagnetomotiveForce
import org.kisu.units.kinematics.angular.Velocity

class PlaneAngleTest : StringSpec({

    "creates a PlaneAngle" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().radians.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Radian(magnitude.builder().metric)
                symbol shouldBe Radian.UNIT.toString()
            }
        }
    }

    "creates a base PlaneAngle" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.radians.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Radian()
                symbol shouldBe Radian.UNIT.toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a PlaneAngle by a Time returns a Velocity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = PlaneAngle(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Velocity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a PlaneAngle by a Velocity returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = PlaneAngle(leftMagnitude, leftPrefix)
            val right = Velocity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a PlaneAngle by a Current returns a MagnetomotiveForce" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = PlaneAngle(leftMagnitude, leftPrefix)
            val right = Current(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MagnetomotiveForce(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
