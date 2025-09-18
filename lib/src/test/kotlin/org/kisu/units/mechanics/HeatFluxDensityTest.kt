package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.wattsPerSquareMetre
import org.kisu.units.mechanics.HeatFluxDensity.Companion.WattPerSquareMetre

class HeatFluxDensityTest : StringSpec({
    "creates a HeatFluxDensity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSquareMetre(magnitude.builder().metric)
                symbol shouldBe WattPerSquareMetre().toString()
            }
        }
    }

    "creates a base HeatFluxDensity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.wattsPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSquareMetre()
                symbol shouldBe WattPerSquareMetre().toString()
            }
        }
    }
})
