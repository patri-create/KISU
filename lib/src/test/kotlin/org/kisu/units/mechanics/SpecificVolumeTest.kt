package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.cubicMetresPerKilogram
import org.kisu.units.mechanics.SpecificVolume.Companion.CubicMetrePerKilogram

class SpecificVolumeTest : StringSpec({
    "creates a SpecificVolume" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().cubicMetresPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerKilogram(magnitude.builder().metric)
                symbol shouldBe CubicMetrePerKilogram().toString()
            }
        }
    }

    "creates a base SpecificVolume" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.cubicMetresPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerKilogram()
                symbol shouldBe CubicMetrePerKilogram().toString()
            }
        }
    }
})
