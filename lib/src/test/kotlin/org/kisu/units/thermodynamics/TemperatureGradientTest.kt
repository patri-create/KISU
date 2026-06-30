package org.kisu.units.thermodynamics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Length
import org.kisu.units.base.Temperature
import org.kisu.units.builders.kelvinsPerMetre
import org.kisu.units.mechanics.HeatFluxDensity
import org.kisu.units.thermodynamics.TemperatureGradient.Companion.KelvinPerMetre

class TemperatureGradientTest : StringSpec({
    "creates a TemperatureGradient" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().kelvinsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KelvinPerMetre(magnitude.builder().metric)
                symbol shouldBe KelvinPerMetre().toString()
            }
        }
    }

    "creates a base TemperatureGradient" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.kelvinsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KelvinPerMetre()
                symbol shouldBe KelvinPerMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a TemperatureGradient by a Length returns a Temperature" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = TemperatureGradient(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Temperature(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a TemperatureGradient by a ThermalConductivity returns a HeatFluxDensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = TemperatureGradient(leftMagnitude, leftPrefix)
            val right = ThermalConductivity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = HeatFluxDensity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
