package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.hertz

class FrequencyTest : StringSpec({
    "creates a Frequency" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().hertz.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe "${magnitude.builder().metric.symbol}${Frequency.SYMBOL}"
                symbol shouldBe Frequency.SYMBOL
            }
        }
    }

    "creates a base Frequency" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.hertz.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Frequency.SYMBOL
                symbol shouldBe Frequency.SYMBOL
            }
        }
    }
})
