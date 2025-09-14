package org.kisu.units.kinematics.linear

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.metresPerSecondFourth
import org.kisu.units.kinematics.linear.Snap.Companion.MetrePerSecondFourth

class SnapTest : StringSpec({
    "creates a linear Snap" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metresPerSecondFourth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondFourth(magnitude.builder().metric)
                symbol shouldBe MetrePerSecondFourth().toString()
            }
        }
    }

    "creates a base linear Snap" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.metresPerSecondFourth.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondFourth()
                symbol shouldBe MetrePerSecondFourth().toString()
            }
        }
    }
})
