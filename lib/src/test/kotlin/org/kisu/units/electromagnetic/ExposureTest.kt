package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Mass
import org.kisu.units.builders.coulombsPerKilogram
import org.kisu.units.electromagnetic.Exposure.Companion.CoulombPerKilogram
import org.kisu.units.special.ElectricCharge

class ExposureTest : StringSpec({
    "creates an Exposure" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().coulombsPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerKilogram(magnitude.builder().metric)
                symbol shouldBe CoulombPerKilogram().toString()
            }
        }
    }

    "creates a base Exposure" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.coulombsPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerKilogram()
                symbol shouldBe CoulombPerKilogram().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying an Exposure by a Mass returns an ElectricCharge" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Exposure(leftMagnitude, leftPrefix)
            val right = Mass(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricCharge(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
