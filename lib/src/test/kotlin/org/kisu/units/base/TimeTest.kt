package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveLong
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.Scalar
import org.kisu.units.builders.seconds

class TimeTest : StringSpec({
    "creates Time" {
        checkAll(Arb.positiveLong(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().seconds.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.bigDecimal
                expression shouldBe Scalar(magnitude.builder().metric, Time.SYMBOL)
                symbol shouldBe Time.SYMBOL
            }
        }
    }

    "creates a base Time" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.seconds.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.bigDecimal
                expression shouldBe Scalar(Metric.BASE, Time.SYMBOL)
                symbol shouldBe Time.SYMBOL
            }
        }
    }
})
