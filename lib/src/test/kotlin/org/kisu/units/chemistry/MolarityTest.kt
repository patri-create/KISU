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
import org.kisu.units.builders.molesPerCubicMetre
import org.kisu.units.chemistry.Molarity.Companion.MolePerCubicMetre
import org.kisu.units.electromagnetic.ElectricConductivity
import org.kisu.units.special.Volume

class MolarityTest : StringSpec({
    "creates a Molarity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().molesPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MolePerCubicMetre(magnitude.builder().metric)
                symbol shouldBe MolePerCubicMetre().toString()
            }
        }
    }

    "creates a base Molarity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.molesPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MolePerCubicMetre()
                symbol shouldBe MolePerCubicMetre().toString()
            }
        }
    }

    "converts to MolarVolume" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.molesPerCubicMetre.molarVolume.molarity.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MolePerCubicMetre()
                symbol shouldBe MolePerCubicMetre().toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "multiplying a Molarity by a MolarConductivity returns an ElectricConductivity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Molarity(leftMagnitude, leftPrefix)
            val right = MolarConductivity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricConductivity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Molarity by a Volume returns an Amount" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Molarity(leftMagnitude, leftPrefix)
            val right = Volume(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Amount(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
