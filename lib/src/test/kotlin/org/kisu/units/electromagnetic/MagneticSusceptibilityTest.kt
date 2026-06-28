package org.kisu.units.electromagnetic

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
import org.kisu.units.builders.metresPerHenry
import org.kisu.units.electromagnetic.MagneticSusceptibility.Companion.MetrePerHenry
import org.kisu.units.special.Inductance

class MagneticSusceptibilityTest : StringSpec({
    "creates a MagneticSusceptibility" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metresPerHenry.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerHenry(magnitude.builder().metric)
                symbol shouldBe MetrePerHenry().toString()
            }
        }
    }

    "creates a base MagneticSusceptibility" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.metresPerHenry.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerHenry()
                symbol shouldBe MetrePerHenry().toString()
            }
        }
    }

    "converts to MagneticPermittivity" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            val roundTrip = magnitude.metresPerHenry.magneticPermittivity.magneticSusceptibility

            roundTrip.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerHenry()
                symbol shouldBe MetrePerHenry().toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "multiplying a MagneticSusceptibility by an Inductance returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MagneticSusceptibility(leftMagnitude, leftPrefix)
            val right = Inductance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
