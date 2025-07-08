package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.sieverts

class DoseEquivalentTest : StringSpec({
    "creates a DoseEquivalent" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().sieverts.should { (amount, prefix, symbol) ->
                amount shouldBe magnitude
                prefix shouldBe magnitude.builder().metric
                symbol shouldBe DoseEquivalent.SYMBOL
            }
        }
    }

    "creates a base DoseEquivalent" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.sieverts.should { (amount, prefix, symbol) ->
                amount shouldBe magnitude
                prefix shouldBe Metric.BASE
                symbol shouldBe DoseEquivalent.SYMBOL
            }
        }
    }
})
