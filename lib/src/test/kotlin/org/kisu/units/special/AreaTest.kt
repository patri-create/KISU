package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.squareMeters

class AreaTest : StringSpec({
    "creates an Area" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().squareMeters.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SquareMetre(magnitude.builder().metric)
                symbol shouldBe SquareMetre.UNIT.toString()
            }
        }
    }

    "creates a base Area" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.squareMeters.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SquareMetre()
                symbol shouldBe SquareMetre.UNIT.toString()
            }
        }
    }
})
