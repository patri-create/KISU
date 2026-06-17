package org.kisu.units.photometric

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.lumensSecond
import org.kisu.units.photometric.LuminousEnergy.Companion.LumenSecond

class LuminousEnergyTest : StringSpec({
    "creates a LuminousEnergy" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().lumensSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe LumenSecond(magnitude.builder().metric)
                symbol shouldBe LumenSecond().toString()
            }
        }
    }

    "creates a base LuminousEnergy" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.lumensSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe LumenSecond()
                symbol shouldBe LumenSecond().toString()
            }
        }
    }
})
