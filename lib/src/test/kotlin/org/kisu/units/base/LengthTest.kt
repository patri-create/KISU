package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.long
import io.kotest.property.checkAll
import org.kisu.magnitude
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.builders.metres
import org.kisu.units.electromagnetic.ElectricConductivity
import org.kisu.units.electromagnetic.ElectricFieldStrength
import org.kisu.units.electromagnetic.LinearChargeDensity
import org.kisu.units.electromagnetic.MagneticMoment
import org.kisu.units.electromagnetic.MagneticPermittivity
import org.kisu.units.electromagnetic.MagneticRigidity
import org.kisu.units.electromagnetic.MagneticSusceptibility
import org.kisu.units.electromagnetic.MagneticVectorPotential
import org.kisu.units.electromagnetic.Magnetization
import org.kisu.units.electromagnetic.Permittivity
import org.kisu.units.electromagnetic.Resistivity
import org.kisu.units.kinematics.linear.Speed
import org.kisu.units.mechanics.AngularMomentum
import org.kisu.units.mechanics.FuelEfficiency
import org.kisu.units.mechanics.LinearMassDensity
import org.kisu.units.mechanics.Momentum
import org.kisu.units.mechanics.Radiance
import org.kisu.units.mechanics.RadiantIntensity
import org.kisu.units.mechanics.SpectralIntensity
import org.kisu.units.mechanics.SpectralPower
import org.kisu.units.mechanics.SpectralRadiance
import org.kisu.units.mechanics.SurfaceTension
import org.kisu.units.special.Area
import org.kisu.units.special.Capacitance
import org.kisu.units.special.Conductance
import org.kisu.units.special.ElectricCharge
import org.kisu.units.special.ElectricPotential
import org.kisu.units.special.Energy
import org.kisu.units.special.Force
import org.kisu.units.special.Inductance
import org.kisu.units.special.MagneticFlux
import org.kisu.units.special.MagneticFluxDensity
import org.kisu.units.special.Power
import org.kisu.units.special.Resistance
import org.kisu.units.special.Volume
import org.kisu.units.thermodynamics.TemperatureGradient

class LengthTest : StringSpec({
    "creates Length" {
        checkAll(Arb.long().filter { it != 0L }, MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metres.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.magnitude
                expression shouldBe Metre(magnitude.builder().metric)
                symbol shouldBe Metre.UNIT.toString()
            }
        }
    }

    "creates a base Length" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.metres.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Metre()
                symbol shouldBe Metre.UNIT.toString()
            }
        }
    }

    "converts to WaveNumber" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.metres.waveNumber.wavelength.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Metre()
                symbol shouldBe Metre.UNIT.toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "dividing a Length by a Time returns a Speed" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Speed(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Length by a MagneticSusceptibility returns an Inductance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = MagneticSusceptibility(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Inductance(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Length by a Speed returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = Speed(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Length by a FuelEfficiency returns a Volume" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = FuelEfficiency(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Volume(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Length by an Inductance returns a MagneticSusceptibility" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = Inductance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = MagneticSusceptibility(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Length by a Volume returns a FuelEfficiency" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = Volume(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = FuelEfficiency(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a Length by a Length returns an Area" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Area(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by an ElectricConductivity returns a Conductance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = ElectricConductivity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Conductance(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by an ElectricFieldStrength returns an ElectricPotential" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = ElectricFieldStrength(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricPotential(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a LinearChargeDensity returns an ElectricCharge" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = LinearChargeDensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricCharge(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a MagneticPermittivity returns an Inductance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = MagneticPermittivity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Inductance(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a MagneticVectorPotential returns a MagneticFlux" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = MagneticVectorPotential(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MagneticFlux(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a Magnetization returns a Current" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = Magnetization(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Current(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a Permittivity returns a Capacitance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = Permittivity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Capacitance(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a LinearMassDensity returns a Mass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = LinearMassDensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Mass(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a Momentum returns an AngularMomentum" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = Momentum(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = AngularMomentum(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a SpectralIntensity returns a RadiantIntensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = SpectralIntensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = RadiantIntensity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a SpectralPower returns a Power" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = SpectralPower(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Power(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a SpectralRadiance returns a Radiance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = SpectralRadiance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Radiance(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a SurfaceTension returns a Force" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = SurfaceTension(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Force(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by an Area returns a Volume" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Volume(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a Force returns an Energy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = Force(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Energy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a MagneticFlux returns a MagneticMoment" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = MagneticFlux(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MagneticMoment(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a MagneticFluxDensity returns a MagneticRigidity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = MagneticFluxDensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MagneticRigidity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a Resistance returns a Resistivity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = Resistance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Resistivity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Length by a TemperatureGradient returns a Temperature" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Length(leftMagnitude, leftPrefix)
            val right = TemperatureGradient(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Temperature(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
