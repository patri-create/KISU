package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.gramsPerSquareMetre
import org.kisu.units.mechanics.AreaDensity.Companion.KilogramPerSquareMetre

class AreaDensityTest : StringSpec({
    "creates an AreaDensity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerSquareMetre(magnitude.builder().metric)
                symbol shouldBe KilogramPerSquareMetre().toString()
            }
        }
    }

    "creates a base AreaDensity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.gramsPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerSquareMetre()
                symbol shouldBe KilogramPerSquareMetre().toString()
            }
        }
    }
})
