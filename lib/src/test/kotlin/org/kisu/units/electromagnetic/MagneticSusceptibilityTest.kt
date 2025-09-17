package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.metresPerHenry
import org.kisu.units.electromagnetic.MagneticSusceptibility.Companion.MetrePerHenry

class MagneticSusceptibilityTest : StringSpec({
    "creates a MagneticSusceptibility" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metresPerHenry.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerHenry(magnitude.builder().metric)
                symbol shouldBe MetrePerHenry().toString()
            }
        }
    }

    "creates a base MagneticSusceptibility" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.metresPerHenry.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerHenry()
                symbol shouldBe MetrePerHenry().toString()
            }
        }
    }
})
