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
import org.kisu.units.builders.voltsPerMetre
import org.kisu.units.electromagnetic.ElectricFieldStrength.Companion.VoltPerMetre
import org.kisu.units.special.ElectricPotential

class ElectricFieldStrengthTest : StringSpec({
    "creates an ElectricFieldStrength" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().voltsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe VoltPerMetre(magnitude.builder().metric)
                symbol shouldBe VoltPerMetre().toString()
            }
        }
    }

    "creates a base ElectricFieldStrength" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.voltsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe VoltPerMetre()
                symbol shouldBe VoltPerMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying an ElectricFieldStrength by a Length returns an ElectricPotential" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricFieldStrength(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricPotential(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
