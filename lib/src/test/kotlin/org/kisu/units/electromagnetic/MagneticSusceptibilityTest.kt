package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.builders.metresPerHenry
import org.kisu.units.electromagnetic.MagneticSusceptibility.Companion.MetrePerHenry

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
})
