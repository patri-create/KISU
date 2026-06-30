package org.kisu.units.kinematics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.base.Amount
import org.kisu.units.base.Time
import org.kisu.units.builders.cubicMetresPerSecond
import org.kisu.units.chemistry.CatalyticEfficiency
import org.kisu.units.kinematics.VolumetricFlow.Companion.CubicMetrePerSecond
import org.kisu.units.special.Volume

class VolumetricFlowTest : StringSpec({
    "creates a VolumetricFlow" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().cubicMetresPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerSecond(magnitude.builder().metric)
                symbol shouldBe CubicMetrePerSecond().toString()
            }
        }
    }

    "creates a base VolumetricFlow" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.cubicMetresPerSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerSecond()
                symbol shouldBe CubicMetrePerSecond().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a VolumetricFlow by an Amount returns a CatalyticEfficiency" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = VolumetricFlow(leftMagnitude, leftPrefix)
            val right = Amount(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = CatalyticEfficiency(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a VolumetricFlow by a CatalyticEfficiency returns an Amount" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = VolumetricFlow(leftMagnitude, leftPrefix)
            val right = CatalyticEfficiency(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Amount(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a VolumetricFlow by a Time returns a Volume" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = VolumetricFlow(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Volume(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
