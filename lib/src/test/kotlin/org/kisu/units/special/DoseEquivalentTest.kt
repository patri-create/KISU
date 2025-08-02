package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.sieverts
import org.kisu.units.representation.Scalar

class DoseEquivalentTest : StringSpec({
    "creates a DoseEquivalent" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().sieverts.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, DoseEquivalent.UNIT)
                symbol shouldBe DoseEquivalent.UNIT.toString()
            }
        }
    }

    "creates a base DoseEquivalent" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.sieverts.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, DoseEquivalent.UNIT)
                symbol shouldBe DoseEquivalent.UNIT.toString()
            }
        }
    }
})
