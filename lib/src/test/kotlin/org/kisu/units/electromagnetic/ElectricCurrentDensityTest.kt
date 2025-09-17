package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.amperesPerSquareMetre
import org.kisu.units.electromagnetic.ElectricCurrentDensity.Companion.AmperePerSquareMetre

class ElectricCurrentDensityTest : StringSpec({
    "creates an ElectricCurrentDensity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().amperesPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe AmperePerSquareMetre(magnitude.builder().metric)
                symbol shouldBe AmperePerSquareMetre().toString()
            }
        }
    }

    "creates a base ElectricCurrentDensity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.amperesPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe AmperePerSquareMetre()
                symbol shouldBe AmperePerSquareMetre().toString()
            }
        }
    }
})
