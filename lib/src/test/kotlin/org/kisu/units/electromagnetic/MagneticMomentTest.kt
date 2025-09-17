package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.webersMetre
import org.kisu.units.electromagnetic.MagneticMoment.Companion.WeberMetre

class MagneticMomentTest : StringSpec({
    "creates a MagneticMoment" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().webersMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WeberMetre(magnitude.builder().metric)
                symbol shouldBe WeberMetre().toString()
            }
        }
    }

    "creates a base MagneticMoment" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.webersMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WeberMetre()
                symbol shouldBe WeberMetre().toString()
            }
        }
    }
})
