package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.base.Current
import org.kisu.units.builders.joulesPerTesla
import org.kisu.units.electromagnetic.MagneticDipoleMoment.Companion.JoulePerTesla
import org.kisu.units.special.Area

class MagneticDipoleMomentTest : StringSpec({
    "creates a MagneticDipoleMoment" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joulesPerTesla.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerTesla(magnitude.builder().metric)
                symbol shouldBe JoulePerTesla().toString()
            }
        }
    }

    "creates a base MagneticDipoleMoment" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.joulesPerTesla.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe JoulePerTesla()
                symbol shouldBe JoulePerTesla().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a MagneticDipoleMoment by a Current returns an Area" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MagneticDipoleMoment(leftMagnitude, leftPrefix)
            val right = Current(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Area(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a MagneticDipoleMoment by an Area returns a Current" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MagneticDipoleMoment(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Current(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
