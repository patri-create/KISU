package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.webers

class MagneticFluxTest : StringSpec({
    "creates a MagneticFlux" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().webers.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Weber(magnitude.builder().metric)
                symbol shouldBe Weber.UNIT.toString()
            }
        }
    }

    "creates a base MagneticFlux" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.webers.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Weber()
                symbol shouldBe Weber.UNIT.toString()
            }
        }
    }
})
