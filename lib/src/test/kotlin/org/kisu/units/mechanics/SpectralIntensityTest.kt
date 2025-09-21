package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.wattsPerSteradianMetre
import org.kisu.units.mechanics.SpectralIntensity.Companion.WattPerSteradianMetre

class SpectralIntensityTest : StringSpec({
    "creates a SpectralIntensity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerSteradianMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSteradianMetre(magnitude.builder().metric)
                symbol shouldBe WattPerSteradianMetre().toString()
            }
        }
    }

    "creates a base SpectralIntensity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.wattsPerSteradianMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSteradianMetre()
                symbol shouldBe WattPerSteradianMetre().toString()
            }
        }
    }
})
