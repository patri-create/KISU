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
import org.kisu.units.builders.newtonsPerMetre
import org.kisu.units.mechanics.SurfaceTension.Companion.NewtonPerMetre
import org.kisu.units.special.Force

class SurfaceTensionTest : StringSpec({
    "creates a SurfaceTension" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().newtonsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe NewtonPerMetre(magnitude.builder().metric)
                symbol shouldBe NewtonPerMetre().toString()
            }
        }
    }

    "creates a base SurfaceTension" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.newtonsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe NewtonPerMetre()
                symbol shouldBe NewtonPerMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a SurfaceTension by a Length returns a Force" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = SurfaceTension(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Force(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
