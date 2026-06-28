package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.coulombsPerCubicMetre
import org.kisu.units.electromagnetic.ElectricChargeDensity.Companion.CoulombPerCubicMetre
import org.kisu.units.special.ElectricCharge
import org.kisu.units.special.Volume

class ElectricChargeDensityTest : StringSpec({
    "creates an ElectricChargeDensity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().coulombsPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerCubicMetre(magnitude.builder().metric)
                symbol shouldBe CoulombPerCubicMetre().toString()
            }
        }
    }

    "creates a base ElectricChargeDensity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.coulombsPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerCubicMetre()
                symbol shouldBe CoulombPerCubicMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying an ElectricChargeDensity by a Volume returns an ElectricCharge" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricChargeDensity(leftMagnitude, leftPrefix)
            val right = Volume(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricCharge(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
