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
import org.kisu.units.base.Mass
import org.kisu.units.builders.gramsPerMetre
import org.kisu.units.mechanics.LinearMassDensity.Companion.KilogramPerMetre

class LinearMassDensityTest : StringSpec({
    "creates a LinearMassDensity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerMetre(magnitude.builder().metric)
                symbol shouldBe KilogramPerMetre().toString()
            }
        }
    }

    "creates a base LinearMassDensity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.gramsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerMetre()
                symbol shouldBe KilogramPerMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a LinearMassDensity by a Length returns a Mass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = LinearMassDensity(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Mass(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
