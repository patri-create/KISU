package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.ohms
import org.kisu.units.representation.Scalar

class ResistanceTest : StringSpec({
    "creates a Resistance" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().ohms.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, Resistance.SYMBOL)
                symbol shouldBe Resistance.SYMBOL
            }
        }
    }

    "creates a base Resistance" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.ohms.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, Resistance.SYMBOL)
                symbol shouldBe Resistance.SYMBOL
            }
        }
    }
})
