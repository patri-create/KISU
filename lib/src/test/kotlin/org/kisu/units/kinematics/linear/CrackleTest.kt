package org.kisu.units.kinematics.linear

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.metresPerSecondFifth
import org.kisu.units.kinematics.linear.Crackle.Companion.MetrePerSecondFifth

class CrackleTest : StringSpec({
    "creates a linear Crackle" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metresPerSecondFifth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondFifth(magnitude.builder().metric)
                symbol shouldBe MetrePerSecondFifth().toString()
            }
        }
    }

    "creates a base linear Crackle" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.metresPerSecondFifth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondFifth()
                symbol shouldBe MetrePerSecondFifth().toString()
            }
        }
    }
})
