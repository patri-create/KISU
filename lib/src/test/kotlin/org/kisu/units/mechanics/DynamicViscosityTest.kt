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
import org.kisu.units.builders.pascalsSecond
import org.kisu.units.mechanics.DynamicViscosity.Companion.PascalSecond
import org.kisu.units.special.Pressure

class DynamicViscosityTest : StringSpec({
    "creates a DynamicViscosity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().pascalsSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe PascalSecond(magnitude.builder().metric)
                symbol shouldBe PascalSecond().toString()
            }
        }
    }

    "creates a base DynamicViscosity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.pascalsSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe PascalSecond()
                symbol shouldBe PascalSecond().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a DynamicViscosity by a Time returns a Pressure" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = DynamicViscosity(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Pressure(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a DynamicViscosity by a Pressure returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = DynamicViscosity(leftMagnitude, leftPrefix)
            val right = Pressure(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
