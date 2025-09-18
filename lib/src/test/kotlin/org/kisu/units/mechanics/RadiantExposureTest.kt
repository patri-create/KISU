package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.joulesPerSquareMetre
import org.kisu.units.mechanics.RadiantExposure.Companion.JoulePerSquareMetre

class RadiantExposureTest : StringSpec({
    "creates a RadiantExposure" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerSquareMetre(magnitude.builder().metric)
                symbol shouldBe JoulePerSquareMetre().toString()
            }
        }
    }

    "creates a base RadiantExposure" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.joulesPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerSquareMetre()
                symbol shouldBe JoulePerSquareMetre().toString()
            }
        }
    }
})
