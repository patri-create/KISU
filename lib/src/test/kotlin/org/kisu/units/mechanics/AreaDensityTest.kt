package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Mass
import org.kisu.units.builders.gramsPerSquareMetre
import org.kisu.units.mechanics.AreaDensity.Companion.KilogramPerSquareMetre
import org.kisu.units.special.Area

class AreaDensityTest : StringSpec({
    "creates an AreaDensity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerSquareMetre(magnitude.builder().metric)
                symbol shouldBe KilogramPerSquareMetre().toString()
            }
        }
    }

    "creates a base AreaDensity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.gramsPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerSquareMetre()
                symbol shouldBe KilogramPerSquareMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying an AreaDensity by an Area returns a Mass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = AreaDensity(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Mass(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
