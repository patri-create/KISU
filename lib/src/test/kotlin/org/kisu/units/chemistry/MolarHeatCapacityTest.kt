package org.kisu.units.chemistry

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.joulePerKelvinMole

class MolarHeatCapacityTest : StringSpec({
    "creates a MolarHeatCapacity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulePerKelvinMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKelvinMole(magnitude.builder().metric)
                symbol shouldBe JoulePerKelvinMole().toString()
            }
        }
    }

    "creates a base MolarHeatCapacity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.joulePerKelvinMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerKelvinMole()
                symbol shouldBe JoulePerKelvinMole().toString()
            }
        }
    }
})
