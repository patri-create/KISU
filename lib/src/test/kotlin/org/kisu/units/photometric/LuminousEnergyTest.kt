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
import org.kisu.units.builders.lumensSecond
import org.kisu.units.photometric.LuminousEnergy.Companion.LumenSecond
import org.kisu.units.special.LuminousFlux

class LuminousEnergyTest : StringSpec({
    "creates a LuminousEnergy" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().lumensSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe LumenSecond(magnitude.builder().metric)
                symbol shouldBe LumenSecond().toString()
            }
        }
    }

    "creates a base LuminousEnergy" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.lumensSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe LumenSecond()
                symbol shouldBe LumenSecond().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a LuminousEnergy by a Time returns a LuminousFlux" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = LuminousEnergy(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = LuminousFlux(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a LuminousEnergy by a LuminousFlux returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = LuminousEnergy(leftMagnitude, leftPrefix)
            val right = LuminousFlux(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
