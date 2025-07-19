package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.radians

class PlaneAngleTest : StringSpec({

    "creates a PlaneAngle" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().radians.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe "${magnitude.builder().metric.symbol}${PlaneAngle.SYMBOL}"
                symbol shouldBe PlaneAngle.SYMBOL
            }
        }
    }

    "creates a base PlaneAngle" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.radians.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe PlaneAngle.SYMBOL
                symbol shouldBe PlaneAngle.SYMBOL
            }
        }
    }
})
