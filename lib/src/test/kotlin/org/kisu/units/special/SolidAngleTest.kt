package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.steradians

class SolidAngleTest : StringSpec({
    "creates a SolidAngle" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().steradians.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Steradian(magnitude.builder().metric)
                symbol shouldBe Steradian.UNIT.toString()
            }
        }
    }

    "creates a base SolidAngle" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.steradians.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Steradian()
                symbol shouldBe Steradian.UNIT.toString()
            }
        }
    }
})
