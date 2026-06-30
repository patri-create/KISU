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
import org.kisu.units.builders.siemens
import org.kisu.units.electromagnetic.ElectricConductivity

class ConductanceTest : StringSpec({
    "creates a Conductance" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().siemens.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Siemens(magnitude.builder().metric)
                symbol shouldBe Siemens.UNIT.toString()
            }
        }
    }

    "creates a base Conductance" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.siemens.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Siemens()
                symbol shouldBe Siemens.UNIT.toString()
            }
        }
    }

    "converts to Resistance" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.siemens.resistance.conductance.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Siemens()
                symbol shouldBe Siemens.UNIT.toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "dividing a Conductance by a Length returns an ElectricConductivity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Conductance(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = ElectricConductivity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Conductance by an ElectricConductivity returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Conductance(leftMagnitude, leftPrefix)
            val right = ElectricConductivity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a Conductance by an ElectricPotential returns a Current" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Conductance(leftMagnitude, leftPrefix)
            val right = ElectricPotential(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Current(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
