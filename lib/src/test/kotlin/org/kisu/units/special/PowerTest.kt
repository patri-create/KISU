package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.base.Current
import org.kisu.units.base.Length
import org.kisu.units.base.Temperature
import org.kisu.units.builders.watts
import org.kisu.units.kinematics.linear.Speed
import org.kisu.units.mechanics.HeatFluxDensity
import org.kisu.units.mechanics.RadiantIntensity
import org.kisu.units.mechanics.SpectralPower
import org.kisu.units.photometric.Efficacy
import org.kisu.units.thermodynamics.ThermalResistance

class PowerTest : StringSpec({
    "creates a Power" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().watts.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Watt(magnitude.builder().metric)
                symbol shouldBe Watt.UNIT.toString()
            }
        }
    }

    "creates a base Power" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.watts.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Watt()
                symbol shouldBe Watt.UNIT.toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a Power by a Current returns an ElectricPotential" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Power(leftMagnitude, leftPrefix)
            val right = Current(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = ElectricPotential(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Power by a Length returns a SpectralPower" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Power(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = SpectralPower(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Power by a Speed returns a Force" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Power(leftMagnitude, leftPrefix)
            val right = Speed(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Force(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Power by a HeatFluxDensity returns an Area" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Power(leftMagnitude, leftPrefix)
            val right = HeatFluxDensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Area(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Power by a RadiantIntensity returns a SolidAngle" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Power(leftMagnitude, leftPrefix)
            val right = RadiantIntensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = SolidAngle(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Power by a SpectralPower returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Power(leftMagnitude, leftPrefix)
            val right = SpectralPower(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Power by an Area returns a HeatFluxDensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Power(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = HeatFluxDensity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Power by an ElectricPotential returns a Current" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Power(leftMagnitude, leftPrefix)
            val right = ElectricPotential(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Current(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Power by a Force returns a Speed" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Power(leftMagnitude, leftPrefix)
            val right = Force(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Speed(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Power by a SolidAngle returns a RadiantIntensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Power(leftMagnitude, leftPrefix)
            val right = SolidAngle(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = RadiantIntensity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a Power by an Efficacy returns a LuminousFlux" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Power(leftMagnitude, leftPrefix)
            val right = Efficacy(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = LuminousFlux(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Power by a ThermalResistance returns a Temperature" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Power(leftMagnitude, leftPrefix)
            val right = ThermalResistance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Temperature(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
