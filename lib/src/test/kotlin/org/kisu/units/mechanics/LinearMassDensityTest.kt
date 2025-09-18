package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.gramsPerMetre
import org.kisu.units.mechanics.LinearMassDensity.Companion.KilogramPerMetre

class LinearMassDensityTest : StringSpec({
    "creates a LinearMassDensity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerMetre(magnitude.builder().metric)
                symbol shouldBe KilogramPerMetre().toString()
            }
        }
    }

    "creates a base LinearMassDensity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.gramsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerMetre()
                symbol shouldBe KilogramPerMetre().toString()
            }
        }
    }
})
