package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Time
import org.kisu.units.builders.lux
import org.kisu.units.photometric.Exposure

class IlluminanceTest : StringSpec({
    "creates an Illuminance" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().lux.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Lux(magnitude.builder().metric)
                symbol shouldBe Lux.UNIT.toString()
            }
        }
    }

    "creates a base Illuminance" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.lux.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Lux()
                symbol shouldBe Lux.UNIT.toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying an Illuminance by a Time returns an Exposure" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Illuminance(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Exposure(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Illuminance by an Area returns a LuminousFlux" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Illuminance(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = LuminousFlux(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
