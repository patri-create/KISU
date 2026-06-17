package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.joulesPerTesla
import org.kisu.units.electromagnetic.MagneticDipoleMoment.Companion.JoulePerTesla

class MagneticDipoleMomentTest : StringSpec({
    "creates a MagneticDipoleMoment" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerTesla.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerTesla(magnitude.builder().metric)
                symbol shouldBe JoulePerTesla().toString()
            }
        }
    }

    "creates a base MagneticDipoleMoment" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.joulesPerTesla.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerTesla()
                symbol shouldBe JoulePerTesla().toString()
            }
        }
    }
})
