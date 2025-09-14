package org.kisu.units.kinematics.angular

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.radiansPerSecondFourth
import org.kisu.units.kinematics.angular.Snap.Companion.RadianPerSecondFourth

class SnapTest : StringSpec({
    "creates an angular Snap" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().radiansPerSecondFourth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecondFourth(magnitude.builder().metric)
                symbol shouldBe RadianPerSecondFourth().toString()
            }
        }
    }

    "creates a base angular Snap" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.radiansPerSecondFourth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecondFourth()
                symbol shouldBe RadianPerSecondFourth().toString()
            }
        }
    }
})
