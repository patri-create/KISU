package org.kisu.units.kinematics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Time
import org.kisu.units.builders.gramsMetreSecondCubed
import org.kisu.units.kinematics.Yank.Companion.KilogramMetrePerSecondCubed
import org.kisu.units.special.Force

class YankTest : StringSpec({
    "creates a Yank" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsMetreSecondCubed.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramMetrePerSecondCubed(magnitude.builder().metric)
                symbol shouldBe KilogramMetrePerSecondCubed().toString()
            }
        }
    }

    "creates a base Yank" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.gramsMetreSecondCubed.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramMetrePerSecondCubed()
                symbol shouldBe KilogramMetrePerSecondCubed().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a Yank by a Time returns a Force" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Yank(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Force(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
