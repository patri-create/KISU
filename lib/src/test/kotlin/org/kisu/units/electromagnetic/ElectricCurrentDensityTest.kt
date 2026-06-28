package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Current
import org.kisu.units.builders.amperesPerSquareMetre
import org.kisu.units.electromagnetic.ElectricCurrentDensity.Companion.AmperePerSquareMetre
import org.kisu.units.special.Area

class ElectricCurrentDensityTest : StringSpec({
    "creates an ElectricCurrentDensity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().amperesPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe AmperePerSquareMetre(magnitude.builder().metric)
                symbol shouldBe AmperePerSquareMetre().toString()
            }
        }
    }

    "creates a base ElectricCurrentDensity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.amperesPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe AmperePerSquareMetre()
                symbol shouldBe AmperePerSquareMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying an ElectricCurrentDensity by an Area returns a Current" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricCurrentDensity(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Current(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
