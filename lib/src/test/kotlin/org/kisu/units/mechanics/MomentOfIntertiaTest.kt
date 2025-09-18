package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.gramsSquareMetre
import org.kisu.units.mechanics.MomentOfIntertia.Companion.KilogramSquareMetre

class MomentOfIntertiaTest : StringSpec({
    "creates a MomentOfIntertia" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramSquareMetre(magnitude.builder().metric)
                symbol shouldBe KilogramSquareMetre().toString()
            }
        }
    }

    "creates a base MomentOfIntertia" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.gramsSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramSquareMetre()
                symbol shouldBe KilogramSquareMetre().toString()
            }
        }
    }
})
