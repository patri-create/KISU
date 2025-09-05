package org.kisu.units.photometric

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.lumensPerWatt

class EfficacyTest : StringSpec({
    "creates an Efficacy" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().lumensPerWatt.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe LumenPerWatt(magnitude.builder().metric)
                symbol shouldBe LumenPerWatt().toString()
            }
        }
    }

    "creates a base Efficacy" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.lumensPerWatt.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe LumenPerWatt()
                symbol shouldBe LumenPerWatt().toString()
            }
        }
    }
})
