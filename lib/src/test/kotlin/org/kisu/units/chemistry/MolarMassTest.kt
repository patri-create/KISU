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
import org.kisu.units.builders.gramsPerMole
import org.kisu.units.chemistry.MolarMass.Companion.KilogramPerMole

class MolarMassTest : StringSpec({
    "creates a MolarMass" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsPerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerMole(magnitude.builder().metric)
                symbol shouldBe KilogramPerMole().toString()
            }
        }
    }

    "creates a base MolarMass" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.gramsPerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerMole()
                symbol shouldBe KilogramPerMole().toString()
            }
        }
    }

    "converts to Molality" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.gramsPerMole.molality.molarMass.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerMole()
                symbol shouldBe KilogramPerMole().toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "multiplying a MolarMass by an Amount returns a Mass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MolarMass(leftMagnitude, leftPrefix)
            val right = Amount(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Mass(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
