package org.kisu.units.kinematics.angular

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.radiansPerSecondFifth
import org.kisu.units.kinematics.angular.Crackle.Companion.RadianPerSecondFifth

class CrackleTest : StringSpec({
    "creates a Crackle" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().radiansPerSecondFifth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecondFifth(magnitude.builder().metric)
                symbol shouldBe RadianPerSecondFifth().toString()
            }
        }
    }

    "creates a base Crackle" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.radiansPerSecondFifth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecondFifth()
                symbol shouldBe RadianPerSecondFifth().toString()
            }
        }
    }
})
