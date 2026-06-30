package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.wattsPerCubicMetre
import org.kisu.units.mechanics.SpectralIrradiance.Companion.WattPerCubicMetre
import org.kisu.units.special.Area

class SpectralIrradianceTest : StringSpec({
    "creates a SpectralIrradiance" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerCubicMetre(magnitude.builder().metric)
                symbol shouldBe WattPerCubicMetre().toString()
            }
        }
    }

    "creates a base SpectralIrradiance" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.wattsPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerCubicMetre()
                symbol shouldBe WattPerCubicMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a SpectralIrradiance by an Area returns a SpectralPower" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SpectralIrradiance(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = SpectralPower(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
