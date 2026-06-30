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
import org.kisu.units.base.Time
import org.kisu.units.builders.volts
import org.kisu.units.electromagnetic.ElectricFieldStrength
import org.kisu.units.electromagnetic.ElectronMobility
import org.kisu.units.mechanics.KinematicViscosity

class ElectricPotentialTest : StringSpec({
    "creates an ElectricPotential" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().volts.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Volt(magnitude.builder().metric)
                symbol shouldBe Volt.UNIT.toString()
            }
        }
    }

    "creates a base ElectricPotential" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.volts.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Volt()
                symbol shouldBe Volt.UNIT.toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing an ElectricPotential by a Current returns a Resistance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricPotential(leftMagnitude, leftPrefix)
            val right = Current(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Resistance(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an ElectricPotential by a Length returns an ElectricFieldStrength" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricPotential(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = ElectricFieldStrength(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an ElectricPotential by an ElectricFieldStrength returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricPotential(leftMagnitude, leftPrefix)
            val right = ElectricFieldStrength(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an ElectricPotential by an Elastance returns an ElectricCharge" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricPotential(leftMagnitude, leftPrefix)
            val right = Elastance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = ElectricCharge(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an ElectricPotential by an ElectricCharge returns an Elastance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricPotential(leftMagnitude, leftPrefix)
            val right = ElectricCharge(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Elastance(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an ElectricPotential by a Resistance returns a Current" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricPotential(leftMagnitude, leftPrefix)
            val right = Resistance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Current(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying an ElectricPotential by a Current returns a Power" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricPotential(leftMagnitude, leftPrefix)
            val right = Current(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Power(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an ElectricPotential by a Time returns a MagneticFlux" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricPotential(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MagneticFlux(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an ElectricPotential by an ElectronMobility returns a KinematicViscosity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricPotential(leftMagnitude, leftPrefix)
            val right = ElectronMobility(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = KinematicViscosity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an ElectricPotential by a Capacitance returns an ElectricCharge" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricPotential(leftMagnitude, leftPrefix)
            val right = Capacitance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricCharge(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an ElectricPotential by a Conductance returns a Current" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricPotential(leftMagnitude, leftPrefix)
            val right = Conductance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Current(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an ElectricPotential by an ElectricCharge returns an Energy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricPotential(leftMagnitude, leftPrefix)
            val right = ElectricCharge(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Energy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
