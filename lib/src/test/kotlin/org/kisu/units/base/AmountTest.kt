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
import org.kisu.units.representation.Scalar

class AmountTest : StringSpec({

    "creates an Amount" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().moles.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, Amount.SYMBOL)
                symbol shouldBe Amount.SYMBOL
            }
        }
    }

    "creates a base Amount" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.moles.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, Amount.SYMBOL)
                symbol shouldBe Amount.SYMBOL
            }
        }
    }
})
