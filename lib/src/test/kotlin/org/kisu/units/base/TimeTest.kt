package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveLong
import io.kotest.property.checkAll
import org.kisu.magnitude
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.builders.seconds
import org.kisu.units.chemistry.CatalyticEfficiency
import org.kisu.units.chemistry.MolarVolume
import org.kisu.units.kinematics.FrequencyDrift
import org.kisu.units.kinematics.VolumetricFlow
import org.kisu.units.kinematics.Yank
import org.kisu.units.kinematics.angular.Velocity
import org.kisu.units.kinematics.linear.Speed
import org.kisu.units.mechanics.AbsorbedDoseRate
import org.kisu.units.mechanics.Action
import org.kisu.units.mechanics.DynamicViscosity
import org.kisu.units.mechanics.EnergyFluxDensity
import org.kisu.units.mechanics.KinematicViscosity
import org.kisu.units.mechanics.MassFlowRate
import org.kisu.units.mechanics.Momentum
import org.kisu.units.mechanics.RadiantExposure
import org.kisu.units.photometric.Exposure
import org.kisu.units.photometric.LuminousEnergy
import org.kisu.units.special.AbsorbedDose
import org.kisu.units.special.Area
import org.kisu.units.special.CatalyticActivity
import org.kisu.units.special.ElectricCharge
import org.kisu.units.special.ElectricPotential
import org.kisu.units.special.Energy
import org.kisu.units.special.Force
import org.kisu.units.special.Frequency
import org.kisu.units.special.Illuminance
import org.kisu.units.special.LuminousFlux
import org.kisu.units.special.MagneticFlux
import org.kisu.units.special.PlaneAngle
import org.kisu.units.special.Pressure
import org.kisu.units.special.Volume
import org.kisu.units.kinematics.angular.Acceleration as AngularAcceleration
import org.kisu.units.kinematics.angular.Crackle as AngularCrackle
import org.kisu.units.kinematics.angular.Jerk as AngularJerk
import org.kisu.units.kinematics.angular.Pop as AngularPop
import org.kisu.units.kinematics.angular.Snap as AngularSnap
import org.kisu.units.kinematics.linear.Acceleration as LinearAcceleration
import org.kisu.units.kinematics.linear.Crackle as LinearCrackle
import org.kisu.units.kinematics.linear.Jerk as LinearJerk
import org.kisu.units.kinematics.linear.Pop as LinearPop
import org.kisu.units.kinematics.linear.Snap as LinearSnap

class TimeTest : StringSpec({
    "creates Time" {
        checkAll(Arb.positiveLong(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().seconds.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.magnitude
                expression shouldBe Second(magnitude.builder().metric)
                symbol shouldBe Second.UNIT.toString()
            }
        }
    }

    "creates a base Time" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.seconds.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Second()
                symbol shouldBe Second.UNIT.toString()
            }
        }
    }

    "converts to Frequency" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.seconds.frequency.period.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Second()
                symbol shouldBe Second.UNIT.toString()
            }
        }
    }

    "converts to Radioactivity" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.seconds.activity.meanInterval.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Second()
                symbol shouldBe Second.UNIT.toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "multiplying a Time by a Current returns an ElectricCharge" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = Current(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricCharge(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a CatalyticEfficiency returns a MolarVolume" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = CatalyticEfficiency(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MolarVolume(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a FrequencyDrift returns a Frequency" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = FrequencyDrift(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Frequency(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a VolumetricFlow returns a Volume" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = VolumetricFlow(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Volume(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a Yank returns a Force" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = Yank(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Force(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by an AngularAcceleration returns a Velocity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = AngularAcceleration(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Velocity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by an AngularCrackle returns an AngularSnap" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = AngularCrackle(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = AngularSnap(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by an AngularJerk returns an AngularAcceleration" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = AngularJerk(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = AngularAcceleration(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by an AngularPop returns an AngularCrackle" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = AngularPop(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = AngularCrackle(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by an AngularSnap returns an AngularJerk" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = AngularSnap(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = AngularJerk(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a Velocity returns a PlaneAngle" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = Velocity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = PlaneAngle(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a LinearAcceleration returns a Speed" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = LinearAcceleration(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Speed(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a LinearCrackle returns a LinearSnap" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = LinearCrackle(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = LinearSnap(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a LinearJerk returns a LinearAcceleration" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = LinearJerk(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = LinearAcceleration(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a LinearPop returns a LinearCrackle" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = LinearPop(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = LinearCrackle(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a LinearSnap returns a LinearJerk" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = LinearSnap(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = LinearJerk(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a Speed returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = Speed(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by an AbsorbedDoseRate returns an AbsorbedDose" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = AbsorbedDoseRate(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = AbsorbedDose(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by an EnergyFluxDensity returns a RadiantExposure" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = EnergyFluxDensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = RadiantExposure(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a KinematicViscosity returns an Area" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = KinematicViscosity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Area(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a MassFlowRate returns a Mass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = MassFlowRate(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Mass(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a CatalyticActivity returns an Amount" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = CatalyticActivity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Amount(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by an ElectricPotential returns a MagneticFlux" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = ElectricPotential(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MagneticFlux(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by an Energy returns an Action" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = Energy(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Action(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a Force returns a Momentum" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = Force(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Momentum(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by an Illuminance returns an Exposure" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = Illuminance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Exposure(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a LuminousFlux returns a LuminousEnergy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = LuminousFlux(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = LuminousEnergy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Time by a Pressure returns a DynamicViscosity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Time(leftMagnitude, leftPrefix)
            val right = Pressure(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = DynamicViscosity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
