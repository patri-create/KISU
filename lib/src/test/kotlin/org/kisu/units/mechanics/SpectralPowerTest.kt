package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.wattsPerMetre
import org.kisu.units.mechanics.SpectralPower.Companion.WattPerMetre

class SpectralPowerTest : StringSpec({
    "creates a SpectralPower" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerMetre(magnitude.builder().metric)
                symbol shouldBe WattPerMetre().toString()
            }
        }
    }

    "creates a base SpectralPower" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.wattsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerMetre()
                symbol shouldBe WattPerMetre().toString()
            }
        }
    }
})
