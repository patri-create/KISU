package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Length
import org.kisu.units.builders.teslas
import org.kisu.units.electromagnetic.MagneticRigidity

class MagneticFluxDensityTest : StringSpec({
    "creates a MagneticFluxDensity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().teslas.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Tesla(magnitude.builder().metric)
                symbol shouldBe Tesla.UNIT.toString()
            }
        }
    }

    "creates a base MagneticFluxDensity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.teslas.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Tesla()
                symbol shouldBe Tesla.UNIT.toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a MagneticFluxDensity by a Length returns a MagneticRigidity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MagneticFluxDensity(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MagneticRigidity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a MagneticFluxDensity by an Area returns a MagneticFlux" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MagneticFluxDensity(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MagneticFlux(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
