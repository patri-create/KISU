package org.kisu.units.chemistry

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.joulePerMole
import org.kisu.units.chemistry.MolarEnergy.Companion.JoulePerMole

class MolarEnergyTest : StringSpec({
    "creates a MolarEnergy" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulePerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerMole(magnitude.builder().metric)
                symbol shouldBe JoulePerMole().toString()
            }
        }
    }

    "creates a base MolarEnergy" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.joulePerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerMole()
                symbol shouldBe JoulePerMole().toString()
            }
        }
    }
})
