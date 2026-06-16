package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.gramsPerCubicMetre
import org.kisu.units.mechanics.Density.Companion.KilogramPerCubicMetre

class DensityTest : StringSpec({
    "creates a Density" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerCubicMetre(magnitude.builder().metric)
                symbol shouldBe KilogramPerCubicMetre().toString()
            }
        }
    }

    "creates a base Density" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.gramsPerCubicMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerCubicMetre()
                symbol shouldBe KilogramPerCubicMetre().toString()
            }
        }
    }
})
