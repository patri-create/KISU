package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Length
import org.kisu.units.builders.wattsPerSteradianMetre
import org.kisu.units.mechanics.SpectralIntensity.Companion.WattPerSteradianMetre

class SpectralIntensityTest : StringSpec({
    "creates a SpectralIntensity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerSteradianMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSteradianMetre(magnitude.builder().metric)
                symbol shouldBe WattPerSteradianMetre().toString()
            }
        }
    }

    "creates a base SpectralIntensity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.wattsPerSteradianMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSteradianMetre()
                symbol shouldBe WattPerSteradianMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a SpectralIntensity by a Length returns a RadiantIntensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SpectralIntensity(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = RadiantIntensity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
