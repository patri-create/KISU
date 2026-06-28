package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Length
import org.kisu.units.builders.webersPerMetre
import org.kisu.units.electromagnetic.MagneticVectorPotential.Companion.WeberPerMetre
import org.kisu.units.special.MagneticFlux

class MagneticVectorPotentialTest : StringSpec({
    "creates a MagneticVectorPotential" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().webersPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WeberPerMetre(magnitude.builder().metric)
                symbol shouldBe WeberPerMetre().toString()
            }
        }
    }

    "creates a base MagneticVectorPotential" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.webersPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WeberPerMetre()
                symbol shouldBe WeberPerMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a MagneticVectorPotential by a Length returns a MagneticFlux" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MagneticVectorPotential(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MagneticFlux(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
