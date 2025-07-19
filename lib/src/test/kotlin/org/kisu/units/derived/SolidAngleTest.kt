package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.Scalar
import org.kisu.units.builders.steradians

class SolidAngleTest : StringSpec({
    "creates a SolidAngle" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().steradians.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, SolidAngle.SYMBOL)
                symbol shouldBe SolidAngle.SYMBOL
            }
        }
    }

    "creates a base SolidAngle" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.steradians.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, SolidAngle.SYMBOL)
                symbol shouldBe SolidAngle.SYMBOL
            }
        }
    }
})
