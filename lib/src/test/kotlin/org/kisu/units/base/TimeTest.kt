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
import org.kisu.units.builders.seconds
import org.kisu.units.representation.Scalar

class TimeTest : StringSpec({
    "creates Time" {
        checkAll(Arb.positiveLong(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().seconds.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.bigDecimal
                expression shouldBe Scalar(magnitude.builder().metric, unit = Time.UNIT)
                symbol shouldBe Time.UNIT.toString()
            }
        }
    }

    "creates a base Time" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.seconds.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.bigDecimal
                expression shouldBe Scalar(Metric.BASE, unit = Time.UNIT)
                symbol shouldBe Time.UNIT.toString()
            }
        }
    }
})
