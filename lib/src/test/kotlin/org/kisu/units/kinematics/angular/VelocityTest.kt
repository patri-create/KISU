package org.kisu.units.kinematics.angular

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.radiansPerSecond
import org.kisu.units.kinematics.angular.Velocity.Companion.RadianPerSecond

class VelocityTest : StringSpec({
    "creates an angular Velocity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().radiansPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecond(magnitude.builder().metric)
                symbol shouldBe RadianPerSecond().toString()
            }
        }
    }

    "creates a base angular Velocity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.radiansPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe RadianPerSecond()
                symbol shouldBe RadianPerSecond().toString()
            }
        }
    }
})
