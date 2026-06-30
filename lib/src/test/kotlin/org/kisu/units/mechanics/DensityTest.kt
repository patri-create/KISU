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
import org.kisu.units.base.Mass
import org.kisu.units.builders.gramsPerCubicMetre
import org.kisu.units.mechanics.Density.Companion.KilogramPerCubicMetre
import org.kisu.units.special.Volume

class DensityTest : StringSpec({
    "creates a Density" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerCubicMetre(magnitude.builder().metric)
                symbol shouldBe KilogramPerCubicMetre().toString()
            }
        }
    }

    "creates a base Density" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.gramsPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerCubicMetre()
                symbol shouldBe KilogramPerCubicMetre().toString()
            }
        }
    }

    "converts to SpecificVolume" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.gramsPerCubicMetre.specificVolume.density.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerCubicMetre()
                symbol shouldBe KilogramPerCubicMetre().toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "multiplying a Density by a Volume returns a Mass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Density(leftMagnitude, leftPrefix)
            val right = Volume(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Mass(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
