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
import org.kisu.units.builders.henries
import org.kisu.units.electromagnetic.MagneticPermittivity
import org.kisu.units.electromagnetic.MagneticSusceptibility

class InductanceTest : StringSpec({
    "creates an Inductance" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().henries.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Henry(magnitude.builder().metric)
                symbol shouldBe Henry.UNIT.toString()
            }
        }
    }

    "creates a base Inductance" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.henries.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Henry()
                symbol shouldBe Henry.UNIT.toString()
            }
        }
    }

    "converts to MagneticReluctance" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.henries.magneticReluctance.inductance.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Henry()
                symbol shouldBe Henry.UNIT.toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "dividing an Inductance by a Length returns a MagneticPermittivity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Inductance(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = MagneticPermittivity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an Inductance by a MagneticPermittivity returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Inductance(leftMagnitude, leftPrefix)
            val right = MagneticPermittivity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying an Inductance by a Current returns a MagneticFlux" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Inductance(leftMagnitude, leftPrefix)
            val right = Current(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MagneticFlux(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying an Inductance by a MagneticSusceptibility returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Inductance(leftMagnitude, leftPrefix)
            val right = MagneticSusceptibility(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
