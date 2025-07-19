package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.lumens

class LuminousFluxTest : StringSpec({
    "creates a LuminousFlux" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().lumens.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe "${magnitude.builder().metric.symbol}${LuminousFlux.SYMBOL}"
                symbol shouldBe LuminousFlux.SYMBOL
            }
        }
    }

    "creates a base LuminousFlux" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.lumens.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe LuminousFlux.SYMBOL
                symbol shouldBe LuminousFlux.SYMBOL
            }
        }
    }
})
