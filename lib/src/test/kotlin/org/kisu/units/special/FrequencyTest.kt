package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.base.Time
import org.kisu.units.builders.hertz
import org.kisu.units.kinematics.FrequencyDrift

class FrequencyTest : StringSpec({
    "creates a Frequency" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().hertz.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Hertz(magnitude.builder().metric)
                symbol shouldBe Hertz.UNIT.toString()
            }
        }
    }

    "creates a base Frequency" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.hertz.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Hertz()
                symbol shouldBe Hertz.UNIT.toString()
            }
        }
    }

    "converts to Time" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.hertz.period.frequency.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Hertz()
                symbol shouldBe Hertz.UNIT.toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "dividing a Frequency by a Time returns a FrequencyDrift" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Frequency(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = FrequencyDrift(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Frequency by a FrequencyDrift returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Frequency(leftMagnitude, leftPrefix)
            val right = FrequencyDrift(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
