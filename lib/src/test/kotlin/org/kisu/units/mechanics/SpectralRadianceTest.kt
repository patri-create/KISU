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
import org.kisu.units.builders.wattsPerSteradianCubicMetre
import org.kisu.units.mechanics.SpectralRadiance.Companion.WattPerSteradianCubicMetre

class SpectralRadianceTest : StringSpec({
    "creates a SpectralRadiance" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerSteradianCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSteradianCubicMetre(magnitude.builder().metric)
                symbol shouldBe WattPerSteradianCubicMetre().toString()
            }
        }
    }

    "creates a base SpectralRadiance" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.wattsPerSteradianCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSteradianCubicMetre()
                symbol shouldBe WattPerSteradianCubicMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a SpectralRadiance by a Length returns a Radiance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SpectralRadiance(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Radiance(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
