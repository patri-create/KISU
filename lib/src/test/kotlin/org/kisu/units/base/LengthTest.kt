package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.long
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.Scalar
import org.kisu.units.builders.meters

class LengthTest : StringSpec({
    "creates Length" {
        checkAll(Arb.long().filter { it != 0L }, MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().meters.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.bigDecimal
                expression shouldBe Scalar(magnitude.builder().metric, Length.SYMBOL)
                symbol shouldBe Length.SYMBOL
            }
        }
    }

    "creates a base Length" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.meters.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.bigDecimal
                expression shouldBe Scalar(Metric.BASE, Length.SYMBOL)
                symbol shouldBe Length.SYMBOL
            }
        }
    }
})
