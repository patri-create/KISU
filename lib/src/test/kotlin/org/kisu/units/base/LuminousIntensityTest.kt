package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveLong
import io.kotest.property.checkAll
import org.kisu.magnitude
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.candelas

class LuminousIntensityTest : StringSpec({
    "creates LuminousIntensity" {
        checkAll(Arb.positiveLong(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().candelas.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.magnitude
                expression shouldBe Candela(magnitude.builder().metric)
                symbol shouldBe Candela.UNIT.toString()
            }
        }
    }

    "creates a base LuminousIntensity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.candelas.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Candela()
                symbol shouldBe Candela.UNIT.toString()
            }
        }
    }
})
