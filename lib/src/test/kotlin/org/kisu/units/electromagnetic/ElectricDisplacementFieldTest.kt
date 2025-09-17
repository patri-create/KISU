package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.coulombsPerSquareMetre
import org.kisu.units.electromagnetic.ElectricDisplacementField.Companion.CoulombPerSquareMetre

class ElectricDisplacementFieldTest : StringSpec({
    "creates an ElectricDisplacementField" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().coulombsPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerSquareMetre(magnitude.builder().metric)
                symbol shouldBe CoulombPerSquareMetre().toString()
            }
        }
    }

    "creates a base ElectricDisplacementField" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.coulombsPerSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerSquareMetre()
                symbol shouldBe CoulombPerSquareMetre().toString()
            }
        }
    }
})
