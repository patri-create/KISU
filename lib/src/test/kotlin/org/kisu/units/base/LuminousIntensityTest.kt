package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveLong
import io.kotest.property.checkAll
import org.kisu.magnitude
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.candelas
import org.kisu.units.photometric.Luminance
import org.kisu.units.special.Area
import org.kisu.units.special.LuminousFlux
import org.kisu.units.special.SolidAngle

class LuminousIntensityTest : StringSpec({
    "creates LuminousIntensity" {
        checkAll(Arb.positiveLong(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().candelas.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.magnitude
                expression shouldBe Candela(magnitude.builder().metric)
                symbol shouldBe Candela.UNIT.toString()
            }
        }
    }

    "creates a base LuminousIntensity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.candelas.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Candela()
                symbol shouldBe Candela.UNIT.toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a LuminousIntensity by a Luminance returns an Area" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = LuminousIntensity(leftMagnitude, leftPrefix)
            val right = Luminance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Area(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a LuminousIntensity by an Area returns a Luminance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = LuminousIntensity(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Luminance(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a LuminousIntensity by a SolidAngle returns a LuminousFlux" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = LuminousIntensity(leftMagnitude, leftPrefix)
            val right = SolidAngle(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = LuminousFlux(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
