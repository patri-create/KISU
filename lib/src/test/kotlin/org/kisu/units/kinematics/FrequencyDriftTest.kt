package org.kisu.units.kinematics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.hertzPerSecond
import org.kisu.units.kinematics.FrequencyDrift.Companion.HertzPerSecond

class FrequencyDriftTest : StringSpec({
    "creates a FrequencyDrift" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().hertzPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe HertzPerSecond(magnitude.builder().metric)
                symbol shouldBe HertzPerSecond().toString()
            }
        }
    }

    "creates a base FrequencyDrift" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.hertzPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe HertzPerSecond()
                symbol shouldBe HertzPerSecond().toString()
            }
        }
    }
})
