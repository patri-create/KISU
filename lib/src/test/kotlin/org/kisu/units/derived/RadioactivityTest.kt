package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.Scalar
import org.kisu.units.builders.becquerels

class RadioactivityTest : StringSpec({
    "creates a Radioactivity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().becquerels.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, Radioactivity.SYMBOL)
                symbol shouldBe Radioactivity.SYMBOL
            }
        }
    }

    "creates a base Radioactivity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.becquerels.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, Radioactivity.SYMBOL)
                symbol shouldBe Radioactivity.SYMBOL
            }
        }
    }
})
