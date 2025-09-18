package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.wattsPerSteradian
import org.kisu.units.mechanics.RadiantIntensity.Companion.WattPerSteradian

class RadiantIntensityTest : StringSpec({
    "creates a RadiantIntensity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerSteradian.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSteradian(magnitude.builder().metric)
                symbol shouldBe WattPerSteradian().toString()
            }
        }
    }

    "creates a base RadiantIntensity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.wattsPerSteradian.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSteradian()
                symbol shouldBe WattPerSteradian().toString()
            }
        }
    }
})
