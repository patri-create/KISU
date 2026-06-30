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
import org.kisu.units.builders.reciprocalPascals
import org.kisu.units.special.Area
import org.kisu.units.special.Force

class CompressibilityTest : StringSpec({
    "creates a Compressibility" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().reciprocalPascals.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ReciprocalPascal(magnitude.builder().metric)
                symbol shouldBe ReciprocalPascal().toString()
            }
        }
    }

    "creates a base Compressibility" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.reciprocalPascals.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ReciprocalPascal()
                symbol shouldBe ReciprocalPascal().toString()
            }
        }
    }

    "converts to Pressure" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.reciprocalPascals.pressure.compressibility.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ReciprocalPascal()
                symbol shouldBe ReciprocalPascal().toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "multiplying a Compressibility by a Force returns an Area" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Compressibility(leftMagnitude, leftPrefix)
            val right = Force(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Area(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
