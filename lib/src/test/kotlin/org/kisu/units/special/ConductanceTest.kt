package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.siemens
import org.kisu.units.representation.Scalar

class ConductanceTest : StringSpec({
    "creates a Conductance" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().siemens.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, unit = Conductance.UNIT)
                symbol shouldBe Conductance.UNIT.toString()
            }
        }
    }

    "creates a base Conductance" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.siemens.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, unit = Conductance.UNIT)
                symbol shouldBe Conductance.UNIT.toString()
            }
        }
    }
})
