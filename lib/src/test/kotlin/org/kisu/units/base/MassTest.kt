package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.grams
import org.kisu.units.representation.Scalar

class MassTest : StringSpec({
    "creates mass" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().grams.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, unit = Mass.UNIT)
                symbol shouldBe Mass.UNIT.toString()
            }
        }
    }

    "creates a base Mass" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.grams.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, unit = Mass.UNIT)
                symbol shouldBe Mass.UNIT.toString()
            }
        }
    }
})
