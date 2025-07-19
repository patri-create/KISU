package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.grams

class MassTest : StringSpec({
    "creates mass" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().grams.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe "${magnitude.builder().metric.symbol}${Mass.SYMBOL}"
                symbol shouldBe Mass.SYMBOL
            }
        }
    }

    "creates a base Mass" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.grams.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Mass.SYMBOL
                symbol shouldBe Mass.SYMBOL
            }
        }
    }
})
