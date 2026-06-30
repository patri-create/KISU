package org.kisu.units.special

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
import org.kisu.units.builders.pascals
import org.kisu.units.mechanics.DynamicViscosity

class PressureTest : StringSpec({
    "creates a Pressure" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().pascals.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Pascal(magnitude.builder().metric)
                symbol shouldBe Pascal.UNIT.toString()
            }
        }
    }

    "creates a base Pressure" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.pascals.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Pascal()
                symbol shouldBe Pascal.UNIT.toString()
            }
        }
    }

    "converts to Compressibility" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.pascals.compressibility.pressure.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Pascal()
                symbol shouldBe Pascal.UNIT.toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "multiplying a Pressure by a Time returns a DynamicViscosity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Pressure(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = DynamicViscosity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Pressure by an Area returns a Force" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Pressure(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Force(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
