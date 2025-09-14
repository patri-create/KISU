package org.kisu.units.kinematics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.gramsMetreSecondCubed
import org.kisu.units.kinematics.Yank.Companion.KilogramMetrePerSecondCubed

class YankTest : StringSpec({
    "creates a Yank" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsMetreSecondCubed.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramMetrePerSecondCubed(magnitude.builder().metric)
                symbol shouldBe KilogramMetrePerSecondCubed().toString()
            }
        }
    }

    "creates a base Yank" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.gramsMetreSecondCubed.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramMetrePerSecondCubed()
                symbol shouldBe KilogramMetrePerSecondCubed().toString()
            }
        }
    }
})
