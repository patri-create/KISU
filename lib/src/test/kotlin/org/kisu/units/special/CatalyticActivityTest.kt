package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.katals

class CatalyticActivityTest : StringSpec({
    "creates a CatalyticActivity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().katals.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Katal(magnitude.builder().metric)
                symbol shouldBe Katal.UNIT.toString()
            }
        }
    }

    "creates a base CatalyticActivity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.katals.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Katal()
                symbol shouldBe Katal.UNIT.toString()
            }
        }
    }
})
