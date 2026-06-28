package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Current
import org.kisu.units.base.Length
import org.kisu.units.builders.amperesPerMetre
import org.kisu.units.electromagnetic.Magnetization.Companion.AmperePerMetre

class MagnetizationTest : StringSpec({
    "creates a Magnetization" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().amperesPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe AmperePerMetre(magnitude.builder().metric)
                symbol shouldBe AmperePerMetre().toString()
            }
        }
    }

    "creates a base Magnetization" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.amperesPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe AmperePerMetre()
                symbol shouldBe AmperePerMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a Magnetization by a Length returns a Current" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Magnetization(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Current(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
