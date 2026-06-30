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
import org.kisu.units.builders.wattsPerSteradian
import org.kisu.units.mechanics.RadiantIntensity.Companion.WattPerSteradian
import org.kisu.units.special.Area
import org.kisu.units.special.Power
import org.kisu.units.special.SolidAngle

class RadiantIntensityTest : StringSpec({
    "creates a RadiantIntensity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerSteradian.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSteradian(magnitude.builder().metric)
                symbol shouldBe WattPerSteradian().toString()
            }
        }
    }

    "creates a base RadiantIntensity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.wattsPerSteradian.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSteradian()
                symbol shouldBe WattPerSteradian().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a RadiantIntensity by a Length returns a SpectralIntensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = RadiantIntensity(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = SpectralIntensity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a RadiantIntensity by a Radiance returns an Area" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = RadiantIntensity(leftMagnitude, leftPrefix)
            val right = Radiance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Area(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a RadiantIntensity by a SpectralIntensity returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = RadiantIntensity(leftMagnitude, leftPrefix)
            val right = SpectralIntensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a RadiantIntensity by an Area returns a Radiance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = RadiantIntensity(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Radiance(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a RadiantIntensity by a SolidAngle returns a Power" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = RadiantIntensity(leftMagnitude, leftPrefix)
            val right = SolidAngle(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Power(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
