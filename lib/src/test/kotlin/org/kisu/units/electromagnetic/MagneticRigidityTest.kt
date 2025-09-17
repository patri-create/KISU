package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.teslasMetre
import org.kisu.units.electromagnetic.MagneticRigidity.Companion.TeslaMetre

class MagneticRigidityTest : StringSpec({
    "creates a MagneticRigidity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().teslasMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe TeslaMetre(magnitude.builder().metric)
                symbol shouldBe TeslaMetre().toString()
            }
        }
    }

    "creates a base MagneticRigidity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.teslasMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe TeslaMetre()
                symbol shouldBe TeslaMetre().toString()
            }
        }
    }
})
