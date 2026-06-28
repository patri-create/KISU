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
import org.kisu.units.builders.henriesPerMetre
import org.kisu.units.electromagnetic.MagneticPermittivity.Companion.HenryPerMetre
import org.kisu.units.special.Inductance

class MagneticPermittivityTest : StringSpec({
    "creates a MagneticPermittivity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().henriesPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe HenryPerMetre(magnitude.builder().metric)
                symbol shouldBe HenryPerMetre().toString()
            }
        }
    }

    "creates a base MagneticPermittivity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.henriesPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe HenryPerMetre()
                symbol shouldBe HenryPerMetre().toString()
            }
        }
    }

    "converts to MagneticSusceptibility" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            val roundTrip = magnitude.henriesPerMetre.magneticSusceptibility.magneticPermittivity

            roundTrip.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe HenryPerMetre()
                symbol shouldBe HenryPerMetre().toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "multiplying a MagneticPermittivity by a Length returns an Inductance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MagneticPermittivity(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Inductance(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
