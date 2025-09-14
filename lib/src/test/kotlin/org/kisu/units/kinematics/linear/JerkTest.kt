package org.kisu.units.kinematics.linear

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.metresPerSecondCubed
import org.kisu.units.kinematics.linear.Jerk.Companion.MetrePerSecondCubed

class JerkTest : StringSpec({
    "creates a linear Jerk" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metresPerSecondCubed.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondCubed(magnitude.builder().metric)
                symbol shouldBe MetrePerSecondCubed().toString()
            }
        }
    }

    "creates a base linear Jerk" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.metresPerSecondCubed.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondCubed()
                symbol shouldBe MetrePerSecondCubed().toString()
            }
        }
    }
})
