package org.kisu.units.chemistry

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Amount
import org.kisu.units.base.Time
import org.kisu.units.builders.cubicMetresPerMoleSecond
import org.kisu.units.chemistry.CatalyticEfficiency.Companion.CubicMetrePerMoleSecond
import org.kisu.units.kinematics.VolumetricFlow

class CatalyticEfficiencyTest : StringSpec({
    "creates a CatalyticEfficiency" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().cubicMetresPerMoleSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerMoleSecond(magnitude.builder().metric)
                symbol shouldBe CubicMetrePerMoleSecond().toString()
            }
        }
    }

    "creates a base CatalyticEfficiency" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.cubicMetresPerMoleSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerMoleSecond()
                symbol shouldBe CubicMetrePerMoleSecond().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a CatalyticEfficiency by an Amount returns a VolumetricFlow" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = CatalyticEfficiency(leftMagnitude, leftPrefix)
            val right = Amount(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = VolumetricFlow(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    "multiplying a CatalyticEfficiency by a Time returns a MolarVolume" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = CatalyticEfficiency(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = MolarVolume(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
