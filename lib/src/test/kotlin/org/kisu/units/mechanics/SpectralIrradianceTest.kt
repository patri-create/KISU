package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.wattsPerCubicMetre
import org.kisu.units.mechanics.SpectralIrradiance.Companion.WattPerCubicMetre

class SpectralIrradianceTest : StringSpec({
    "creates a SpectralIrradiance" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerCubicMetre(magnitude.builder().metric)
                symbol shouldBe WattPerCubicMetre().toString()
            }
        }
    }

    "creates a base SpectralIrradiance" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.wattsPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerCubicMetre()
                symbol shouldBe WattPerCubicMetre().toString()
            }
        }
    }
})
