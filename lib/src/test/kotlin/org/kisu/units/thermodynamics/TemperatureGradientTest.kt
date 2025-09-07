package org.kisu.units.thermodynamics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.kelvinsPerMetre

class TemperatureGradientTest : StringSpec({
    "creates a TemperatureGradient" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().kelvinsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KelvinPerMetre(magnitude.builder().metric)
                symbol shouldBe KelvinPerMetre().toString()
            }
        }
    }

    "creates a base TemperatureGradient" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.kelvinsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KelvinPerMetre()
                symbol shouldBe KelvinPerMetre().toString()
            }
        }
    }
})
