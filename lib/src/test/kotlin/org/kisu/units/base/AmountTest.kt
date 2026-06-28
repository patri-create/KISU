package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.builders.moles
import org.kisu.units.chemistry.CatalyticEfficiency
import org.kisu.units.chemistry.Molality
import org.kisu.units.chemistry.MolarEnergy
import org.kisu.units.chemistry.MolarHeatCapacity
import org.kisu.units.chemistry.MolarMass
import org.kisu.units.chemistry.MolarVolume
import org.kisu.units.chemistry.Molarity
import org.kisu.units.kinematics.VolumetricFlow
import org.kisu.units.special.CatalyticActivity
import org.kisu.units.special.Energy
import org.kisu.units.special.Volume
import org.kisu.units.thermodynamics.HeatCapacity

class AmountTest : StringSpec({
    "creates an Amount" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().moles.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Mole(magnitude.builder().metric)
                symbol shouldBe Mole.UNIT.toString()
            }
        }
    }

    "creates a base Amount" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.moles.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Mole()
                symbol shouldBe Mole.UNIT.toString()
            }
        }
    }

    "converts to ReciprocalAmount" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.moles.reciprocalAmount.amount.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Mole()
                symbol shouldBe Mole.UNIT.toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "dividing an Amount by a Mass returns a Molality" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Amount(leftMagnitude, leftPrefix)
            val right = Mass(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Molality(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an Amount by a Time returns a CatalyticActivity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Amount(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = CatalyticActivity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an Amount by a Molality returns a Mass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Amount(leftMagnitude, leftPrefix)
            val right = Molality(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Mass(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an Amount by a Molarity returns a Volume" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Amount(leftMagnitude, leftPrefix)
            val right = Molarity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Volume(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an Amount by a CatalyticActivity returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Amount(leftMagnitude, leftPrefix)
            val right = CatalyticActivity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an Amount by a Volume returns a Molarity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Amount(leftMagnitude, leftPrefix)
            val right = Volume(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Molarity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying an Amount by a CatalyticEfficiency returns a VolumetricFlow" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Amount(leftMagnitude, leftPrefix)
            val right = CatalyticEfficiency(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = VolumetricFlow(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Amount by a MolarEnergy returns an Energy" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Amount(leftMagnitude, leftPrefix)
            val right = MolarEnergy(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Energy(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Amount by a MolarHeatCapacity returns a HeatCapacity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Amount(leftMagnitude, leftPrefix)
            val right = MolarHeatCapacity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = HeatCapacity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Amount by a MolarMass returns a Mass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Amount(leftMagnitude, leftPrefix)
            val right = MolarMass(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Mass(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Amount by a MolarVolume returns a Volume" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Amount(leftMagnitude, leftPrefix)
            val right = MolarVolume(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Volume(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
