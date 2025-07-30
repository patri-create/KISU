package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.Scalar
import org.kisu.units.builders.bytes

class ByteTest : StringSpec({
    "creates a Byte" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().bytes.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, Byte.SYMBOL)
                symbol shouldBe Byte.SYMBOL
            }
        }
    }

    "creates a base Byte" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.bytes.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, Byte.SYMBOL)
                symbol shouldBe Byte.SYMBOL
            }
        }
    }
})
