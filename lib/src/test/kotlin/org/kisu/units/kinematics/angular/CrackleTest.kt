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
import org.kisu.units.builders.radiansPerSecondFifth
import org.kisu.units.kinematics.angular.Crackle.Companion.RadianPerSecondFifth

class CrackleTest : StringSpec({
    "creates a Crackle" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().radiansPerSecondFifth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecondFifth(magnitude.builder().metric)
                symbol shouldBe RadianPerSecondFifth().toString()
            }
        }
    }

    "creates a base Crackle" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.radiansPerSecondFifth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecondFifth()
                symbol shouldBe RadianPerSecondFifth().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a Crackle by a Time returns a Pop" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Crackle(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Pop(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Crackle by a Pop returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Crackle(leftMagnitude, leftPrefix)
            val right = Pop(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a Crackle by a Time returns a Snap" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Crackle(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Snap(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
