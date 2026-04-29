package org.kisu.units.chemistry

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.molesPerCubicMetre
import org.kisu.units.chemistry.Molarity.Companion.MolePerCubicMetre

class MolarityTest : StringSpec({
    "creates a Molarity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().molesPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MolePerCubicMetre(magnitude.builder().metric)
                symbol shouldBe MolePerCubicMetre().toString()
            }
        }
    }

    "creates a base Molarity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.molesPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MolePerCubicMetre()
                symbol shouldBe MolePerCubicMetre().toString()
            }
        }
    }
})
