package org.kisu.units.chemistry

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.base.Amount
import org.kisu.units.base.Mass
import org.kisu.units.builders.molesPerKilogram
import org.kisu.units.chemistry.Molality.Companion.MolPerKilogram

class MolalityTest : StringSpec({
    "creates a Molality" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().molesPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MolPerKilogram(magnitude.builder().metric)
                symbol shouldBe MolPerKilogram().toString()
            }
        }
    }

    "creates a base Molality" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.molesPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MolPerKilogram()
                symbol shouldBe MolPerKilogram().toString()
            }
        }
    }

    "converts to MolarMass" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.molesPerKilogram.molarMass.molality.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MolPerKilogram()
                symbol shouldBe MolPerKilogram().toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "multiplying a Molality by a Mass returns an Amount" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Molality(leftMagnitude, leftPrefix)
            val right = Mass(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Amount(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
