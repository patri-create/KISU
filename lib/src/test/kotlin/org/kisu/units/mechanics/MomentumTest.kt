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
import org.kisu.units.base.Length
import org.kisu.units.base.Mass
import org.kisu.units.base.Time
import org.kisu.units.builders.newtonsSecond
import org.kisu.units.kinematics.linear.Speed
import org.kisu.units.mechanics.Momentum.Companion.NewtonSecond
import org.kisu.units.special.Force

class MomentumTest : StringSpec({
    "creates a Momentum" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().newtonsSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe NewtonSecond(magnitude.builder().metric)
                symbol shouldBe NewtonSecond().toString()
            }
        }
    }

    "creates a base Momentum" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.newtonsSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe NewtonSecond()
                symbol shouldBe NewtonSecond().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a Momentum by a Mass returns a Speed" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Momentum(leftMagnitude, leftPrefix)
            val right = Mass(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Speed(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Momentum by a Time returns a Force" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Momentum(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Force(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Momentum by a Speed returns a Mass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Momentum(leftMagnitude, leftPrefix)
            val right = Speed(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Mass(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Momentum by a Force returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Momentum(leftMagnitude, leftPrefix)
            val right = Force(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a Momentum by a Length returns an AngularMomentum" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Momentum(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = AngularMomentum(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
