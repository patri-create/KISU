package org.kisu.units.kinematics.angular

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.radiansPerSecondCubed
import org.kisu.units.kinematics.angular.Jerk.Companion.RadianPerSecondCubed

class JerkTest : StringSpec({
    "creates an angular Jerk" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().radiansPerSecondCubed.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecondCubed(magnitude.builder().metric)
                symbol shouldBe RadianPerSecondCubed().toString()
            }
        }
    }

    "creates a base angular Jerk" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.radiansPerSecondCubed.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecondCubed()
                symbol shouldBe RadianPerSecondCubed().toString()
            }
        }
    }
})
