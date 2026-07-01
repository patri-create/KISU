package org.kisu.units.thermodynamics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Temperature
import org.kisu.units.builders.kelvinsPerWatt
import org.kisu.units.special.Power
import org.kisu.units.thermodynamics.ThermalResistance.Companion.KelvinPerWatt

class ThermalResistanceTest : StringSpec({
    "creates a ThermalResistance" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().kelvinsPerWatt.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KelvinPerWatt(magnitude.builder().metric)
                symbol shouldBe KelvinPerWatt().toString()
            }
        }
    }

    "creates a base ThermalResistance" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.kelvinsPerWatt.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KelvinPerWatt()
                symbol shouldBe KelvinPerWatt().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a ThermalResistance by a Power returns a Temperature" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ThermalResistance(leftMagnitude, leftPrefix)
            val right = Power(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Temperature(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
