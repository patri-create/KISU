package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.amperesPerMetre
import org.kisu.units.electromagnetic.Magnetization.Companion.AmperePerMetre

class MagnetizationTest : StringSpec({
    "creates a Magnetization" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().amperesPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe AmperePerMetre(magnitude.builder().metric)
                symbol shouldBe AmperePerMetre().toString()
            }
        }
    }

    "creates a base Magnetization" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.amperesPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe AmperePerMetre()
                symbol shouldBe AmperePerMetre().toString()
            }
        }
    }
})
