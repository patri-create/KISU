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
import org.kisu.units.builders.wattsPerSteradianSquareMetre
import org.kisu.units.mechanics.Radiance.Companion.WattPerSteradianSquareMetre
import org.kisu.units.special.Area

class RadianceTest : StringSpec({
    "creates a Radiance" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerSteradianSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSteradianSquareMetre(magnitude.builder().metric)
                symbol shouldBe WattPerSteradianSquareMetre().toString()
            }
        }
    }

    "creates a base Radiance" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.wattsPerSteradianSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSteradianSquareMetre()
                symbol shouldBe WattPerSteradianSquareMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a Radiance by a Length returns a SpectralRadiance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Radiance(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = SpectralRadiance(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Radiance by a SpectralRadiance returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Radiance(leftMagnitude, leftPrefix)
            val right = SpectralRadiance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a Radiance by an Area returns a RadiantIntensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Radiance(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = RadiantIntensity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
