package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.coulombsPerCubicMetre
import org.kisu.units.electromagnetic.ElectricChargeDensity.Companion.CoulombPerCubicMetre

class ElectricChargeDensityTest : StringSpec({
    "creates an ElectricChargeDensity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().coulombsPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerCubicMetre(magnitude.builder().metric)
                symbol shouldBe CoulombPerCubicMetre().toString()
            }
        }
    }

    "creates a base ElectricChargeDensity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.coulombsPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerCubicMetre()
                symbol shouldBe CoulombPerCubicMetre().toString()
            }
        }
    }
})
