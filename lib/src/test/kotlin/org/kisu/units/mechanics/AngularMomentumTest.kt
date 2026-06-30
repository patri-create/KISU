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
import org.kisu.units.builders.newtonsMetreSecond
import org.kisu.units.mechanics.AngularMomentum.Companion.NewtonMeterSecond

class AngularMomentumTest : StringSpec({
    "creates an AngularMomentum" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().newtonsMetreSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe NewtonMeterSecond(magnitude.builder().metric)
                symbol shouldBe NewtonMeterSecond().toString()
            }
        }
    }

    "creates a base AngularMomentum" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.newtonsMetreSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe NewtonMeterSecond()
                symbol shouldBe NewtonMeterSecond().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing an AngularMomentum by a Length returns a Momentum" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = AngularMomentum(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Momentum(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an AngularMomentum by a Mass returns a SpecificAngularMomentum" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = AngularMomentum(leftMagnitude, leftPrefix)
            val right = Mass(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = SpecificAngularMomentum(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an AngularMomentum by a Momentum returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = AngularMomentum(leftMagnitude, leftPrefix)
            val right = Momentum(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an AngularMomentum by a SpecificAngularMomentum returns a Mass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = AngularMomentum(leftMagnitude, leftPrefix)
            val right = SpecificAngularMomentum(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Mass(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
