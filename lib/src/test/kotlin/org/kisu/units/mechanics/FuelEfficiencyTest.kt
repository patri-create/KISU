package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.metresPerCubicMetre
import org.kisu.units.mechanics.FuelEfficiency.Companion.MetrePerCubicMetre

class FuelEfficiencyTest : StringSpec({
    "creates a FuelEfficiency" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metresPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerCubicMetre(magnitude.builder().metric)
                symbol shouldBe MetrePerCubicMetre().toString()
            }
        }
    }

    "creates a base FuelEfficiency" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.metresPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerCubicMetre()
                symbol shouldBe MetrePerCubicMetre().toString()
            }
        }
    }
})
