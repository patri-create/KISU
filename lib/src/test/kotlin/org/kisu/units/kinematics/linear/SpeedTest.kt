package org.kisu.units.kinematics.linear

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.metresPerSecond
import org.kisu.units.kinematics.linear.Speed.Companion.MetrePerSecond

class SpeedTest : StringSpec({
    "creates a linear Speed" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().metresPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecond(magnitude.builder().metric)
                symbol shouldBe MetrePerSecond().toString()
            }
        }
    }

    "creates a base linear Speed" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.metresPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MetrePerSecond()
                symbol shouldBe MetrePerSecond().toString()
            }
        }
    }
})
