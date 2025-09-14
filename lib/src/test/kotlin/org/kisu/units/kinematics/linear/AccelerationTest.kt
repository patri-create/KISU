package org.kisu.units.kinematics.linear

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.metresPerSecondSquared
import org.kisu.units.kinematics.linear.Acceleration.Companion.MetrePerSecondSquared

class AccelerationTest : StringSpec({
    "creates a linear Acceleration" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metresPerSecondSquared.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondSquared(magnitude.builder().metric)
                symbol shouldBe MetrePerSecondSquared().toString()
            }
        }
    }

    "creates a base linear Acceleration" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.metresPerSecondSquared.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecondSquared()
                symbol shouldBe MetrePerSecondSquared().toString()
            }
        }
    }
})
