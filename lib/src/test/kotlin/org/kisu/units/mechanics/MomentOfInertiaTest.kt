package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.gramsSquareMetre
import org.kisu.units.mechanics.MomentOfInertia.Companion.KilogramSquareMetre

class MomentOfInertiaTest : StringSpec({
    "creates a MomentOfInertia" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramSquareMetre(magnitude.builder().metric)
                symbol shouldBe KilogramSquareMetre().toString()
            }
        }
    }

    "creates a base MomentOfInertia" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.gramsSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramSquareMetre()
                symbol shouldBe KilogramSquareMetre().toString()
            }
        }
    }
})
