package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.long
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.metres

class LengthTest : StringSpec({
    "creates Length" {
        checkAll(Arb.long().filter { it != 0L }, MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metres.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.bigDecimal
                expression shouldBe Metre(magnitude.builder().metric)
                symbol shouldBe Metre.UNIT.toString()
            }
        }
    }

    "creates a base Length" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.metres.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.bigDecimal
                expression shouldBe Metre()
                symbol shouldBe Metre.UNIT.toString()
            }
        }
    }
})
