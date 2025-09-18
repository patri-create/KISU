package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.newtonsPerMetre
import org.kisu.units.mechanics.SurfaceTension.Companion.NewtonPerMetre

class SurfaceTensionTest : StringSpec({
    "creates a SurfaceTension" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().newtonsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe NewtonPerMetre(magnitude.builder().metric)
                symbol shouldBe NewtonPerMetre().toString()
            }
        }
    }

    "creates a base SurfaceTension" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.newtonsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe NewtonPerMetre()
                symbol shouldBe NewtonPerMetre().toString()
            }
        }
    }
})
