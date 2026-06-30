package org.kisu.units.kinematics.linear

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
import org.kisu.units.builders.metresPerSecondCubed
import org.kisu.units.kinematics.linear.Jerk.Companion.MetrePerSecondCubed

class JerkTest : StringSpec({
    "creates a linear Jerk" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metresPerSecondCubed.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondCubed(magnitude.builder().metric)
                symbol shouldBe MetrePerSecondCubed().toString()
            }
        }
    }

    "creates a base linear Jerk" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.metresPerSecondCubed.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondCubed()
                symbol shouldBe MetrePerSecondCubed().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a Jerk by a Time returns a Snap" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Jerk(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Snap(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Jerk by a Snap returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Jerk(leftMagnitude, leftPrefix)
            val right = Snap(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a Jerk by a Time returns an Acceleration" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Jerk(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Acceleration(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
