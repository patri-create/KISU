package org.kisu.units.chemistry

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.cubicMetresPerMole
import org.kisu.units.chemistry.MolarVolume.Companion.CubicMetrePerMole

class MolarVolumeTest : StringSpec({
    "creates a MolarEnergy" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().cubicMetresPerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerMole(magnitude.builder().metric)
                symbol shouldBe CubicMetrePerMole().toString()
            }
        }
    }

    "creates a base MolarEnergy" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.cubicMetresPerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerMole()
                symbol shouldBe CubicMetrePerMole().toString()
            }
        }
    }
})
