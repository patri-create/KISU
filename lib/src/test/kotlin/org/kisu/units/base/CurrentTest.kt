package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.amperes
import org.kisu.units.representation.Scalar

class CurrentTest : StringSpec({
    "creates a Current" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().amperes.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, Current.UNIT)
                symbol shouldBe Current.UNIT.toString()
            }
        }
    }

    "creates a base Current" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.amperes.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, Current.UNIT)
                symbol shouldBe Current.UNIT.toString()
            }
        }
    }
})
