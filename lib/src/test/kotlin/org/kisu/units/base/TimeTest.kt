package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveLong
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.seconds

class TimeTest : StringSpec({
    "creates Time" {
        checkAll(Arb.positiveLong(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().seconds.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.bigDecimal
                expression shouldBe Second(magnitude.builder().metric)
                symbol shouldBe Second.UNIT.toString()
            }
        }
    }

    "creates a base Time" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.seconds.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.bigDecimal
                expression shouldBe Second()
                symbol shouldBe Second.UNIT.toString()
            }
        }
    }
})
