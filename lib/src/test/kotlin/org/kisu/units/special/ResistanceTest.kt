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
import org.kisu.units.builders.ohms
import org.kisu.units.electromagnetic.Resistivity

class ResistanceTest : StringSpec({
    "creates a Resistance" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().ohms.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Ohm(magnitude.builder().metric)
                symbol shouldBe Ohm.UNIT.toString()
            }
        }
    }

    "creates a base Resistance" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.ohms.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Ohm()
                symbol shouldBe Ohm.UNIT.toString()
            }
        }
    }

    "converts to Conductance" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.ohms.conductance.resistance.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Ohm()
                symbol shouldBe Ohm.UNIT.toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "multiplying a Resistance by a Current returns an ElectricPotential" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Resistance(leftMagnitude, leftPrefix)
            val right = Current(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricPotential(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a Resistance by a Length returns a Resistivity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Resistance(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Resistivity(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
