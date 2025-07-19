package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.grays

class AbsorbedDoseTest : StringSpec({
    "creates an AbsorbedDose" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().grays
                .should { (amount, expression, symbol) ->
                    amount shouldBe magnitude
                    expression shouldBe "${magnitude.builder().metric.symbol}${AbsorbedDose.SYMBOL}"
                    symbol shouldBe AbsorbedDose.SYMBOL
                }
        }
    }

    "creates a base AbsorbedDose" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.grays.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe AbsorbedDose.SYMBOL
                symbol shouldBe AbsorbedDose.SYMBOL
            }
        }
    }
})
