package org.kisu.units.kinematics.angular

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.radiansPerSecondSquared
import org.kisu.units.kinematics.angular.Acceleration.Companion.RadianPerSecondSquared

class AccelerationTest : StringSpec({
    "creates an angular Acceleration" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().radiansPerSecondSquared.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecondSquared(magnitude.builder().metric)
                symbol shouldBe RadianPerSecondSquared().toString()
            }
        }
    }

    "creates a base angular Acceleration" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.radiansPerSecondSquared.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecondSquared()
                symbol shouldBe RadianPerSecondSquared().toString()
            }
        }
    }
})
