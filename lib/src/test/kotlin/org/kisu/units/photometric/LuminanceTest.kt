package org.kisu.units.photometric

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.LuminousIntensity
import org.kisu.units.builders.candelasPerSquareMetre
import org.kisu.units.photometric.Luminance.Companion.CandelaPerSquareMetre
import org.kisu.units.special.Area

class LuminanceTest : StringSpec({
    "creates a Luminance" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().candelasPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CandelaPerSquareMetre(magnitude.builder().metric)
                symbol shouldBe CandelaPerSquareMetre().toString()
            }
        }
    }

    "creates a base Luminance" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.candelasPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CandelaPerSquareMetre()
                symbol shouldBe CandelaPerSquareMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a Luminance by an Area returns a LuminousIntensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Luminance(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = LuminousIntensity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
