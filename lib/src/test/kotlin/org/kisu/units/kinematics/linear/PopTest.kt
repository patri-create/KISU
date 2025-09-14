package org.kisu.units.kinematics.linear

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.metresPerSecondSixth
import org.kisu.units.kinematics.linear.Pop.Companion.MetrePerSecondSixth

class PopTest : StringSpec({
    "creates a linear Pop" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metresPerSecondSixth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondSixth(magnitude.builder().metric)
                symbol shouldBe MetrePerSecondSixth().toString()
            }
        }
    }

    "creates a base linear Pop" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.metresPerSecondSixth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondSixth()
                symbol shouldBe MetrePerSecondSixth().toString()
            }
        }
    }
})
