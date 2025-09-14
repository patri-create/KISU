package org.kisu.units.kinematics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.cubicMetresPerSecond
import org.kisu.units.kinematics.VolumetricFlow.Companion.CubicMetrePerSecond

class VolumetricFlowTest : StringSpec({
    "creates a VolumetricFlow" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().cubicMetresPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerSecond(magnitude.builder().metric)
                symbol shouldBe CubicMetrePerSecond().toString()
            }
        }
    }

    "creates a base VolumetricFlow" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.cubicMetresPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerSecond()
                symbol shouldBe CubicMetrePerSecond().toString()
            }
        }
    }
})
