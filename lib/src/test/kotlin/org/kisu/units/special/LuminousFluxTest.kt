package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.base.LuminousIntensity
import org.kisu.units.base.Time
import org.kisu.units.builders.lumens
import org.kisu.units.photometric.Efficacy
import org.kisu.units.photometric.LuminousEnergy

class LuminousFluxTest : StringSpec({
    "creates a LuminousFlux" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().lumens.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Lumen(magnitude.builder().metric)
                symbol shouldBe Lumen.UNIT.toString()
            }
        }
    }

    "creates a base LuminousFlux" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.lumens.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Lumen()
                symbol shouldBe Lumen.UNIT.toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a LuminousFlux by a LuminousIntensity returns a SolidAngle" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = LuminousFlux(leftMagnitude, leftPrefix)
            val right = LuminousIntensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = SolidAngle(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a LuminousFlux by an Efficacy returns a Power" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = LuminousFlux(leftMagnitude, leftPrefix)
            val right = Efficacy(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Power(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a LuminousFlux by an Area returns an Illuminance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = LuminousFlux(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Illuminance(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a LuminousFlux by an Illuminance returns an Area" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = LuminousFlux(leftMagnitude, leftPrefix)
            val right = Illuminance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Area(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a LuminousFlux by a Power returns an Efficacy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = LuminousFlux(leftMagnitude, leftPrefix)
            val right = Power(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Efficacy(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a LuminousFlux by a SolidAngle returns a LuminousIntensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = LuminousFlux(leftMagnitude, leftPrefix)
            val right = SolidAngle(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = LuminousIntensity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a LuminousFlux by a Time returns a LuminousEnergy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = LuminousFlux(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = LuminousEnergy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
