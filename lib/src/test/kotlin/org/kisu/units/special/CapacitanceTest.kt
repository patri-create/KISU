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
import org.kisu.units.base.Length
import org.kisu.units.builders.farads
import org.kisu.units.electromagnetic.Permittivity

class CapacitanceTest : StringSpec({
    "creates a Capacitance" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().farads.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Farad(magnitude.builder().metric)
                symbol shouldBe Farad.UNIT.toString()
            }
        }
    }

    "creates a base Capacitance" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.farads.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Farad()
                symbol shouldBe Farad.UNIT.toString()
            }
        }
    }

    "converts to Elastance" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.farads.elastance.capacitance.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Farad()
                symbol shouldBe Farad.UNIT.toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "dividing a Capacitance by a Length returns a Permittivity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Capacitance(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Permittivity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Capacitance by a Permittivity returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Capacitance(leftMagnitude, leftPrefix)
            val right = Permittivity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a Capacitance by an ElectricPotential returns an ElectricCharge" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Capacitance(leftMagnitude, leftPrefix)
            val right = ElectricPotential(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricCharge(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
