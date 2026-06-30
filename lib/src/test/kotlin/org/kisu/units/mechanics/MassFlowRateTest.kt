package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Mass
import org.kisu.units.base.Time
import org.kisu.units.builders.gramsPerSecond
import org.kisu.units.mechanics.MassFlowRate.Companion.KilogramPerSecond

class MassFlowRateTest : StringSpec({
    "creates a MassFlowRate" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerSecond(magnitude.builder().metric)
                symbol shouldBe KilogramPerSecond().toString()
            }
        }
    }

    "creates a base MassFlowRate" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.gramsPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerSecond()
                symbol shouldBe KilogramPerSecond().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a MassFlowRate by a Time returns a Mass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MassFlowRate(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Mass(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
