package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.radians
import org.kisu.units.representation.Scalar

class PlaneAngleTest : StringSpec({

    "creates a PlaneAngle" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().radians.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, PlaneAngle.UNIT)
                symbol shouldBe PlaneAngle.UNIT.toString()
            }
        }
    }

    "creates a base PlaneAngle" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.radians.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, PlaneAngle.UNIT)
                symbol shouldBe PlaneAngle.UNIT.toString()
            }
        }
    }
})
