package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.grams
import org.kisu.units.chemistry.Molality
import org.kisu.units.chemistry.MolarMass
import org.kisu.units.electromagnetic.Exposure
import org.kisu.units.kinematics.linear.Acceleration
import org.kisu.units.kinematics.linear.Speed
import org.kisu.units.mechanics.AngularMomentum
import org.kisu.units.mechanics.AreaDensity
import org.kisu.units.mechanics.Density
import org.kisu.units.mechanics.LinearMassDensity
import org.kisu.units.mechanics.MassFlowRate
import org.kisu.units.mechanics.MomentOfInertia
import org.kisu.units.mechanics.Momentum
import org.kisu.units.mechanics.SpecificAngularMomentum
import org.kisu.units.mechanics.SpecificEnergy
import org.kisu.units.mechanics.SpecificVolume
import org.kisu.units.special.Area
import org.kisu.units.special.ElectricCharge
import org.kisu.units.special.Energy
import org.kisu.units.special.Force
import org.kisu.units.special.Volume
import org.kisu.units.thermodynamics.HeatCapacity
import org.kisu.units.thermodynamics.SpecificHeatCapacity

class MassTest : StringSpec({
    "creates mass" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().grams.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Kilogram(magnitude.builder().metric to Magnitude.ONE)
                symbol shouldBe Kilogram().toString()
            }
        }
    }

    "creates a base Mass" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.grams.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Kilogram(Metric.BASE to Magnitude.ONE)
                symbol shouldBe Kilogram().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a Mass by an Amount returns a MolarMass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = Amount(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = MolarMass(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Mass by a Length returns a LinearMassDensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = LinearMassDensity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Mass by a Time returns a MassFlowRate" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = MassFlowRate(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Mass by a MolarMass returns an Amount" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = MolarMass(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Amount(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Mass by an AreaDensity returns an Area" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = AreaDensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Area(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Mass by a Density returns a Volume" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = Density(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Volume(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Mass by a LinearMassDensity returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = LinearMassDensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Mass by a MassFlowRate returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = MassFlowRate(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Mass by an Area returns an AreaDensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = AreaDensity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Mass by a Volume returns a Density" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = Volume(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Density(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a Mass by a Molality returns an Amount" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = Molality(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Amount(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Mass by an Exposure returns an ElectricCharge" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = Exposure(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricCharge(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Mass by an Acceleration returns a Force" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = Acceleration(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Force(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Mass by a Speed returns a Momentum" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = Speed(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Momentum(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Mass by a SpecificAngularMomentum returns an AngularMomentum" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = SpecificAngularMomentum(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = AngularMomentum(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Mass by a SpecificEnergy returns an Energy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = SpecificEnergy(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Energy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Mass by a SpecificVolume returns a Volume" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = SpecificVolume(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Volume(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Mass by an Area returns a MomentOfInertia" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MomentOfInertia(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Mass by a SpecificHeatCapacity returns a HeatCapacity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Mass(leftMagnitude, leftPrefix)
            val right = SpecificHeatCapacity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = HeatCapacity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
