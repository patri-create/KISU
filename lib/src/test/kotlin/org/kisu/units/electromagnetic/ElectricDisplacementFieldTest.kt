package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.coulombsPerSquareMetre
import org.kisu.units.electromagnetic.ElectricDisplacementField.Companion.CoulombPerSquareMetre
import org.kisu.units.special.Area
import org.kisu.units.special.ElectricCharge

class ElectricDisplacementFieldTest : StringSpec({
    "creates an ElectricDisplacementField" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().coulombsPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerSquareMetre(magnitude.builder().metric)
                symbol shouldBe CoulombPerSquareMetre().toString()
            }
        }
    }

    "creates a base ElectricDisplacementField" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.coulombsPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerSquareMetre()
                symbol shouldBe CoulombPerSquareMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying an ElectricDisplacementField by an Area returns an ElectricCharge" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricDisplacementField(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricCharge(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
