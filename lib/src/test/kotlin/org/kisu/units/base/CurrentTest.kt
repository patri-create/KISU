package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.amperes

class CurrentTest : StringSpec({
    "creates a Current" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().amperes.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe "${magnitude.builder().metric.symbol}${Current.SYMBOL}"
                symbol shouldBe Current.SYMBOL
            }
        }
    }

    "creates a base Current" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.amperes.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Current.SYMBOL
                symbol shouldBe Current.SYMBOL
            }
        }
    }
})
