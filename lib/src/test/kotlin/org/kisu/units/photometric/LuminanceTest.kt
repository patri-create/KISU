package org.kisu.units.photometric

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.candelasPerSquareMetre
import org.kisu.units.photometric.Luminance.Companion.CandelaPerSquareMetre

class LuminanceTest : StringSpec({
    "creates a Luminance" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().candelasPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CandelaPerSquareMetre(magnitude.builder().metric)
                symbol shouldBe CandelaPerSquareMetre().toString()
            }
        }
    }

    "creates a base Luminance" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.candelasPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CandelaPerSquareMetre()
                symbol shouldBe CandelaPerSquareMetre().toString()
            }
        }
    }
})
