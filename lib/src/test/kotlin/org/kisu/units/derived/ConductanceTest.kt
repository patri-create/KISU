package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.siemens

class ConductanceTest : StringSpec({
    "creates a Conductance" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().siemens.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe "${magnitude.builder().metric.symbol}${Conductance.SYMBOL}"
                symbol shouldBe Conductance.SYMBOL
            }
        }
    }

    "creates a base Conductance" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.siemens.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Conductance.SYMBOL
                symbol shouldBe Conductance.SYMBOL
            }
        }
    }
})
