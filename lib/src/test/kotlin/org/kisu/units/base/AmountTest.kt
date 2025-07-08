package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.moles

class AmountTest : StringSpec({

    "creates an Amount" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().moles.should { (amount, prefix, symbol) ->
                amount shouldBe magnitude
                prefix shouldBe magnitude.builder().metric
                symbol shouldBe Amount.SYMBOL
            }
        }
    }

    "creates a base Amount" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.moles.should { (amount, prefix, symbol) ->
                amount shouldBe magnitude
                prefix shouldBe Metric.BASE
                symbol shouldBe Amount.SYMBOL
            }
        }
    }
})
