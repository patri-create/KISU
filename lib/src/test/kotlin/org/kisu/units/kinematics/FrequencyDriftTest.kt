package org.kisu.units.kinematics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Time
import org.kisu.units.builders.hertzPerSecond
import org.kisu.units.kinematics.FrequencyDrift.Companion.HertzPerSecond
import org.kisu.units.special.Frequency

class FrequencyDriftTest : StringSpec({
    "creates a FrequencyDrift" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().hertzPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe HertzPerSecond(magnitude.builder().metric)
                symbol shouldBe HertzPerSecond().toString()
            }
        }
    }

    "creates a base FrequencyDrift" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.hertzPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe HertzPerSecond()
                symbol shouldBe HertzPerSecond().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a FrequencyDrift by a Time returns a Frequency" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = FrequencyDrift(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Frequency(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
