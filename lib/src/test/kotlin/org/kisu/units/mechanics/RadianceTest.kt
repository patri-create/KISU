package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.wattsPerSteradianSquareMetre
import org.kisu.units.mechanics.Radiance.Companion.WattPerSteradianSquareMetre

class RadianceTest : StringSpec({
    "creates a Radiance" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerSteradianSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSteradianSquareMetre(magnitude.builder().metric)
                symbol shouldBe WattPerSteradianSquareMetre().toString()
            }
        }
    }

    "creates a base Radiance" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.wattsPerSteradianSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerSteradianSquareMetre()
                symbol shouldBe WattPerSteradianSquareMetre().toString()
            }
        }
    }
})
