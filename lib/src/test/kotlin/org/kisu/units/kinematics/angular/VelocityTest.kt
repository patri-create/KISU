package org.kisu.units.kinematics.angular

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
import org.kisu.units.builders.radiansPerSecond
import org.kisu.units.kinematics.angular.Velocity.Companion.RadianPerSecond
import org.kisu.units.special.PlaneAngle

class VelocityTest : StringSpec({
    "creates an angular Velocity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().radiansPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecond(magnitude.builder().metric)
                symbol shouldBe RadianPerSecond().toString()
            }
        }
    }

    "creates a base angular Velocity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.radiansPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecond()
                symbol shouldBe RadianPerSecond().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a Velocity by a Time returns an Acceleration" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Velocity(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Acceleration(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Velocity by an Acceleration returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Velocity(leftMagnitude, leftPrefix)
            val right = Acceleration(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a Velocity by a Time returns a PlaneAngle" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Velocity(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = PlaneAngle(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
