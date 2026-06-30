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
import org.kisu.units.builders.metresPerSecondFifth
import org.kisu.units.kinematics.linear.Crackle.Companion.MetrePerSecondFifth

class CrackleTest : StringSpec({
    "creates a linear Crackle" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metresPerSecondFifth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondFifth(magnitude.builder().metric)
                symbol shouldBe MetrePerSecondFifth().toString()
            }
        }
    }

    "creates a base linear Crackle" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.metresPerSecondFifth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondFifth()
                symbol shouldBe MetrePerSecondFifth().toString()
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
