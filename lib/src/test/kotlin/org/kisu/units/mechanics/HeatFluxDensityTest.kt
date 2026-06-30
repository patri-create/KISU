package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.builders.wattsPerSquareMetre
import org.kisu.units.mechanics.HeatFluxDensity.Companion.WattPerSquareMetre
import org.kisu.units.special.Area
import org.kisu.units.special.Power
import org.kisu.units.thermodynamics.TemperatureGradient
import org.kisu.units.thermodynamics.ThermalConductivity

class HeatFluxDensityTest : StringSpec({
    "creates a HeatFluxDensity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSquareMetre(magnitude.builder().metric)
                symbol shouldBe WattPerSquareMetre().toString()
            }
        }
    }

    "creates a base HeatFluxDensity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.wattsPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSquareMetre()
                symbol shouldBe WattPerSquareMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a HeatFluxDensity by a TemperatureGradient returns a ThermalConductivity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = HeatFluxDensity(leftMagnitude, leftPrefix)
            val right = TemperatureGradient(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = ThermalConductivity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a HeatFluxDensity by a ThermalConductivity returns a TemperatureGradient" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = HeatFluxDensity(leftMagnitude, leftPrefix)
            val right = ThermalConductivity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = TemperatureGradient(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a HeatFluxDensity by an Area returns a Power" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = HeatFluxDensity(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Power(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
