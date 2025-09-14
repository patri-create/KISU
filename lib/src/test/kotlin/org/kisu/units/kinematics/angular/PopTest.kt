package org.kisu.units.kinematics.angular

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.radiansPerSecondSixth
import org.kisu.units.kinematics.angular.Pop.Companion.RadianPerSecondSixth

class PopTest : StringSpec({
    "creates an angular Pop" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().radiansPerSecondSixth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecondSixth(magnitude.builder().metric)
                symbol shouldBe RadianPerSecondSixth().toString()
            }
        }
    }

    "creates a base angular Pop" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.radiansPerSecondSixth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecondSixth()
                symbol shouldBe RadianPerSecondSixth().toString()
            }
        }
    }
})
