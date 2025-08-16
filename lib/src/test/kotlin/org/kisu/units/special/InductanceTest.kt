package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.henries

class InductanceTest : StringSpec({
    "creates an Inductance" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().henries.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Henry(magnitude.builder().metric)
                symbol shouldBe Henry.UNIT.toString()
            }
        }
    }

    "creates a base Inductance" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.henries.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Henry()
                symbol shouldBe Henry.UNIT.toString()
            }
        }
    }
})
