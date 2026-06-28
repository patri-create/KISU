package org.kisu.units.chemistry

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.siemensSquareMetrePerMole
import org.kisu.units.chemistry.MolarConductivity.Companion.SiemensSquareMetrePerMole
import org.kisu.units.electromagnetic.ElectricConductivity

class MolarConductivityTest : StringSpec({
    "creates a MolarConductivity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().siemensSquareMetrePerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SiemensSquareMetrePerMole(magnitude.builder().metric)
                symbol shouldBe SiemensSquareMetrePerMole().toString()
            }
        }
    }

    "creates a base MolarConductivity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.siemensSquareMetrePerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SiemensSquareMetrePerMole()
                symbol shouldBe SiemensSquareMetrePerMole().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a MolarConductivity by a Molarity returns an ElectricConductivity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MolarConductivity(leftMagnitude, leftPrefix)
            val right = Molarity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricConductivity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
