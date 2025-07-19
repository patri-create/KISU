package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.watts

class PowerTest : StringSpec({
    "creates a Power" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().watts.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe "${magnitude.builder().metric.symbol}${Power.SYMBOL}"
                symbol shouldBe Power.SYMBOL
            }
        }
    }

    "creates a base Power" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.watts.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Power.SYMBOL
                symbol shouldBe Power.SYMBOL
            }
        }
    }
})
