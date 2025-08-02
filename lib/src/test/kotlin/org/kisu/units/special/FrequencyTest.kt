package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.hertz
import org.kisu.units.representation.Scalar

class FrequencyTest : StringSpec({
    "creates a Frequency" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().hertz.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, Frequency.UNIT)
                symbol shouldBe Frequency.UNIT.toString()
            }
        }
    }

    "creates a base Frequency" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.hertz.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, Frequency.UNIT)
                symbol shouldBe Frequency.UNIT.toString()
            }
        }
    }
})
