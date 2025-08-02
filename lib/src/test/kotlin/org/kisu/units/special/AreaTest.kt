package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.squareMeters
import org.kisu.units.representation.Scalar

class AreaTest : StringSpec({
    "creates an Area" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().squareMeters.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, Area.UNIT)
                symbol shouldBe Area.UNIT.toString()
            }
        }
    }

    "creates a base Area" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.squareMeters.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, Area.UNIT)
                symbol shouldBe Area.UNIT.toString()
            }
        }
    }
})
