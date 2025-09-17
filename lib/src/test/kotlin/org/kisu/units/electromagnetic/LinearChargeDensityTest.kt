package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.coulombsPerMetre
import org.kisu.units.electromagnetic.LinearChargeDensity.Companion.CoulombPerMetre

class LinearChargeDensityTest : StringSpec({
    "creates a LinearChargeDensity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().coulombsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerMetre(magnitude.builder().metric)
                symbol shouldBe CoulombPerMetre().toString()
            }
        }
    }

    "creates a base LinearChargeDensity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.coulombsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerMetre()
                symbol shouldBe CoulombPerMetre().toString()
            }
        }
    }
})
