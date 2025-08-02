package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.henries
import org.kisu.units.representation.Scalar

class InductanceTest : StringSpec({
    "creates an Inductance" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().henries.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, Inductance.SYMBOL)
                symbol shouldBe Inductance.SYMBOL
            }
        }
    }

    "creates a base Inductance" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.henries.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, Inductance.SYMBOL)
                symbol shouldBe Inductance.SYMBOL
            }
        }
    }
})
