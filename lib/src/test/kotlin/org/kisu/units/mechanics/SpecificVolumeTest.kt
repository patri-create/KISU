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
import org.kisu.units.base.Mass
import org.kisu.units.builders.cubicMetresPerKilogram
import org.kisu.units.mechanics.SpecificVolume.Companion.CubicMetrePerKilogram
import org.kisu.units.special.Volume

class SpecificVolumeTest : StringSpec({
    "creates a SpecificVolume" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().cubicMetresPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerKilogram(magnitude.builder().metric)
                symbol shouldBe CubicMetrePerKilogram().toString()
            }
        }
    }

    "creates a base SpecificVolume" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.cubicMetresPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerKilogram()
                symbol shouldBe CubicMetrePerKilogram().toString()
            }
        }
    }

    "converts to Density" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.cubicMetresPerKilogram.density.specificVolume.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerKilogram()
                symbol shouldBe CubicMetrePerKilogram().toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "multiplying a SpecificVolume by a Mass returns a Volume" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SpecificVolume(leftMagnitude, leftPrefix)
            val right = Mass(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Volume(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
