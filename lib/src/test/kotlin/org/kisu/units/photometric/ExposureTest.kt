package org.kisu.units.photometric

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.luxSecond

class ExposureTest : StringSpec({
    "creates an Exposure" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().luxSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe LuxSecond(magnitude.builder().metric)
                symbol shouldBe LuxSecond().toString()
            }
        }
    }

    "creates a base Exposure" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.luxSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe LuxSecond()
                symbol shouldBe LuxSecond().toString()
            }
        }
    }
})
