package org.kisu.units.photometric

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
import org.kisu.units.builders.luxSecond
import org.kisu.units.photometric.Exposure.Companion.LuxSecond
import org.kisu.units.special.Illuminance

class ExposureTest : StringSpec({
    "creates an Exposure" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().luxSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe LuxSecond(magnitude.builder().metric)
                symbol shouldBe LuxSecond().toString()
            }
        }
    }

    "creates a base Exposure" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.luxSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe LuxSecond()
                symbol shouldBe LuxSecond().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing an Exposure by a Time returns an Illuminance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Exposure(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Illuminance(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an Exposure by an Illuminance returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Exposure(leftMagnitude, leftPrefix)
            val right = Illuminance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
