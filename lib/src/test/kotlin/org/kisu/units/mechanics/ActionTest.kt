package org.kisu.units.mechanics

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
import org.kisu.units.builders.joulesSecond
import org.kisu.units.mechanics.Action.Companion.JouleSecond
import org.kisu.units.special.Energy

class ActionTest : StringSpec({
    "creates an Action" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JouleSecond(magnitude.builder().metric)
                symbol shouldBe JouleSecond().toString()
            }
        }
    }

    "creates a base Action" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.joulesSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JouleSecond()
                symbol shouldBe JouleSecond().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing an Action by a Time returns an Energy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Action(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Energy(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an Action by an Energy returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Action(leftMagnitude, leftPrefix)
            val right = Energy(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
