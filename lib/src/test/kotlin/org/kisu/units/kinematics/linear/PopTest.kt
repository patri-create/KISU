package org.kisu.units.kinematics.linear

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Time
import org.kisu.units.builders.metresPerSecondSixth
import org.kisu.units.kinematics.linear.Pop.Companion.MetrePerSecondSixth

class PopTest : StringSpec({
    "creates a linear Pop" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metresPerSecondSixth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondSixth(magnitude.builder().metric)
                symbol shouldBe MetrePerSecondSixth().toString()
            }
        }
    }

    "creates a base linear Pop" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.metresPerSecondSixth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondSixth()
                symbol shouldBe MetrePerSecondSixth().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a Pop by a Time returns a Crackle" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Pop(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Crackle(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
