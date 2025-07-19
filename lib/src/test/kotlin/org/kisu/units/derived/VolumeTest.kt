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
import org.kisu.units.builders.cubicMeters

class VolumeTest : StringSpec({
    "creates a Volume" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().cubicMeters.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, Volume.SYMBOL)
                symbol shouldBe Volume.SYMBOL
            }
        }
    }

    "creates a base Volume" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.cubicMeters.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, Volume.SYMBOL)
                symbol shouldBe Volume.SYMBOL
            }
        }
    }
})
