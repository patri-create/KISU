package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.voltsPerMetre
import org.kisu.units.electromagnetic.ElectricFieldStrength.Companion.VoltPerMetre

class ElectricFieldStrengthTest : StringSpec({
    "creates an ElectricFieldStrength" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().voltsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe VoltPerMetre(magnitude.builder().metric)
                symbol shouldBe VoltPerMetre().toString()
            }
        }
    }

    "creates a base ElectricFieldStrength" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.voltsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe VoltPerMetre()
                symbol shouldBe VoltPerMetre().toString()
            }
        }
    }
})
