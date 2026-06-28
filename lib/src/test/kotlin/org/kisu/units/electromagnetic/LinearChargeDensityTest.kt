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
import org.kisu.units.builders.coulombsPerMetre
import org.kisu.units.electromagnetic.LinearChargeDensity.Companion.CoulombPerMetre
import org.kisu.units.special.ElectricCharge

class LinearChargeDensityTest : StringSpec({
    "creates a LinearChargeDensity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().coulombsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerMetre(magnitude.builder().metric)
                symbol shouldBe CoulombPerMetre().toString()
            }
        }
    }

    "creates a base LinearChargeDensity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.coulombsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerMetre()
                symbol shouldBe CoulombPerMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a LinearChargeDensity by a Length returns an ElectricCharge" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = LinearChargeDensity(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricCharge(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
