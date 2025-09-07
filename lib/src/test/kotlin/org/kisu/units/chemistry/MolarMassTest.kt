package org.kisu.units.chemistry

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.gramsPerMole
import org.kisu.units.chemistry.MolarMass.Companion.KilogramPerMole

class MolarMassTest : StringSpec({
    "creates a MolarMass" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsPerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerMole(magnitude.builder().metric)
                symbol shouldBe KilogramPerMole().toString()
            }
        }
    }

    "creates a base MolarMass" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.gramsPerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramPerMole()
                symbol shouldBe KilogramPerMole().toString()
            }
        }
    }
})
