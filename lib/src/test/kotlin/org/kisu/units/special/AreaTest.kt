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
import org.kisu.units.base.LuminousIntensity
import org.kisu.units.base.Mass
import org.kisu.units.base.Time
import org.kisu.units.builders.squareMetres
import org.kisu.units.electromagnetic.ElectricCurrentDensity
import org.kisu.units.electromagnetic.ElectricDisplacementField
import org.kisu.units.electromagnetic.MagneticDipoleMoment
import org.kisu.units.mechanics.AreaDensity
import org.kisu.units.mechanics.Compressibility
import org.kisu.units.mechanics.HeatFluxDensity
import org.kisu.units.mechanics.KinematicViscosity
import org.kisu.units.mechanics.MomentOfInertia
import org.kisu.units.mechanics.Radiance
import org.kisu.units.mechanics.RadiantExposure
import org.kisu.units.mechanics.RadiantIntensity
import org.kisu.units.mechanics.SpectralIrradiance
import org.kisu.units.mechanics.SpectralPower
import org.kisu.units.mechanics.WaveNumber
import org.kisu.units.photometric.Luminance

class AreaTest : StringSpec({
    "creates an Area" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().squareMetres.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SquareMetre(magnitude.builder().metric)
                symbol shouldBe SquareMetre.UNIT.toString()
            }
        }
    }

    "creates a base Area" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.squareMetres.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SquareMetre()
                symbol shouldBe SquareMetre.UNIT.toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing an Area by a Length returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an Area by a Time returns a KinematicViscosity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = KinematicViscosity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an Area by a Compressibility returns a Force" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = Compressibility(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Force(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an Area by a KinematicViscosity returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = KinematicViscosity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an Area by a WaveNumber returns a Volume" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = WaveNumber(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Volume(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an Area by a Force returns a Compressibility" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = Force(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Compressibility(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an Area by a Volume returns a WaveNumber" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = Volume(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = WaveNumber(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying an Area by a Current returns a MagneticDipoleMoment" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = Current(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MagneticDipoleMoment(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Area by a Length returns a Volume" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Volume(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Area by a Mass returns a MomentOfInertia" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = Mass(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MomentOfInertia(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Area by an ElectricCurrentDensity returns a Current" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = ElectricCurrentDensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Current(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Area by an ElectricDisplacementField returns an ElectricCharge" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = ElectricDisplacementField(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricCharge(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Area by an AreaDensity returns a Mass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = AreaDensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Mass(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Area by a HeatFluxDensity returns a Power" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = HeatFluxDensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Power(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Area by a Radiance returns a RadiantIntensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = Radiance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = RadiantIntensity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Area by a RadiantExposure returns an Energy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = RadiantExposure(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Energy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Area by a SpectralIrradiance returns a SpectralPower" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = SpectralIrradiance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = SpectralPower(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Area by a Luminance returns a LuminousIntensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = Luminance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = LuminousIntensity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Area by an Illuminance returns a LuminousFlux" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = Illuminance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = LuminousFlux(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Area by a MagneticFluxDensity returns a MagneticFlux" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = MagneticFluxDensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MagneticFlux(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Area by a Pressure returns a Force" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Area(leftMagnitude, leftPrefix)
            val right = Pressure(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Force(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
