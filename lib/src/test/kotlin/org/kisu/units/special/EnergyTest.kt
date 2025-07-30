package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.joules
import org.kisu.units.representation.Scalar

class EnergyTest : StringSpec({
    "creates an Energy" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joules.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, unit = Energy.UNIT)
                symbol shouldBe Energy.UNIT.toString()
            }
        }
    }

    "creates a base Energy" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.joules.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, unit = Energy.UNIT)
                symbol shouldBe Energy.UNIT.toString()
            }
        }
    }
})
