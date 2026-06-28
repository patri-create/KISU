package org.kisu.units.chemistry

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
import org.kisu.units.builders.cubicMetresPerMole
import org.kisu.units.chemistry.MolarVolume.Companion.CubicMetrePerMole
import org.kisu.units.special.Volume

class MolarVolumeTest : StringSpec({
    "creates a MolarEnergy" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().cubicMetresPerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerMole(magnitude.builder().metric)
                symbol shouldBe CubicMetrePerMole().toString()
            }
        }
    }

    "creates a base MolarEnergy" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.cubicMetresPerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerMole()
                symbol shouldBe CubicMetrePerMole().toString()
            }
        }
    }

    "converts to Molarity" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.cubicMetresPerMole.molarity.molarVolume.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerMole()
                symbol shouldBe CubicMetrePerMole().toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "dividing a MolarVolume by a Time returns a CatalyticEfficiency" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MolarVolume(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = CatalyticEfficiency(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a MolarVolume by a CatalyticEfficiency returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MolarVolume(leftMagnitude, leftPrefix)
            val right = CatalyticEfficiency(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying a MolarVolume by an Amount returns a Volume" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MolarVolume(leftMagnitude, leftPrefix)
            val right = Amount(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Volume(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
