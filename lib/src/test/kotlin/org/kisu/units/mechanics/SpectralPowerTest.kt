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
import org.kisu.units.builders.wattsPerMetre
import org.kisu.units.mechanics.SpectralPower.Companion.WattPerMetre
import org.kisu.units.special.Area
import org.kisu.units.special.Power

class SpectralPowerTest : StringSpec({
    "creates a SpectralPower" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerMetre(magnitude.builder().metric)
                symbol shouldBe WattPerMetre().toString()
            }
        }
    }

    "creates a base SpectralPower" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.wattsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerMetre()
                symbol shouldBe WattPerMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a SpectralPower by a SpectralIrradiance returns an Area" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SpectralPower(leftMagnitude, leftPrefix)
            val right = SpectralIrradiance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Area(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a SpectralPower by an Area returns a SpectralIrradiance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SpectralPower(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = SpectralIrradiance(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a SpectralPower by a Length returns a Power" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SpectralPower(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Power(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
