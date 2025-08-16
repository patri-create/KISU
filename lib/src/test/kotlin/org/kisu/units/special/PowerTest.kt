package org.kisu.units.special

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
                expression shouldBe Watt(magnitude.builder().metric)
                symbol shouldBe Watt.UNIT.toString()
            }
        }
    }

    "creates a base Power" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.watts.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Watt()
                symbol shouldBe Watt.UNIT.toString()
            }
        }
    }
})
