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
import org.kisu.units.builders.metresPerCubicMetre
import org.kisu.units.mechanics.FuelEfficiency.Companion.MetrePerCubicMetre
import org.kisu.units.special.Volume

class FuelEfficiencyTest : StringSpec({
    "creates a FuelEfficiency" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metresPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerCubicMetre(magnitude.builder().metric)
                symbol shouldBe MetrePerCubicMetre().toString()
            }
        }
    }

    "creates a base FuelEfficiency" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.metresPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerCubicMetre()
                symbol shouldBe MetrePerCubicMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a FuelEfficiency by a Volume returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = FuelEfficiency(leftMagnitude, leftPrefix)
            val right = Volume(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
