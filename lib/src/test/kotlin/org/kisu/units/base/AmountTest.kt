package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.moles

class AmountTest : StringSpec({

    "creates an Amount" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().moles.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Mol(magnitude.builder().metric)
                symbol shouldBe Mol.UNIT.toString()
            }
        }
    }

    "creates a base Amount" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.moles.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Mol()
                symbol shouldBe Mol.UNIT.toString()
            }
        }
    }
})
