package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.joulePerTesla
import org.kisu.units.electromagnetic.MagneticDipoleMoment.Companion.JoulePerTesla

class MagneticDipoleMomentTest : StringSpec({
    "creates a MagneticDipoleMoment" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulePerTesla.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerTesla(magnitude.builder().metric)
                symbol shouldBe JoulePerTesla().toString()
            }
        }
    }

    "creates a base MagneticDipoleMoment" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.joulePerTesla.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerTesla()
                symbol shouldBe JoulePerTesla().toString()
            }
        }
    }
})
